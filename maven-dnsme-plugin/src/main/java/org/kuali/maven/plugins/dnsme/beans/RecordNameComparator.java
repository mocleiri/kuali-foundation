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
package org.kuali.maven.plugins.dnsme.beans;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public final class RecordNameComparator implements Comparator<Record> {

	private static final char SEPARATOR = '.';
	private static final Splitter SPLITTER = Splitter.on(SEPARATOR);
	private static final Joiner JOINER = Joiner.on(SEPARATOR);

	@Override
	public int compare(Record one, Record two) {
		return compareStrings(one.getName(), two.getName());
	}

	protected int compareStrings(String one, String two) {
		List<String> t1 = Lists.newArrayList(SPLITTER.split(one));
		List<String> t2 = Lists.newArrayList(SPLITTER.split(two));
		padEnvString(t1);
		padEnvString(t2);
		Collections.reverse(t1);
		Collections.reverse(t2);
		String c1 = JOINER.join(t1);
		String c2 = JOINER.join(t2);
		return c1.compareTo(c2);
	}

	protected List<String> padEnvString(List<String> list) {
		String first = list.get(0);
		if (first.startsWith("env")) {
			Integer i = Integer.parseInt(first.substring(3));
			String s = StringUtils.leftPad(i.toString(), 10, "0");
			list.set(0, s);
		}
		return list;
	}

}
