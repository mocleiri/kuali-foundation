package org.kuali.common.devops.model;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang.StringUtils.leftPad;

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
		List<String> tokens1 = update(Lists.newArrayList((SPLITTER.split(fqdn1))));
		List<String> tokens2 = update(Lists.newArrayList(SPLITTER.split(fqdn2)));
		String s1 = JOINER.join(tokens1);
		String s2 = JOINER.join(tokens2);
		return s1.compareTo(s2);
	}

	protected List<String> update(List<String> tokens) {
		if (tokens.get(0).startsWith("env")) {
			String token = tokens.get(0);
			Integer integer = parseInt(token.substring(3));
			String padded = "env" + leftPad(integer.toString(), 10, "0");
			tokens.set(0, padded);
		}
		Collections.reverse(tokens);
		return tokens;
	}

}
