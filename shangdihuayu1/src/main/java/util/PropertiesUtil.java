package util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class PropertiesUtil {
	public static String PATH = null;
	
	public static String getProperties(){//暂时废弃的方法
		FileInputStream in = null;
		try{
			Properties properties = new Properties();
			in = new FileInputStream("path.properties");//这个输入流在获取资源文件路径的是会有问题
			properties.load(in);
			PATH = properties.getProperty("path");
			
//			System.out.println("读取配置信息成功！");
//			showConfig();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("读取配置信息失败！");
		}finally{
			if(in != null){
				try{
					in.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return PATH;
	}
	
/*	private static void showConfig(){
		System.out.println("-----------------------配置信息-----------------");
		System.out.println("dirver: "+PATH);
		System.out.println("----------------------------------------------");
	}*/
	
	public static Map getPropertiesRes(String file_path, String[] prop_names){
		Resource resource = null;
	    Properties props = null;
	    String path = null;
	    Map propMap = new HashMap();
	    try {
	    	//ClassPathResource这个获取项目中的文件比较靠谱
	        resource = new ClassPathResource(file_path);
	        props = PropertiesLoaderUtils.loadProperties(resource);
	        
	        for (String prop_name : prop_names) {
	        	propMap.put(prop_name, (String) props.get(prop_name));
			}
//	        System.err.println(propMap.toString());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return propMap;
	}
	/**
	 * 从资源文件中获取单个属性的 值
	 * @param file_path
	 * @param prop_name
	 * @return
	 */
	public static String getPropertieRes(String file_path, String prop_name){
		Resource resource = null;
	    Properties props = null;
	    String prop_value = null;
	    try {
	        resource = new ClassPathResource(file_path);
	        props = PropertiesLoaderUtils.loadProperties(resource);
	        prop_value = (String) props.get(prop_name);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return prop_value;
	}
}