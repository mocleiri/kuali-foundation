package org.kuali.common.jdbc.context;

public class SqlContext {

	String type;
	SqlMode mode;

	public SqlContext() {
		this(null, null);
	}

	public SqlContext(String type, SqlMode mode) {
		super();
		this.type = type;
		this.mode = mode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SqlMode getMode() {
		return mode;
	}

	public void setMode(SqlMode mode) {
		this.mode = mode;
	}

}
