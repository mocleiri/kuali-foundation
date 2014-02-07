package org.kuali.common.devops.logic.function;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public final class ReverseDnsNameFunction implements Function<String, String> {

	private static final Splitter SPLITTER = Splitter.on('.');
	private static final Joiner JOINER = Joiner.on('.');

	@Override
	public String apply(String dnsName) {
		checkNotBlank(dnsName, "dnsName");
		return JOINER.join(Lists.reverse(SPLITTER.splitToList(dnsName)));
	}

}
