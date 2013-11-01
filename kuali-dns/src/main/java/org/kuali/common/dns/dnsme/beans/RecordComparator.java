/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.common.dns.dnsme.beans;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

public class RecordComparator implements Comparator<Record> {

	@Override
	public int compare(Record record1, Record record2) {
		String name1 = record1.getName();
		String name2 = record2.getName();

		Integer nullCompare = getNullCompare(name1, name2);
		if (nullCompare != null) {
			return nullCompare;
		}

		String[] tokens1 = StringUtils.split(name1, ".");
		String[] tokens2 = StringUtils.split(name2, ".");

		String s1 = getReversedString(tokens1);
		String s2 = getReversedString(tokens2);

		return s1.compareTo(s2);
	}

	protected String getReversedString(String[] tokens) {
		StringBuilder sb = new StringBuilder();
		for (int i = tokens.length - 1; i >= 0; i--) {
			sb.append(tokens[i]);
		}
		return sb.toString();
	}

	protected Integer getNullCompare(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return 0;
		} else if (s1 != null && s2 == null) {
			return 1;
		} else if (s1 == null && s2 != null) {
			return -1;
		} else {
			return null;
		}
	}

}
