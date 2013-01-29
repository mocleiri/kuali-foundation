package org.kuali.common.jdbc;

import java.io.BufferedReader;

public class ParseContext {

	String sql;
	SqlReader reader;
	BufferedReader input;
	int maxLength = 1024 * 100;
	int maxCount = 100;
	String open = "INSERT ALL" + ParseOracleSqlTest.LF + ParseOracleSqlTest.LF;
	String close = "SELECT * FROM DUAL" + ParseOracleSqlTest.LF + ParseOracleSqlTest.DELIMITER + ParseOracleSqlTest.LF;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public SqlReader getReader() {
		return reader;
	}

	public void setReader(SqlReader reader) {
		this.reader = reader;
	}

	public BufferedReader getInput() {
		return input;
	}

	public void setInput(BufferedReader input) {
		this.input = input;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

}
