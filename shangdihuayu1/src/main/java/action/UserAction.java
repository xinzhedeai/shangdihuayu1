package action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import normalMapper.UserMapper;
import normalPo.User;
import normalPo.UserExample;
import pageModel.EasyUIGridObj;
import pageModel.JsonResult;
import service.UserService;
import util.MSG_CONST;
import util.PropertiesUtil;
import util.SpringUtils;
@Component
@RequestMapping("/userAction")
public class UserAction extends BaseAction{
	
	public static Logger logger  = Logger.getLogger(UserAction.class);
	
	@Value("${path}")
	private String path;
	
	@Value("${jdbc.driver}")
	private String driver;
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userServiceImpl;
	
	@ResponseBody
	@RequestMapping("/loginSubmit")
	public JsonResult loginSubmit1(HttpServletRequest req)throws Exception{
		System.out.println("进到login里面了。");
		Map reqMap = SpringUtils.getParameterMap(req);
		JsonResult j = new JsonResult();
		Map resultMap = (Map) userServiceImpl.getUserInfo(reqMap);
		
		if(resultMap != null){
			j.setSuccess(true);
			j.setMsg("登录成功！");
			j.setResult(resultMap);
		}else{
			j.setSuccess(false);
			j.setMsg("账号或密码错误！");
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping("/getUserListByPage")
	public JsonResult getUserListByPage(HttpServletRequest req) throws Exception{
		
		String BASE_PATH = PropertiesUtil.getPropertieRes("/config/path.properties", "path");
		System.out.println(BASE_PATH);
//		Thread.sleep(2000000000);
		Map reqMap = SpringUtils.getParameterMap(req);
		JsonResult j = new JsonResult();
		EasyUIGridObj easyUIGridObj = (EasyUIGridObj) userServiceImpl.getUserListByPage(reqMap);
		if(easyUIGridObj != null){
			j.setSuccess(true);
			j.setMsg(MSG_CONST.READSUCCESS);
			j.setResult(easyUIGridObj);
		}else{
			j.setSuccess(false);
			j.setMsg(MSG_CONST.READFAIL);
		}
		return j;
	}
	/**
	 * 获取全部用户
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getAllUserList")
	public JsonResult getUserAllList(HttpServletRequest req) throws Exception{
		JsonResult j = new JsonResult();
		List<Map> list = userServiceImpl.getUserAllList();
		if(list != null){
			j.setSuccess(true);
			j.setMsg(MSG_CONST.READSUCCESS);
			j.setResult(list);
		}else{
			j.setSuccess(false);
			j.setMsg(MSG_CONST.READFAIL);
		}
		return j;
	}
	
}
