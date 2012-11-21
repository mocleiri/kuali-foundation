package org.kuali.common.jdbc;

public interface JdbcService {

	void executeSql(JdbcContext context);

	long getSqlStatementCount(SqlContext context);

}
