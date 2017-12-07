package action;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import normalMapper.UserMapper;
import normalPo.User;
import normalPo.UserExample;
import pageModel.EasyUIGridObj;
import pageModel.JsonResult;
import service.UserService;
import util.SpringUtils;
@Controller
@RequestMapping("/userAction")
public class UserAction extends BaseAction{
	
	public static Logger logger  = Logger.getLogger(UserAction.class);
	
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
//		Thread.sleep(2000000000);
		Map reqMap = SpringUtils.getParameterMap(req);
		JsonResult j = new JsonResult();
		EasyUIGridObj easyUIGridObj = (EasyUIGridObj) userServiceImpl.getUserListByPage(reqMap);
		if(easyUIGridObj != null){
			j.setSuccess(true);
			j.setMsg("登录成功！");
			j.setResult(easyUIGridObj);
		}else{
			j.setSuccess(false);
			j.setMsg("账号或密码错误！");
		}
		return j;
	}
	
}
