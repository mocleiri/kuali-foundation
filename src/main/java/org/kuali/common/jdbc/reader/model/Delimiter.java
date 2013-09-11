package org.kuali.common.jdbc.reader.model;

import org.kuali.common.util.Assert;

public final class Delimiter {

	public static final String DEFAULT_VALUE = "/";
	public static final Delimiter DEFAULT_DELIMITER = new Delimiter();

	public Delimiter() {
		this(DEFAULT_VALUE, DelimiterMode.DEFAULT_VALUE);
	}

	public Delimiter(String value) {
		this(value, DelimiterMode.DEFAULT_VALUE);
	}

	public Delimiter(DelimiterMode mode) {
		this(DEFAULT_VALUE, mode);
	}

	public Delimiter(String value, DelimiterMode mode) {
		Assert.noBlanks(value);
		Assert.noNulls(mode);
		this.value = value;
		this.mode = mode;
	}

	private final String value;
	private final DelimiterMode mode;

	public String getValue() {
		return value;
	}

	public DelimiterMode getMode() {
		return mode;
	}

}
