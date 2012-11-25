package org.kuali.common.jdbc;

public class SqlMetaData {

	long count;
	long executionTime;
	SqlReader reader;
	SqlSource source;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public SqlReader getReader() {
		return reader;
	}

	public void setReader(SqlReader reader) {
		this.reader = reader;
	}

	public SqlSource getSource() {
		return source;
	}

	public void setSource(SqlSource source) {
		this.source = source;
	}

}
