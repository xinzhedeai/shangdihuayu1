package tiantainbeishengjing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropTest {

	@Test
	public void test() {
	        Resource resource = null;
	        Properties props = null;
	        String path = null;
	        try {
	            resource = new ClassPathResource("/config/path.properties");
	            props = PropertiesLoaderUtils.loadProperties(resource);
	            path = (String) props.get("path");
	            System.err.println(path);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         
    }

}
