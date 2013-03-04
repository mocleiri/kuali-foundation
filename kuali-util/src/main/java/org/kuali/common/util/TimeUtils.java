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

	private static final List<String> TOKENS = Arrays.asList("ms", "s", "m", "h", "d", "y");
	private static final List<Integer> MULTIPLIERS = Arrays.asList(1, 1000, 1000 * 60, 1000 * 60 * 60, 1000 * 60 * 60 * 24, 1000 * 60 * 60 * 24 * 365);

	/**
	 * Parse milliseconds from a time string that ends with a unit of measure. If no unit of measure is provided, milliseconds is assumed. Unit of measure is case insensitive.
	 * 
	 * <pre>
	 *   1   == 1 millisecond
	 *   1ms == 1 millisecond
	 *   1s  == 1 second ==           1000 milliseconds
	 *   1m  == 1 minute ==         60,000 milliseconds
	 *   1h  == 1 hour   ==      3,600,000 milliseconds 
	 *   1d  == 1 day    ==     86,400,000 milliseconds
	 *   1y  == 1 year   == 31,536,000,000 milliseconds
	 * </pre>
	 */
	public static long getMillis(String time) {
		return getMillis(time, TOKENS, MULTIPLIERS);
	}

	public static long getMillis(String time, List<String> tokens, List<Integer> multipliers) {
		Assert.notBlank(time);
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

	protected static long getLongValue(String time, String suffix, int multiplier) {
		int len = StringUtils.length(time);
		String substring = StringUtils.substring(time, 0, len - suffix.length());
		Long longValue = new Long(substring);
		return longValue * multiplier;
	}
}
