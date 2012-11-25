package org.kuali.common.jdbc;

import java.util.List;

import javax.sql.DataSource;

public interface JdbcService {

	JdbcMetaData getJdbcMetaData(DataSource dataSource);

	SqlMetaData executeSqlString(JdbcContext context, String sql);

	SqlMetaDataList executeSqlStrings(JdbcContext context, List<String> sql);

	SqlMetaData executeSql(JdbcContext context, CharSequence location);

	SqlMetaData executeSql(JdbcContext context, CharSequence location, CharSequence encoding);

	SqlMetaDataList executeSql(JdbcContext context, List<String> locations);

	SqlMetaDataList executeSql(JdbcContext context, List<String> locations, CharSequence encoding);

	SqlMetaData getMetaDataFromString(SqlContext context, String sql);

	SqlMetaDataList getMetaDataFromStrings(SqlContext context, List<String> sql);

	SqlMetaData getMetaData(SqlContext context, CharSequence location);

	SqlMetaData getMetaData(SqlContext context, CharSequence location, CharSequence encoding);

	SqlMetaDataList getMetaData(SqlContext context, List<String> locations);

	SqlMetaDataList getMetaData(SqlContext context, List<String> locations, CharSequence encoding);

}
