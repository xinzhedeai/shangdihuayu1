package util;
import java.io.FileInputStream;
import java.util.Properties;


public class PropertiesUtil {
	public static String PATH = null;
	
	public static String getProperties(){
		FileInputStream in = null;
		try{
			Properties properties = new Properties();
			in = new FileInputStream("path.properties");
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
}