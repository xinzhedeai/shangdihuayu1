package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import pageModel.EasyUIGridObj;
import customMapper.CustomUserInfoMapper;
import exception.SysException;
import service.UserService;
import util.PageUtil;

public class UserServiceImpl implements UserService {

	@Autowired
	private CustomUserInfoMapper customUserInfoMapper;
	
	@Value("${jdbc.driver}")
	private String driver;
	@Value("${path}")
	private String path;
	
	public int checkUser(Map map) throws Exception {
		return customUserInfoMapper.checkUser(map);
	}

	public List<Map> getUserAllList() {
		// TODO Auto-generated method stub
		return customUserInfoMapper.getUserAllList();
	}

	@Override
	public Object getUserInfo(Map reqMap) {
		return customUserInfoMapper.getUserInfo(reqMap);
	}

	@Override
	public EasyUIGridObj getUserListByPage(Map reqMap) {
		EasyUIGridObj obj = null;
		System.out.println("*******************************");
		System.out.println("springSERVICE注入路径path*****" + path);
		System.out.println("springSERVICE注入路径driver*****" + driver);
		System.out.println("*******************************");
		try {
			obj = PageUtil.searchByPage(customUserInfoMapper, reqMap, "getUserListByPage");
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
