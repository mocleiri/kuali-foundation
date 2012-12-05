package org.kuali.common.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlContext;

public interface JdbcService {

	JdbcMetaData getJdbcMetaData(DataSource dataSource);

	SqlMetaData executeSqlString(JdbcContext context, String sql);

	SqlMetaDataList executeSqlStrings(JdbcContext context, List<String> sql);

	SqlMetaData executeSql(JdbcContext context, String location);

	SqlMetaData executeSql(JdbcContext context, String location, String encoding);

	SqlMetaDataList executeSql(JdbcContext context, List<String> locations);

	SqlMetaDataList executeSql(JdbcContext context, List<String> locations, String encoding);

	SqlMetaData getMetaDataFromString(SqlContext context, String sql);

	SqlMetaDataList getMetaDataFromStrings(SqlContext context, List<String> sql);

	SqlMetaData getMetaData(SqlContext context, String location);

	SqlMetaData getMetaData(SqlContext context, String location, String encoding);

	SqlMetaDataList getMetaData(SqlContext context, List<String> locations);

	SqlMetaDataList getMetaData(SqlContext context, List<String> locations, String encoding);

}
