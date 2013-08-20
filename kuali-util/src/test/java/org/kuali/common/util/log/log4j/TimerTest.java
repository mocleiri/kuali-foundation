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
package org.kuali.common.util.log.log4j;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.kuali.common.util.FormatUtils;

public class TimerTest {

	@Test
	public void test() {
		try {
			String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String s = sdf.format(new Date());
			String target = "2013-09-09T17:00:00.000-0700";
			Date then = sdf.parse(target);
			long millis = then.getTime() - System.currentTimeMillis();
			System.out.println(FormatUtils.getTime(millis));
			double twentyDays = 20 * FormatUtils.DAY;
			long newMillis = (long) (millis - twentyDays);
			System.out.println(FormatUtils.getTime(newMillis));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
