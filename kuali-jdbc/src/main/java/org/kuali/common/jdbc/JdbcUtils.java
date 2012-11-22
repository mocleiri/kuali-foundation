package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;

public class JdbcUtils {

	private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	public static final BufferedReader getBufferedReader(SqlSource source) {
		String encoding = source.getEncoding();
		switch (source.getType()) {
		case FILE:
			Assert.notNull(source.getFile());
			String path = LocationUtils.getCanonicalPath(source.getFile());
			logger.debug("Opening {}", path);
			return LocationUtils.getBufferedReader(source.getFile(), encoding);
		case LOCATION:
			Assert.notNull(source.getLocation());
			logger.debug("Opening {}", source.getLocation());
			return LocationUtils.getBufferedReader(source.getLocation(), encoding);
		case STRING:
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

}
