package org.kuali.common.jdbc;

public interface SqlService {

	void executeSql(JdbcContext context);

	long getSqlStatementCount(SqlContext context);

}
