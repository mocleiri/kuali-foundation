package org.kuali.common.util;

import java.nio.charset.Charset;

import org.junit.Test;

public class SimpleTest {

	@Test
	public void testFileEncoding() {
		System.out.println("file.encoding=" + System.getProperty("file.encoding"));
		System.out.println("Charset.defaultCharset().name()=" + Charset.defaultCharset().name());
	}

}
