/**
 * Copyright 2010-2012 The Kuali Foundation
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

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TimeUtils {

	/**
	 * Parse milliseconds from a time string that ends with a unit of measure. If no unit of measure is provided, milliseconds is assumed
	 * 
	 * <pre>
	 *   500ms == 500 milliseconds
	 *   3s    == 3 seconds = 3000 milliseconds
	 *   1m    == 1 minute  = 60,000 milliseconds
	 * </pre>
	 */
	public static long getMillis(String time) {
		Assert.notBlank(time);
		List<String> tokens = Arrays.asList("ms", "s", "m", "h", "d", "y");
		List<Integer> multipliers = Arrays.asList(1, 1000, 1000 * 60, 1000 * 60 * 60, 1000 * 60 * 60 * 24, 1000 * 60 * 60 * 24 * 365);
		Assert.isTrue(tokens.size() == multipliers.size());
		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			int multiplier = multipliers.get(i);
			if (StringUtils.endsWithIgnoreCase(time, token)) {
				return getLongValue(time, token, multiplier);
			}
		}
		// Assume milliseconds
		return new Long(time);
	}

	protected static long getLongValue(String time, String endsWithToken, int multiplier) {
		int len = StringUtils.length(time);
		String substring = StringUtils.substring(time, 0, len - endsWithToken.length());
		Long longValue = new Long(substring);
		return longValue * multiplier;
	}
}
