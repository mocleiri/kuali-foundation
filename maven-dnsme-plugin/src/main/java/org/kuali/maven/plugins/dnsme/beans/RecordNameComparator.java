package org.kuali.maven.plugins.dnsme.beans;

import java.util.Comparator;

public final class RecordNameComparator implements Comparator<Record> {

	private static final Comparator<String> COMPARATOR = new ReverseStringComparator();

	@Override
	public int compare(Record one, Record two) {
		return COMPARATOR.compare(one.getName(), two.getName());
	}

}
