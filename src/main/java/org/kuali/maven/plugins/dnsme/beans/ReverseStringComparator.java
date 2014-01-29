package org.kuali.maven.plugins.dnsme.beans;

import java.util.Comparator;

import org.codehaus.plexus.util.StringUtils;

public final class ReverseStringComparator implements Comparator<String> {

	@Override
	public int compare(String one, String two) {
		String r1 = StringUtils.reverse(one);
		String r2 = StringUtils.reverse(two);
		return r1.compareTo(r2);
	}

}
