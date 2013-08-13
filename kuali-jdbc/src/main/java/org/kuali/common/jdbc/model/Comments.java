package org.kuali.common.jdbc.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

public final class Comments {

	public static final boolean DEFAULT_IGNORE = true;
	public static final List<String> DEFAULT_TOKENS = Collections.unmodifiableList(Arrays.asList("#", "--"));
	public static final Comments DEFAULT_COMMENTS = new Comments();

	public Comments() {
		this(DEFAULT_IGNORE, DEFAULT_TOKENS);
	}

	public Comments(boolean ignore) {
		this(ignore, DEFAULT_TOKENS);
	}

	public Comments(List<String> tokens) {
		this(DEFAULT_IGNORE, tokens);
	}

	public Comments(boolean ignore, List<String> tokens) {
		Assert.noNulls(tokens);
		this.ignore = ignore;
		this.tokens = CollectionUtils.unmodifiableCopy(tokens);
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
