/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.cloudfront;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.aws.s3.BucketConstants;

/**
 * Sort version numbers in the correct order.
 * 
 * <pre>
 * 1.1.10          <  1.1.2
 * 1.1.10-SNAPSHOT <  1.1.10
 * </pre>
 */
public class DisplayRowComparator implements Comparator<DisplayRow> {

	public static final String DEFAULT_SEPARATORS = ".-";

	String separators = DEFAULT_SEPARATORS;
	String delimiter = BucketConstants.DEFAULT_DELIMITER;

	@Override
	public int compare(DisplayRow one, DisplayRow two) {

		String show1 = one.getShow();
		String show2 = two.getShow();

		if (show1 == null && show2 == null) {
			return 0;
		}

		if (show1 == null) {
			return -1;
		}

		if (show2 == null) {
			return 1;
		}

		show1 = StringUtils.strip(show1, delimiter);
		show2 = StringUtils.strip(show2, delimiter);

		String[] tokens1 = StringUtils.split(show1, separators);
		String[] tokens2 = StringUtils.split(show2, separators);

		int len = Math.min(tokens1.length, tokens2.length);

		for (int i = 0; i < len; i++) {
			String token1 = tokens1[i];
			String token2 = tokens2[i];
			int compareTo = compareTokens(token1, token2);
			if (compareTo != 0) {
				return compareTo;
			}
		}

		return tokens1.length - tokens2.length;
	}

	protected int compareTokens(String token1, String token2) {
		try {
			int i1 = Integer.parseInt(token1);
			int i2 = Integer.parseInt(token2);
			return Double.compare(i1, i2);
		} catch (NumberFormatException e) {
			; // Intentionally ignore this
		}

		// Fall through to sorting things as regular strings
		return token1.compareTo(token2);

	}

	public String getSeparators() {
		return separators;
	}

	public void setSeparators(String separators) {
		this.separators = separators;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

}
