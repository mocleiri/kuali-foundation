package org.kuali.common.devops.util;

import static org.apache.commons.lang.StringUtils.leftPad;

import java.util.Comparator;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class FqdnComparator implements Comparator<String> {

	private static final char SEPARATOR = '.';
	private static final Splitter SPLITTER = Splitter.on(SEPARATOR);
	private static final Joiner JOINER = Joiner.on(SEPARATOR);

	@Override
	public int compare(String fqdn1, String fqdn2) {
		List<String> tokens1 = updateTokens(SPLITTER.splitToList(fqdn1));
		List<String> tokens2 = updateTokens(SPLITTER.splitToList(fqdn2));
		String s1 = JOINER.join(tokens1);
		String s2 = JOINER.join(tokens2);
		return s1.compareTo(s2);
	}

	protected List<String> updateTokens(List<String> tokens) {
		if (tokens.get(0).startsWith("env")) {
			String token = tokens.get(0);
			Integer integer = Integer.parseInt(token.substring(3));
			String padded = "env" + leftPad(integer.toString(), 3, "0");
			tokens.set(0, padded);
		}
		return tokens;
	}

}
