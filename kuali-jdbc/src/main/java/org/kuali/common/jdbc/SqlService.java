package org.kuali.common.jdbc;

import java.util.List;

public interface SqlService {

	SqlMetadata executeSql(JdbcContext context, List<SqlSource> sources);

	SqlMetadata executeSql(JdbcContext context, SqlSource source);

	SqlMetadata getSqlMetadata(SqlContext context, List<SqlSource> sources);

	SqlMetadata getSqlMetadata(SqlContext context, SqlSource source);

	List<String> getSqlStatements(SqlContext context, List<SqlSource> sources);

	List<String> getSqlStatements(SqlContext context, SqlSource source);

}
