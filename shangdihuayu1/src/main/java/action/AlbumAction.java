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
import util.DateUtil;
import util.MSG_CONST;
import util.SpringUtils;
import util.StringUtil;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/albumAction")
public class AlbumAction {
	public static Logger logger = Logger.getLogger(UserAction.class);

	@Autowired
	private AlbumService albumServiceImpl;
	@Autowired
	private FileService fileServiceImpl;
	@ResponseBody
	@RequestMapping("/addAlbum")
	public JsonResult addScripture(HttpSession session, HttpServletRequest req) throws Exception {
		JsonResult j = new JsonResult();
		Map paramMap = new HashMap();
		paramMap = SpringUtils.getParameterMap(req);
		String album_id = StringUtil.converterToSpell(paramMap.get("album_name").toString());
		paramMap.put("album_id", album_id);
		try {
			if (fileServiceImpl.insertFile(paramMap) > 0 && albumServiceImpl.insertAlbum(paramMap) > 0) {
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
	@RequestMapping("/getAlbumListByPage")
	public JsonResult getUserListByPage(HttpServletRequest req) throws Exception{
//		Thread.sleep(2000000000);
		Map reqMap = SpringUtils.getParameterMap(req);
		reqMap.put("rel_path", "/storage/upload/Img/");
		JsonResult j = new JsonResult();
		EasyUIGridObj easyUIGridObj = (EasyUIGridObj) albumServiceImpl.getAlbumListByPage(reqMap);
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
