package tiantainbeishengjing;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import normalPo.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import customMapper.CustomUserInfoMapper;

public class ReflectTest {

	@Autowired
	private CustomUserInfoMapper customUserInfoMapper;
	
	@Test
	public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		Method[] methods = customUserInfoMapper.getClass().getDeclaredMethods();
		User user = new User();
		
		System.out.println(user);
		System.out.println(user.getClass());
		Method targetMethod = user.getClass().getMethod("getUserName", null);
		String s = (String) targetMethod.invoke(user, null);
		System.out.println(s);
	}

}
