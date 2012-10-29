/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.common.aws.s3;

import java.util.Date;

import org.junit.Test;

public class SimpleFormatterTest {
	SimpleFormatter formatter = new SimpleFormatter();

	@Test
	public void testDateFormatting() {
		try {
			Date now = new Date();
			String s = formatter.getDate(now);
			formatter.parseDate(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
