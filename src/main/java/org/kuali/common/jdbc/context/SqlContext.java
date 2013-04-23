package org.kuali.common.jdbc.context;

public class SqlContext {

	String group;
	SqlMode mode;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public SqlMode getMode() {
		return mode;
	}

	public void setMode(SqlMode mode) {
		this.mode = mode;
	}

}
