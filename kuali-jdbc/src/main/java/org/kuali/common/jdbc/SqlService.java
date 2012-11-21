package org.kuali.common.jdbc;

import java.util.List;

public interface SqlService {

	long executeSql(JdbcContext context, List<SqlSource> sources);

	long getSqlStatementCount(SqlContext context, List<SqlSource> sources);

	List<String> getSqlStatements(SqlContext context, List<SqlSource> sources);

}
