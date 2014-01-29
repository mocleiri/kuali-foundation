package org.kuali.maven.plugins.dnsme.beans;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public final class RecordNameComparator implements Comparator<Record> {

	@Override
	public int compare(Record one, Record two) {
		return compareStrings(one.getName(), two.getName());
	}

	protected int compareStrings(String one, String two) {
		Splitter splitter = Splitter.on('.');
		Joiner joiner = Joiner.on('.');
		List<String> t1 = Lists.newArrayList(splitter.split(one));
		List<String> t2 = Lists.newArrayList(splitter.split(two));
		padEnvString(t1);
		padEnvString(t2);
		Collections.reverse(t1);
		Collections.reverse(t2);
		String c1 = joiner.join(t1);
		String c2 = joiner.join(t2);
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
