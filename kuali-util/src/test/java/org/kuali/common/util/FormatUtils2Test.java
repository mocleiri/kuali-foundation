/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
			for (int i = 0; i < 55; i++) {
				long pow = (long) Math.pow(number, i);
				String time = FormatUtils.getTime(pow);
				String size = FormatUtils.getSize(pow);
				System.out.print("pow=" + lpad(FormatUtils.getCount(pow), 23) + "   time=" + lpad(time, 10) + "    size=" + lpad(size, 8));
				long millis = FormatUtils.getMillis(time);
				long bytes = FormatUtils.getBytes(size);
				System.out.println("    time=" + lpad(FormatUtils.getCount(millis), 23) + "    size=" + lpad(FormatUtils.getCount(bytes), 23));
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
