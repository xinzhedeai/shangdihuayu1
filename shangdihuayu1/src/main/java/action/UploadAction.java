package action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import exception.SysException;
import pageModel.JsonResult;
import util.ImageCutUtil;
import util.MSG_CONST;
import util.PropertiesUtil;
import util.StringUtil;

@Controller
@RequestMapping("/uploadAction")
public class UploadAction extends BaseAction{
	
	@ResponseBody
	@RequestMapping("/uploadFile")
	public JsonResult UploadFile(HttpServletRequest req) throws SysException, IOException{
		String BASE_PATH = PropertiesUtil.getPropertieRes("/config/path.properties", "path");
		System.out.println(BASE_PATH);	
		
		JsonResult result = new JsonResult();
		//List filesList = new ArrayList();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Comparable> fileMap = new HashMap<String, Comparable>();
		List<Map<String, Comparable>> filesList = new ArrayList<Map<String, Comparable>>();
			try {
				String module = req.getParameter("module");	
				String type = req.getParameter("type");
//				String module = "junzundejingbai";
				req.setCharacterEncoding("UTF-8");
				MultipartRequest multiReq = (MultipartRequest) req;
				Iterator<String> files = multiReq.getFileNames();
				long FILE_SIZE = 0, DL_CNT = 0;
				int ismainpic = 0, successcnt = 0;
				// 多个文件上传
				while (files.hasNext()) {
					String fileName = (String) files.next();
					MultipartFile multiFile = multiReq.getFile(fileName);
					if (multiFile != null && !multiFile.isEmpty()) {
//						String file_path = req.getSession().getServletContext().getRealPath(relPath) + module;
						
						String absolute_file_folder = BASE_PATH + type+ "/" + module;
						String relative_file_folder = "/"+ type+ "/" + module;
						System.out.println(absolute_file_folder);
						if (multiFile != null && !multiFile.isEmpty()) {

							if(multiFile.getSize() > (int)(50*1024*1024)) throw new SysException("文件大小不能超过50MB，请重新上传!");
							
							String originName = multiFile.getOriginalFilename();
							originName = URLDecoder.decode(originName, "UTF-8");
							if("image".equals(originName)){//只有图片的时候,才会对文件名称进行拼音格式化
								originName = StringUtil.removeCommaChar(originName);
								originName = StringUtil.converterToSpell(originName);
							}
							if (originName.length() > 49)
								originName = originName.substring(originName.length() - 49);
							
							String file_content_type = multiFile.getContentType();
							FILE_SIZE = multiFile.getSize();

							// 上传文件
							File f = new File(absolute_file_folder);
							Boolean uploadSuccess = true;
							FileOutputStream fos = null;
							if (!f.exists()) {
								f.mkdirs();
								logger.info("创建了" + absolute_file_folder + "文件夹.");
							}
							//文件的绝对路径
							String file_absolute_path =  absolute_file_folder + "/" + originName;
							String file_relative_path = relative_file_folder + "/" + originName;
							logger.info("文件路径：" + file_absolute_path);
							try {
								fos = new FileOutputStream(file_absolute_path);
								if (multiFile != null && !multiFile.isEmpty())
									fos.write(multiFile.getBytes());

								successcnt++;
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
							// 插入数据库
							if (uploadSuccess) {
								fileMap.put("path", file_relative_path);
								fileMap.put("type", type);
								fileMap.put("module", module);
								fileMap.put("file_name", originName);
								fileMap.put("file_content_type", file_content_type);
								logger.info(file_relative_path);
								
								result.setResult(fileMap);
								result.setSuccess(true);
								result.setMsg(MSG_CONST.UPLOADSUCCESS);
								
							}else{
								result.setSuccess(true);
								result.setMsg(MSG_CONST.UPLOADFAIL);
							}
						}else{
							logger.info("文件对象为空！~！！");
						}
					}
					ismainpic++;
				}
				
				if(ismainpic != successcnt) throw new SysException("总共上传了" + ismainpic + "个文件，成功" + successcnt + "，失败" + (ismainpic - successcnt) + "。");
		}finally{
			
		}
		return result;
	}
	/**
	 * 上传音频方法
	 * @param req
	 * @return
	 * @throws SysException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/uploadVoiceFile")
	public JsonResult UploadMultiMediaFile(HttpServletRequest req) throws SysException, IOException{
		String BASE_PATH = PropertiesUtil.getPropertieRes("/config/path.properties", "path");
		JsonResult result = new JsonResult();
		//List filesList = new ArrayList();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Comparable> fileMap = new HashMap<String, Comparable>();
		List<Map<String, Comparable>> filesList = new ArrayList<Map<String, Comparable>>();
			try {
				System.out.println(BASE_PATH);
				String module = req.getParameter("module");//文件的所属模块
				String type = req.getParameter("type");//上传的文件类型
				req.setCharacterEncoding("UTF-8");
				MultipartRequest multiReq = (MultipartRequest) req;
				Iterator<String> files = multiReq.getFileNames();
				long FILE_SIZE = 0, DL_CNT = 0;
				int ismainpic = 0, successcnt = 0;
				// 多个文件上传
				while (files.hasNext()) {
					String fileName = (String) files.next();
					MultipartFile multiFile = multiReq.getFile(fileName);
					if (multiFile != null && !multiFile.isEmpty()) {
//						String file_path = req.getSession().getServletContext().getRealPath(relPath) + module;
						String file_path = BASE_PATH + type + module;
						System.out.println(file_path);
						if (multiFile != null && !multiFile.isEmpty()) {

							if(multiFile.getSize() > (int)(50*1024*1024)) throw new SysException("文件大小不能超过50MB，请重新上传!");
							
							String originName = multiFile.getOriginalFilename();
							originName = URLDecoder.decode(originName, "UTF-8");
							originName = StringUtil.removeCommaChar(originName);
							originName = StringUtil.converterToSpell(originName);
							if (originName.length() > 49)
								originName = originName.substring(originName.length() - 49);
							
							String file_content_type = multiFile.getContentType();
							FILE_SIZE = multiFile.getSize();

							// 上传文件
							File f = new File(file_path);
							Boolean uploadSuccess = true;
							FileOutputStream fos = null;
							if (!f.exists()) {
								f.mkdirs();
								logger.info("创建了" + file_path + "文件夹.");
							}
							//绝对路径
							file_path =  file_path + "/" + originName;
							
							logger.info("文件路径：" + file_path);
							try {
								fos = new FileOutputStream(file_path);
								if (multiFile != null && !multiFile.isEmpty())
									fos.write(multiFile.getBytes());

								successcnt++;
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
							// 插入数据库
							if (uploadSuccess) {
								fileMap.put("path", BASE_PATH + module + "/" + originName);
								fileMap.put("file_name", originName);
								fileMap.put("file_content_type", file_content_type);
								logger.info(file_path);
								
								result.setResult(fileMap);
								result.setSuccess(true);
								result.setMsg(MSG_CONST.UPLOADSUCCESS);
								
							}else{
								result.setSuccess(true);
								result.setMsg(MSG_CONST.UPLOADFAIL);
							}
						}else{
							logger.info("文件对象为空！~！！");
						}
					}
					ismainpic++;
				}
				
				if(ismainpic != successcnt) throw new SysException("总共上传了" + ismainpic + "个文件，成功" + successcnt + "，失败" + (ismainpic - successcnt) + "。");
		}finally{
			
		}
		return result;
	}
}
