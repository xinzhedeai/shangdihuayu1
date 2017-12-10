package action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.Spring;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.charts.ManuallyPositionable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import customMapper.CustomAlbumMapper;
import customMapper.CustomFileMapper;
import customMapper.CustomVoiceMapper;
import exception.SysException;
import normalMapper.ScriptureMapper;
import normalMapper.UserMapper;
import normalPo.Scripture;
import normalPo.ScriptureExample;
import pageModel.EasyUIGridObj;
import pageModel.JsonResult;
import service.AlbumService;
import util.BeanMapConvertUtil;
import util.DateUtil;
import util.MD5Util;
import util.MSG_CONST;
import util.SpringUtils;
import util.StringUtil;
import util.WDUtil;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/importAction")
public class ImportAction {
	public static Logger logger = Logger.getLogger(UserAction.class);

	@Autowired
	private AlbumService AlbumServiceImpl;
	@Autowired
	private CustomAlbumMapper customAlbumMapper;
	@Autowired
	private CustomVoiceMapper customVoiceMapper;
	@Autowired
	private CustomFileMapper customFileMapper;

	@ResponseBody
	@RequestMapping("/importVoiceByExcel")
	public JsonResult impScriptureBatch(HttpSession session, HttpServletRequest req) throws SysException {
		JsonResult j = new JsonResult();
		logger.error("进入importByExcel中");
		try {
			Boolean uploadSuccess = true;
			String file_path = "";
			// 将excel文件保存到本地
			req.setCharacterEncoding("UTF-8");
			MultipartRequest multiReq = (MultipartRequest) req;
			Iterator<String> files = multiReq.getFileNames();
			while (files.hasNext()) {
				String fileName = (String) files.next();
				MultipartFile multiFile = multiReq.getFile(fileName);
				if (multiFile != null && !multiFile.isEmpty()) {
					file_path = req.getSession().getServletContext().getRealPath("/storage/");
					if (multiFile != null && !multiFile.isEmpty()) {
						String originName = multiFile.getOriginalFilename();
						originName = URLDecoder.decode(originName, "UTF-8");
						String file_extension = (originName.substring(originName.lastIndexOf(".") + 1)).toLowerCase();
						if (!file_extension.equals("xls") && !file_extension.equals("xlsx"))
							throw new SysException("请上传正确的文件!");
						// if(file_extension.equals("xlsx")) throw new
						// SysException("仅支持2003版本的Excel!");
						File f = new File(file_path);
						FileOutputStream fos = null;
						if (!f.exists()) {
							f.mkdirs();
							logger.info("创建了" + file_path + "文件夹.");
						}

						file_path = file_path + "test." + file_extension;
						try {
							fos = new FileOutputStream(file_path);
							if (multiFile != null && !multiFile.isEmpty())
								fos.write(multiFile.getBytes());
						} catch (Exception e) {
							uploadSuccess = false;
							logger.error(e);
							throw new SysException("写文件错误.");
						} finally {
							if (fos != null)
								try {
									fos.close();
									fos = null;
								} catch (IOException e) {
									uploadSuccess = false;
									logger.error(e);
									throw new SysException("关闭文件流错误.");
								}
						}
					}
				}
			}
			// 将excel表中内容读出来
			if (uploadSuccess) {
				for(int i = 0; i < 1; i++)
					readExcel(req, file_path, i);
				File f = new File(file_path);
				f.delete();
			}

		} catch (Exception e) {
			logger.error(e);
			throw new SysException("上传excel出现异常。");
		}

		return j;
	}

