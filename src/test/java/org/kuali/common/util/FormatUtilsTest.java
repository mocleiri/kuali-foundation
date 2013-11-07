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

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(FormatUtilsTest.class);

	@Test
	public void testSplitEvenly() {
		try {
			logger.info("Hello World");
			Date now = new Date();
			String s = FormatUtils.getDate(now);
			logger.info(s);
			// Date parsed1 = FormatUtils.parseDate(s);
			// FastDateFormat adds a colon for UTC offset
			// 2008-11-13T14:06:29.000-07:00
			// vs
			// 2013-02-13T11:57:53.549-0700
			// Date parsed2 = FormatUtils.parseDate("");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
