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
			throw new IllegalArgumentException(source.getType() + " is unknown");
		}
	}

	public static final void closeQuietly(DataSource dataSource, Connection conn, Statement statement) {
		closeQuietly(statement);
		closeQuietly(conn, dataSource);
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

	public static final void closeQuietly(Connection conn, DataSource dataSource) {
		if (conn == null) {
			return;
		}
		try {
			DataSourceUtils.doReleaseConnection(conn, dataSource);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public static final List<SqlSource> getLocationSqlSources(List<String> locations) {
		List<SqlSource> sources = new ArrayList<SqlSource>();
		for (String location : locations) {
			SqlSource source = new SqlSource();
			source.setString(location);
			source.setType(SqlStringType.LOCATION);
			sources.add(source);
		}
		return sources;
	}

	public static final List<SqlSource> getStringSqlSources(List<String> sql) {
		List<SqlSource> sources = new ArrayList<SqlSource>();
		for (String s : sql) {
			SqlSource source = new SqlSource();
			source.setString(s);
			source.setType(SqlStringType.SQL);
			sources.add(source);
		}
		return sources;
	}

}
