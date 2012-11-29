package org.kuali.common.jdbc.context;

import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.SqlReader;

public class SqlContext {

	SqlReader reader = new DefaultSqlReader();
	boolean flatten = true;

	public SqlReader getReader() {
		return reader;
	}

	public void setReader(SqlReader reader) {
		this.reader = reader;
	}

	public boolean isFlatten() {
		return flatten;
	}

	public void setFlatten(boolean flatten) {
		this.flatten = flatten;
	}
}
