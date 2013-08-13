package org.kuali.common.jdbc.model;

import java.util.List;

import org.kuali.common.util.Assert;

public class Comments {

	public Comments(boolean ignore, List<String> tokens) {
		Assert.noNulls(tokens);
		this.ignore = ignore;
		this.tokens = tokens;
	}

	private final boolean ignore;
	private final List<String> tokens;

	public boolean isIgnore() {
		return ignore;
	}

	public List<String> getTokens() {
		return tokens;
	}

}
