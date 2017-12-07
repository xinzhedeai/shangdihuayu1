package tiantainbeishengjing;

import static org.junit.Assert.*;

import org.junit.Test;

import util.StringUtil;

public class PinyinTest {

	@Test
	public void test() {
		String s = StringUtil.converterToSpell("哈利路亚");
		System.out.println(s);
	}

}
