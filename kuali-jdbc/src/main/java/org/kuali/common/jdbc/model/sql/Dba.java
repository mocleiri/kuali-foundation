package org.kuali.common.jdbc.model.sql;

public final class Dba {

	public Dba(String before, String after) {
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
