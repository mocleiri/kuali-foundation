/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.data.impl;

import org.apache.commons.lang3.StringUtils;

/**
 * This class parses a .mpx file and creates an in-memory representation of the data
 * 
 * @author andrewlubbers
 */
public class MpxParser {

	private static final String QUOTE = "\"";
	private static final String TOKEN_DELIMITER = QUOTE + "," + QUOTE;

	/**
	 * Split the line up into individual values and remove any .mpx related formatting
	 */
	public static String[] parseMpxLine(String line) {
		// Remove trailing/leading quotes
		String trimmed = trimQuotes(line);

		// Split the line up into individual values
		String[] values = StringUtils.splitByWholeSeparator(trimmed, TOKEN_DELIMITER);

		// Convert mpx special values (i.e. ${mpx.lf} -> \n )
		for (int i = 0; i < values.length; i++) {
			values[i] = ParseUtils.parse(values[i]);
		}

		// These are the original string values with all of the .mpx related
		// formatting removed
		return values;
	}

	/**
	 * Remove leading and trailing quotes (if any)
	 */
	public static String trimQuotes(String line) {
		if (StringUtils.startsWith(line, QUOTE)) {
			line = StringUtils.substring(line, QUOTE.length());
		}
		int length = line.length();
		if (StringUtils.endsWith(line, QUOTE)) {
			line = StringUtils.substring(line, 0, length - QUOTE.length());
		}
		return line;
	}

}
