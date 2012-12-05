package org.kuali.common.jdbc;

public class SqlSource {

	String encoding;
	String string;
	SqlStringType type;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public SqlStringType getType() {
		return type;
	}

	public void setType(SqlStringType type) {
		this.type = type;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
