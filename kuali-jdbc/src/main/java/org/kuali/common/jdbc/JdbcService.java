package org.kuali.common.jdbc;

import java.util.List;

import javax.sql.DataSource;

public interface JdbcService {

	JdbcMetaData getJdbcMetaData(DataSource dataSource);

	SqlMetaData executeSqlString(JdbcContext context, String sql);

	SqlMetaData executeSqlString(JdbcContext context, String sql, String encoding);

	SqlMetaDataList executeSqlStrings(JdbcContext context, List<String> sql);

	SqlMetaDataList executeSqlStrings(JdbcContext context, List<String> sql, String encoding);

	SqlMetaDataList executeSqlStrings(JdbcContext context, List<String> sql, List<String> encodings);

	SqlMetaData getMetaDataFromString(SqlContext context, String sql);

	SqlMetaData getMetaDataFromString(SqlContext context, String sql, String encoding);

	SqlMetaDataList getMetaDataFromStrings(SqlContext context, List<String> sql);

	SqlMetaDataList getMetaDataFromStrings(SqlContext context, List<String> sql, String encoding);

	SqlMetaDataList getMetaDataFromStrings(SqlContext context, List<String> sql, List<String> encodings);

	SqlMetaData executeSql(JdbcContext context, String location);

	SqlMetaData executeSql(JdbcContext context, String location, String encoding);

	SqlMetaDataList executeSql(JdbcContext context, List<String> locations);

	SqlMetaDataList executeSql(JdbcContext context, List<String> locations, String encoding);

	SqlMetaDataList executeSql(JdbcContext context, List<String> locations, List<String> encodings);

	SqlMetaData getMetaData(SqlContext context, String location);

	SqlMetaData getMetaData(SqlContext context, String location, String encoding);

	SqlMetaDataList getMetaData(SqlContext context, List<String> locations);

	SqlMetaDataList getMetaData(SqlContext context, List<String> locations, String encoding);

	SqlMetaDataList getMetaData(SqlContext context, List<String> locations, List<String> encodings);
}
