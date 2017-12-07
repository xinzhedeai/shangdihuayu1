package tiantainbeishengjing;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;

public class pathTest {

	@Test
	public void test() {
		
	     ClassLoader classLoader = pathTest.class.getClassLoader();  
	        URL resource = classLoader.getResource("config/path.properties");  
	        String path = resource.getPath();  
	        System.out.println(resource.getRef());
	        System.out.println(path);  
	}

}
