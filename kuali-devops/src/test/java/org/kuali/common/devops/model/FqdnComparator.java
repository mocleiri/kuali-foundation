package org.kuali.common.devops.model;

import java.util.Comparator;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class FqdnComparator implements Comparator<String> {

	private static final char SEPARATOR = '.';
	private static final Splitter SPLITTER = Splitter.on(SEPARATOR);
	private static final Joiner JOINER = Joiner.on(SEPARATOR);

	@Override
	public int compare(String fqdn1, String fqdn2) {
		String r1 = reverse(fqdn1);
		String r2 = reverse(fqdn2);
		return r1.compareTo(r2);
	}

	protected String reverse(String fqdn) {
		return JOINER.join(Lists.reverse(SPLITTER.splitToList(fqdn)));
	}

}