	public void readExcel(HttpServletRequest req, String fileName, int sheetNum) throws SysException {
		try {
			InputStream input = new FileInputStream(fileName); // 建立输入流
			Workbook wb = null;
			boolean isExcel2003 = true;
			Sheet sheet;
			Iterator<Row> rows = null;
			if (WDUtil.isExcel2007(fileName)) {// 我是用本地2013作的excel表，改了后缀名为xls格式的，
				// 虽然判断后认为事2003以前的，但实际解析还是为2007以上的文件格式。
				isExcel2003 = false;
			}
			if (isExcel2003) {
				wb = new HSSFWorkbook(input);// hssF是Excel2003以前（包括2003）的版本没有问题
				sheet = (HSSFSheet) wb.getSheetAt(sheetNum);
				rows = sheet.rowIterator();
			} else {
				wb = new XSSFWorkbook(input);
				sheet = (XSSFSheet) wb.getSheetAt(sheetNum);
				rows = sheet.rowIterator();
			}

			// 获取excel表中有数据的行数
			int numOfRow = sheet.getLastRowNum();
			int dataRowNum = 0;
			if (numOfRow > 0) {
				for (int rowNum = 1; rowNum <= numOfRow; rowNum++) {
					Row hssfRow = sheet.getRow(rowNum);
					if (isEmptyRow1(hssfRow) == false) {
						dataRowNum++;
					}
				}
			}
			logger.info("lineNum已读行数" + dataRowNum);
			// 获取表中的数据，每个公司信息存一个Map，所有的放在一个List<Map>中,同样的，每个用户信息存一个Map，所有的放在一个List<Map>中。
			List<Map> voice_list = new ArrayList();

			int line = 0;
			while (rows.hasNext()) {
				Map voiceMap = new HashMap();
				Row row = rows.next(); // 获得行数据
				Iterator<Cell> cells = row.cellIterator();// 获得第一行的迭代器
				if (line != 0) {
					String[] list = new String[6];
					int num = 0;
					while (cells.hasNext()) {
						Cell cell = cells.next();
						if (num != cell.getColumnIndex()) {
							list[num] = "";
							num++;// 这里的num++为什么要写两个。
						}
						list[num] = getValue(cell).trim();
						num++;
					}
					/**
					 * 0:音频封面 1:音频名称2:一级专辑3:二级专辑4:主播名称5:音频拓展名
					 */
					String voice_img = list[0], voice_name = list[1], album_lv1_name = list[2], 
						   album_lv2_name = list[3], author = list[4], extension = list[5],
						   author_pinyin = StringUtil.converterToSpell(StringUtil.replaceSpecStr(author)),
						   voice_name_pinyin = StringUtil.converterToSpell(StringUtil.replaceSpecStr(voice_name)),
						   album_lv1_name_pinyin = StringUtil.converterToSpell(StringUtil.replaceSpecStr(album_lv1_name));
					
//					voiceMap.put("voice_img", voice_img);//音频封面图片
					voiceMap.put("voice_id", voice_name_pinyin);
					voiceMap.put("multimedia_file_name", voice_name + extension);//音频名称:haliluya.mp3 
					voiceMap.put("album_id", album_lv1_name_pinyin);//一级专辑id
					voiceMap.put("album_name", album_lv1_name);//一级专辑名称
					voiceMap.put("user_id", author_pinyin);//主播名称
					voiceMap.put("module", album_lv1_name);
					voiceMap.put("multimedia_module", album_lv1_name);
					if(!album_lv2_name.toString().isEmpty()){//二级专辑不为空
						String album_lv2_name_pinyin = StringUtil.converterToSpell(StringUtil.replaceSpecStr(album_lv2_name));
						voiceMap.put("album_id", album_lv2_name_pinyin);//二级专辑id
						voiceMap.put("parent_id", album_lv1_name_pinyin);//一级专辑id
						voiceMap.put("album_name", list[3]);//二级专辑名称
						voiceMap.put("module", album_lv1_name + '/' + album_lv2_name);
						voiceMap.put("multimedia_module", album_lv1_name + '/' + album_lv2_name);
					}
					voice_list.add(voiceMap);
					logger.error("第二個單元格的内容" + voice_name + "&&&&共" + dataRowNum +"行");
				}

				if (line == dataRowNum)
					break;
				line++;
			}
			logger.error("已读"+ line +"行");
			// 将数据存入数据库 && insertMultiMediaFile(map) == 0 && insertImgFile(map) == 0
			for (Map map : voice_list) {
				System.out.println(map);
				int a = insertVoice(map),
				b = insertMultiMediaFile(map),
				c = insertImgFile(map),
				d = insertAlbum(map);
				if(a == 0  
						&& b == 0 
						&& c == 0
						&& d == 0){
					throw new SysException("导入失败!");
				}else{
					System.out.println(map.get("module"));
				}
			}
    
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private int insertAlbum(Map map) {
		return customAlbumMapper.insertAlbum(map);
	}

	private int insertImgFile(Map map) {
//		return customFileMapper.insertMultiMediaImg(map);
		return 1;
	}

	private int insertMultiMediaFile(Map map) {
		return customFileMapper.insertMultiMediaFile(map);
	}

	private int insertVoice(Map map) {
		return customVoiceMapper.insertVoice(map);
	}

	private static String getValue(Cell hssfCell) {
		if (hssfCell == null) {
			return "";
		} else {
			try {
				if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
					// 返回布尔类型的值
					return toString(hssfCell.getBooleanCellValue());
				} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
					try {
						// 返回数值类型的值
						boolean isCellDateFormatted = HSSFDateUtil.isCellDateFormatted(hssfCell);
						if (isCellDateFormatted) {
							short format = hssfCell.getCellStyle().getDataFormat();
							SimpleDateFormat sdf = null;
							if (format == 14 || format == 31 || format == 57 || format == 58) {
								// 日期
								sdf = new SimpleDateFormat("yyyy-MM-dd");
							} else if (format == 20 || format == 32) {
								// 时间
								sdf = new SimpleDateFormat("HH:mm");
							}
							double value = hssfCell.getNumericCellValue();
							Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
							return sdf.format(date);
						} else {
							try {
								DecimalFormat df = new DecimalFormat("0");
								String number = df.format(hssfCell.getNumericCellValue());
								return number;

							} catch (Exception e) {
								return toString(hssfCell.getNumericCellValue());
							}
						}
					} catch (Exception e) {
						return toString(hssfCell.getNumericCellValue());
					}
				} else {
					// 返回字符串类型的值
					return toString(hssfCell.getStringCellValue());
				}
			} catch (Exception e) {
				return "";
			}
		}
	}

	// 只要没有数据,就判断为空行,不管它是不是有格式,而poi自带的row == null,指的是既没有数据又没有格式Style
	public static boolean isEmptyRow1(Row hssfRow) {
		int temp = 0;
		boolean isEmptyRow = false;
		if (hssfRow == null) {
			isEmptyRow = true;
		} else {
			for (int i = 0; i <= hssfRow.getLastCellNum(); i++) {
				if (hssfRow.getCell(i) != null) {
					if (getValue(hssfRow.getCell(i)) != null && !("".equals(getValue(hssfRow.getCell(i))))) {
						temp++;
					}
				}
			}
			if (temp == 0) {
				isEmptyRow = true;
			}
		}
		return isEmptyRow;
	}

	public static String toString(Object object) {
		String temp = "";
		if (object != null) {
			temp = object.toString();
		}
		return temp;
	}
}
