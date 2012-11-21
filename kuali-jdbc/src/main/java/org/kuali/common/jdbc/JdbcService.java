package org.kuali.common.jdbc;

public interface JdbcService {

	void executeSql(SqlExecutionContext context);

	long getSqlStatementCount(SqlContext context);

}
