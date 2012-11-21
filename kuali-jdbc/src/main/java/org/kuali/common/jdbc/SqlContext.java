package org.kuali.common.jdbc;

public class SqlContext {

	SqlReader reader;
	boolean show;
	SqlSource source;

	public SqlReader getReader() {
		return reader;
	}

	public void setReader(SqlReader reader) {
		this.reader = reader;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public SqlSource getSource() {
		return source;
	}

	public void setSource(SqlSource source) {
		this.source = source;
	}

}
