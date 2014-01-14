package org.kuali.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AsciiTest {

	@Test
	public void test() {
		assertTrue(Ascii.isDigit('0'));
		assertFalse(Ascii.isDigit('a'));
		assertFalse(Ascii.isLowerCase('Z'));
		assertFalse(Ascii.isLowerCase((char) 0));
		assertFalse(Ascii.isLowerCase((char) 500));
		assertTrue(Ascii.isLetter('a'));
		assertTrue(Ascii.isLetter('A'));
		assertFalse(Ascii.isLetter('0'));
		assertEquals('0', Ascii.flip('5'));
		assertEquals('5', Ascii.flip('0'));
		assertEquals('a', Ascii.flip('n'));
		assertEquals('n', Ascii.flip('a'));
		assertEquals('A', Ascii.flip('N'));
		assertEquals('N', Ascii.flip('A'));
	}

}
