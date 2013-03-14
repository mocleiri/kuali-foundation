package org.kuali.common.util;

import org.codehaus.plexus.util.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SizeUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(SizeUtilsTest.class);

	@Test
	public void test() {
		show("1");
		show("1b");
		show("1k");
		show("1m");
		show("1g");
		show("1t");
		show("1p");
		show("1e");
		System.out.println();
		show("1.378g");
		show("1.921t");
	}

	protected void show(String size) {
		long bytes = FormatUtils.getBytes(size);
		String original = lpad(size, 6);
		String formatted = lpad(FormatUtils.getSize(bytes), 7);
		String raw = lpad(FormatUtils.getCount(bytes), 25);
		Object[] args = { original, formatted, raw };
		logger.info("{} - {} - {}", args);
	}

	protected String lpad(String s, int size) {
		return StringUtils.leftPad(s, size, " ");
	}

	protected String lpad(long number, int size) {
		return lpad(number + "", size);
	}
}
