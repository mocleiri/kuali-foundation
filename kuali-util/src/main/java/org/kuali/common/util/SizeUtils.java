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

public class SizeUtils {

	private static final List<String> TOKENS = Arrays.asList("b", "k", "m", "g", "t", "p", "e");
	private static final int BASE = 1024;

	/**
	 * Parse bytes from a size string that ends with a unit of measure. If no unit of measure is provided, bytes is assumed. Unit of measure is case insensitive.
	 * 
	 * <pre>
	 *   1  == 1 byte
	 *   1b == 1 byte
	 *   1k == 1 kilobyte == 1024   bytes ==                     1,024 bytes
	 *   1m == 1 megabyte == 1024^2 bytes ==                 1,048,576 bytes
	 *   1g == 1 gigabyte == 1024^3 bytes ==             1,073,741,824 bytes
	 *   1t == 1 terabyte == 1024^4 bytes ==         1,099,511,627,776 bytes
	 *   1p == 1 petabyte == 1024^5 bytes ==     1,125,899,906,842,624 bytes
	 *   1e == 1 exabyte  == 1024^6 bytes == 1,152,921,504,606,846,976 bytes
	 * </pre>
	 */
	public static long getBytes(String size) {
		return getBytes(size, TOKENS, BASE);
	}

	public static long getBytes(String size, List<String> tokens, int base) {
		Assert.notBlank(size);
		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			long multiplier = (long) Math.pow(base, i);
			if (StringUtils.endsWithIgnoreCase(size, token)) {
				return getLongValue(size, token, multiplier);
			}
		}
		// Assume bytes
		return getLongValue(size, "", 1);
	}

	protected static long getLongValue(String time, String suffix, long multiplier) {
		int len = StringUtils.length(time);
		String substring = StringUtils.substring(time, 0, len - suffix.length());
		Double value = new Double(substring);
		value = value * multiplier;
		return value.longValue();
	}
}
