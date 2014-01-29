package org.kuali.maven.plugins.dnsme.beans;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public final class RecordNameComparator implements Comparator<Record> {

	private static final Comparator<String> COMPARATOR = new ReverseStringComparator();

	@Override
	public int compare(Record one, Record two) {
		String n1 = one.getName();
		String n2 = two.getName();
		if (n1.startsWith("env") && n2.startsWith("env")) {
			return compareEnvRecords(n1, n2);
		} else {
			return COMPARATOR.compare(one.getName(), two.getName());
		}
	}

	protected int compareEnvRecords(String one, String two) {
		Splitter splitter = Splitter.on('.');
		Joiner joiner = Joiner.on('.');
		List<String> t1 = Lists.newArrayList(splitter.split(one));
		List<String> t2 = Lists.newArrayList(splitter.split(two));
		Integer i1 = Integer.parseInt(t1.get(0).substring(3));
		Integer i2 = Integer.parseInt(t2.get(0).substring(3));
		String s1 = StringUtils.leftPad(i1.toString(), 10, "0");
		String s2 = StringUtils.leftPad(i2.toString(), 10, "0");
		t1.set(0, s1);
		t2.set(0, s2);
		Collections.reverse(t1);
		Collections.reverse(t2);
		String c1 = joiner.join(t1);
		String c2 = joiner.join(t2);
		return c1.compareTo(c2);
	}

}
