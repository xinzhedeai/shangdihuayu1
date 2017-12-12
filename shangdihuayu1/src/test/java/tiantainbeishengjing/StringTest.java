package tiantainbeishengjing;

import static org.junit.Assert.*;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.junit.Test;

public class StringTest {

	@Test
	public void test() {
		String[] a = {"a", "b", "c"};
		System.out.println();
		for (String str : a) {
			System.err.println(str);
		}
	}

}
