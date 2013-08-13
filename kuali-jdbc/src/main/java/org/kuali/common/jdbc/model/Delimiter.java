package org.kuali.common.jdbc.model;

import org.kuali.common.util.Assert;

public final class Delimiter {

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
