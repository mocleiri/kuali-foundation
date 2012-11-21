package org.kuali.common.jdbc;

import javax.sql.DataSource;

public class SqlExecutionContext {

	DataSource dataSource;
	SqlReader sqlReader;
	boolean autoCommit;
	boolean showSql;
	SqlSource sqlSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public SqlReader getSqlReader() {
		return sqlReader;
	}

	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}

	public boolean isAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

	public boolean isShowSql() {
		return showSql;
	}

	public void setShowSql(boolean showSql) {
		this.showSql = showSql;
	}

}
