package org.kuali.common.util;

import junit.framework.Assert;

import org.junit.Test;

public class StrTest {

	@Test
	public void testConceal() {
		String s = "foo.bar.baz";
		String concealed = Str.conceal(s);
		String revealed = Str.reveal(concealed);
		Assert.assertEquals(s, revealed);
	}

}
