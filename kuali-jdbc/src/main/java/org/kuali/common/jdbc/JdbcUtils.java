package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;

public class JdbcUtils {

	private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	public static final BufferedReader getBufferedReader(SqlSource source) {
		Assert.notNull(source.getString());
		String encoding = source.getEncoding();
		switch (source.getType()) {
		case LOCATION:
			logger.debug("Opening {}", source.getString());
			return LocationUtils.getBufferedReader(source.getString(), encoding);
		case SQL:
			Assert.notNull(source.getString());
			return LocationUtils.getBufferedReaderFromString(source.getString(), encoding);
		default:
			throw new IllegalArgumentException("SQL source type '" + source.getType() + "' is unknown");
		}
	}

	public static final void closeQuietly(DataSource dataSource, Connection conn, Statement statement) {
		closeQuietly(statement);
		closeQuietly(dataSource, conn);
	}

	public static final void closeQuietly(Statement statement) {
		if (statement == null) {
			return;
		}
		try {
			statement.close();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public static final void closeQuietly(DataSource dataSource, Connection conn) {
		if (conn == null && dataSource == null) {
			return;
		}
		Assert.notNull(dataSource, "dataSource is null but conn is not");
		try {
			DataSourceUtils.doReleaseConnection(conn, dataSource);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public static final List<SqlSource> getSqlSources(List<String> locations, List<String> encodings) {
		return getSqlSources(locations, encodings, SqlStringType.LOCATION);
	}

	public static final List<SqlSource> getSqlSources(List<String> strings, List<String> encodings, SqlStringType sqlStringType) {
		Assert.isTrue(encodings == null || strings.size() == encodings.size());
		List<SqlSource> sources = new ArrayList<SqlSource>();
		for (int i = 0; i < strings.size(); i++) {
			String string = strings.get(i);
			String encoding = encodings == null ? null : encodings.get(i);
			SqlSource source = new SqlSource();
			source.setString(string);
			source.setEncoding(encoding);
			source.setType(sqlStringType);
			sources.add(source);
		}
		return sources;
	}

	public static final List<SqlSource> getSqlSourcesFromStrings(List<String> sql, List<String> encodings) {
		return getSqlSources(sql, encodings, SqlStringType.SQL);
	}

}
