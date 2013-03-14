package org.kuali.common.util;

import org.codehaus.plexus.util.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtils2Test {

	private static final Logger logger = LoggerFactory.getLogger(FormatUtils2Test.class);

	@Test
	public void test1() {
		try {
			long number = 2;
			for (int i = 0; i < 45; i++) {
				long pow = (long) Math.pow(number, i);
				String time = FormatUtils.getTime(pow);
				String size = FormatUtils.getSize(pow);
				System.out.print("pow=" + lpad(FormatUtils.getCount(pow), 20) + "   time=" + lpad(time, 10) + "    size=" + lpad(size, 8));
				long millis = FormatUtils.getMillis(time);
				long bytes = FormatUtils.getBytes(size);
				System.out.println("    time=" + lpad(FormatUtils.getCount(millis), 20) + "    size=" + lpad(FormatUtils.getCount(bytes), 20));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
