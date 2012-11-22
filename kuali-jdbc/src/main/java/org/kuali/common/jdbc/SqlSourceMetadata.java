package org.kuali.common.jdbc;

public class SqlSourceMetadata {

	long count;
	SqlReader reader;
	SqlSource source;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
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
