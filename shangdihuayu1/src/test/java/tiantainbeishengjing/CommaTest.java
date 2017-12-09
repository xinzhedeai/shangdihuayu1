package tiantainbeishengjing;

import static org.junit.Assert.*;

import org.junit.Test;

import util.StringUtil;

public class CommaTest {

	@Test
	public void test() {
		System.out.println(StringUtil.removeCommaChar("哈利 ,路亚!"));
		System.err.println("哈利 ,路亚!".trim());
	}

}
