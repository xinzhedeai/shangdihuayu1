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
public class UploadImgAction extends BaseAction{
	
	@Value("${path}")
	private String relPath;
	
	@ResponseBody
	@RequestMapping("/uploadFile")
	public JsonResult UploadFile(HttpServletRequest req) throws SysException, IOException{
//		String relPath = PropertiesUtil.getProperties();
		String relPath = "/storage/upload/Img/";
		JsonResult result = new JsonResult();
		//List filesList = new ArrayList();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Comparable> fileMap = new HashMap<String, Comparable>();
		List<Map<String, Comparable>> filesList = new ArrayList<Map<String, Comparable>>();
			try {
				System.out.println(relPath);
				String module = req.getParameter("module");	
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
						String file_path = "D:photo/img/" + module;
						System.out.println(file_path);
						if (multiFile != null && !multiFile.isEmpty()) {

							if(multiFile.getSize() > (int)(2.5*1024*1024)) throw new SysException("文件过大，请重新上传!");
							
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
								fileMap.put("path", relPath + module + "/" + originName);
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
	
	@ResponseBody
	@RequestMapping("/userInfoUploadImg")
	public JsonResult userInfoUploadImg(HttpServletRequest req){
		JsonResult j = new JsonResult();
		Map<String, String> paramMap = new HashMap<String, String>();
		String usId = req.getParameter("usId");
		paramMap.put("US_ID", usId);
		String headImg = (String) req.getParameter("headImg");
		paramMap.put("HEAD_IMG", headImg);
		try {
			//userService.userInfoUploadImg(sysFileInfo);
			String files = (String)req.getParameter("files");
			//这个paramMap存放的是userid和图片名。
			
				String path = (String) req.getParameter("file_absolute_path");
				//图片缩放
				if(req.getParameter("scale") != null && req.getParameter("flag") != null) {
					int scale = Integer.valueOf((String)req.getParameter("scale")).intValue();
					String flagStr =  (String)req.getParameter("flag");
					boolean flag = "true".equals(flagStr) == true? true:false;
					ImageCutUtil.scale(path, headImg, scale, flag);
				}
				//图片裁剪
				if(req.getParameter("x") != null && req.getParameter("y") != null 
						&&req.getParameter("destWidth") != null && req.getParameter("destHeight") != null 
						&& req.getParameter("file_absolute_path") != null) {
					int x = Integer.valueOf((String) req.getParameter("x")).intValue();
					int y = Integer.valueOf((String) req.getParameter("y")).intValue();
					int destWidth = Integer.valueOf((String) req.getParameter("destWidth")).intValue();
					int destHeight = Integer.valueOf((String) req.getParameter("destHeight")).intValue();
					ImageCutUtil.abscut(path, headImg, x, y, destWidth, destHeight);
				}
				//图片压缩
				ImageCutUtil.resize(path, headImg);
				if (!StringUtil.isEmpty(files)) {
					/*if(!userService.saveFiles(files, usId)||userService.modifyHeadImg(paramMap) <= 0) 
						j.setMsg("头像上传失败");*/
				}
			
			//session.commit();
				j.setMsg("头像修改成功！");
				j.setSuccess(true);
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
