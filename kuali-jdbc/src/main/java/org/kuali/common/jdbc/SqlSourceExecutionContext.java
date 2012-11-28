package org.kuali.common.jdbc;

import java.sql.Connection;
import java.sql.Statement;

public class SqlSourceExecutionContext {

	JdbcContext jdbcContext;
	Connection connection;
	Statement statement;
	SqlSource source;
	long runningCount;
	int sourceIndex;
	int sourcesCount;

	public JdbcContext getJdbcContext() {
		return jdbcContext;
	}

	public void setJdbcContext(JdbcContext context) {
		this.jdbcContext = context;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public SqlSource getSource() {
		return source;
	}

	public void setSource(SqlSource source) {
		this.source = source;
	}

	public long getRunningCount() {
		return runningCount;
	}

	public void setRunningCount(long runningCount) {
		this.runningCount = runningCount;
	}

	public int getSourceIndex() {
		return sourceIndex;
	}

	public void setSourceIndex(int index) {
		this.sourceIndex = index;
	}

	public int getSourcesCount() {
		return sourcesCount;
	}

	public void setSourcesCount(int sourcesCount) {
		this.sourcesCount = sourcesCount;
	}

}
