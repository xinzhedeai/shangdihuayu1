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

import exception.SysException;
import normalMapper.ScriptureMapper;
import normalMapper.UserMapper;
import normalPo.Scripture;
import normalPo.ScriptureExample;
import pageModel.EasyUIGridObj;
import pageModel.JsonResult;
import service.AlbumService;
import service.FileService;
import service.VoiceService;
import util.DateUtil;
import util.MSG_CONST;
import util.SpringUtils;
import util.StringUtil;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/voiceAction")
public class VoiceAction {
	public static Logger logger = Logger.getLogger(UserAction.class);

	@Autowired
	private VoiceService voiceServiceImpl;
	@Autowired
	private FileService fileServiceImpl;
	@ResponseBody
	@RequestMapping("/addVoice")
	public JsonResult addVoice(HttpServletRequest req) throws Exception {
		JsonResult j = new JsonResult();
		Map paramMap = new HashMap();
		paramMap = SpringUtils.getParameterMap(req);
		String voice_id = paramMap.get("multimedia_file_name").toString();
		voice_id = StringUtil.replaceSpecStr(voice_id);//去掉特殊字符
		voice_id = StringUtil.converterToSpell(voice_id);//汉字变拼音
		
		int flag = 1;//flag为1时则表示为添加一级专辑；2时为二级专辑；默认为1级专辑
		flag = paramMap.get("album_id_lv2").toString().isEmpty() ? 1 : 2;
		String album_id_lv1_pinyin = "", album_id_lv2_pinyin = "";
		album_id_lv1_pinyin = StringUtil.converterToSpell(paramMap.get("album_id_lv1").toString());
		//当音频为第一级专辑下的，那么则写入一级专辑的ID，否则写入二级专辑的ID， 使用相同的参数值album_id; 
		paramMap.put("album_id", album_id_lv1_pinyin);
		if(flag == 2){
			album_id_lv2_pinyin = StringUtil.converterToSpell(paramMap.get("album_id_lv2").toString());
			paramMap.put("album_id", album_id_lv2_pinyin);
		}
		paramMap.put("voice_id", voice_id);
		try {
			if (voiceServiceImpl.insertVoice(paramMap) > 0
					&& fileServiceImpl.insertMultiMediaImg(paramMap) > 0 
					&& fileServiceImpl.insertMultiMediaFile(paramMap) > 0) {
				j.setSuccess(true);
				j.setMsg(MSG_CONST.ADDSUCCESS);
			} else {
				j.setSuccess(false);
				j.setMsg(MSG_CONST.ADDFAIL);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new SysException(MSG_CONST.ERROR);
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping("/getVoiceListByPage")
	public JsonResult getVoiceListByPage(HttpServletRequest req) throws Exception{
//		Thread.sleep(2000000000);
		Map reqMap = SpringUtils.getParameterMap(req);
		JsonResult j = new JsonResult();
		EasyUIGridObj easyUIGridObj = (EasyUIGridObj) voiceServiceImpl.getVoiceListByPage(reqMap);
		if(easyUIGridObj != null){
		
			j.setSuccess(true);
			j.setMsg(MSG_CONST.READSUCCESS);
			j.setResult(easyUIGridObj);
		}else{
			j.setSuccess(false);
			j.setMsg(MSG_CONST.READSUCCESS);
		}
		return j;
	}
}
