package org.kuali.common.jdbc.model;

public final class DbaSql {

	public DbaSql(String before, String after) {
		this.before = before;
		this.after = after;
	}

	private final String before;
	private final String after;

	public String getBefore() {
		return before;
	}

	public String getAfter() {
		return after;
	}

}
