package org.kuali.common.devops.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class FqdnComparator implements Comparator<String> {

	private static final char SEPARATOR = '.';
	private static final Splitter SPLITTER = Splitter.on(SEPARATOR);
	private static final Joiner JOINER = Joiner.on(SEPARATOR);

	@Override
	public int compare(String fqdn1, String fqdn2) {
		String r1 = getReversed(fqdn1);
		String r2 = getReversed(fqdn2);
		return r1.compareTo(r2);
	}

	protected String getReversed(String fqdn) {
		List<String> tokens = Lists.newArrayList((SPLITTER.split(fqdn)));
		Collections.reverse(tokens);
		return JOINER.join(tokens);
	}

}
