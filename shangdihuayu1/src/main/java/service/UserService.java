package service;

import java.util.List;
import java.util.Map;

import pageModel.ActiveUser;
import pageModel.EasyUIGridObj;
@SuppressWarnings("rawtypes")
public interface UserService {
	//用户认证
	public int checkUser(Map map) throws Exception;

	public Object getUserInfo(Map reqMap);

	public EasyUIGridObj getUserListByPage(Map reqMap);
}
