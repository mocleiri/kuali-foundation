package org.kuali.common.util;

import junit.framework.Assert;

import org.junit.Test;

public class StrTest {

	@Test
	public void testConceal() {
		String original = "foo.bar.baz";
		String concealed = Str.conceal(original);
		String revealed = Str.reveal(concealed);
		Assert.assertEquals(original, revealed); // Test that we got our original string back
		Assert.assertEquals(concealed, Str.conceal(concealed)); // Attempting to conceal an already concealed string is a NOOP
		Assert.assertEquals(revealed, Str.reveal(revealed)); // Attempting to reveal an already revealed string is a NOOP
	}

	@Test
	public void test2() {
		String original = "bar.baz";
		System.out.println(Str.conceal(original));
	}
}
