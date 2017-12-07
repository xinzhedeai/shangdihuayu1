package tiantainbeishengjing;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Test;

public class propertiesTest {

	@Test
	public void test() {
			FileInputStream in = null;
			try{
				Properties properties = new Properties();
				in = new FileInputStream("config\\db.properties");
				properties.load(in);
				System.out.println("读取配置信息成功！");
				System.out.println(properties.getProperty("path"));
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
			
	}

}
