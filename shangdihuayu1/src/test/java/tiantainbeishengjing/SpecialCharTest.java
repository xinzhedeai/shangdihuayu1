package tiantainbeishengjing;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class SpecialCharTest {

	@Test
	public void test() {
		String orgStr = "黄韵玲、钟兴民 - 安静的对话.mp3";
		if (null!=orgStr&&!"".equals(orgStr.trim())) {
			String regEx="[\\s~·`!！@#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。》>、/？?]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(orgStr);
			System.out.println(m.replaceAll(""));
		}
	}

}
