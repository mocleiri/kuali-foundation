package org.kuali.common.jdbc;

import java.util.List;

public interface SqlService {

	SqlMetadata executeString(JdbcContext context, String sql);

	SqlMetadata executeStrings(JdbcContext context, List<String> sql);

	SqlMetadata executeLocation(JdbcContext context, String location);

	SqlMetadata executeLocations(JdbcContext context, List<String> locations);

	SqlMetadata execute(JdbcContext context, List<SqlSource> sources);

	SqlMetadata execute(JdbcContext context, SqlSource source);

	SqlMetadata getMetadata(SqlContext context, List<SqlSource> sources);

	SqlMetadata getMetadata(SqlContext context, SqlSource source);

	List<String> getSqlStatements(SqlContext context, List<SqlSource> sources);

	List<String> getSqlStatements(SqlContext context, SqlSource source);

}
