package org.kuali.common.util;

import org.junit.Test;

public class StrTest {

	@Test
	public void testConceal() {
		try {
			String s = "foo.bar.baz";
			String concealed = Str.conceal(s);
			String revealed = Str.reveal(concealed);
			System.out.println(concealed + "=" + revealed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
