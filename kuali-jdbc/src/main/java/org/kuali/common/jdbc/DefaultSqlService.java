package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;

public class DefaultSqlService implements SqlService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSqlService.class);

	@Override
	public long getSqlStatementCount(SqlContext context) {
		long count = 0;
		for (SqlSource source : context.getSources()) {
			count += getSqlStatementCount(context.getReader(), source);
		}
		return count;
	}

	protected long getSqlStatementCount(SqlReader reader, SqlSource source) {
		long count = 0;
		BufferedReader in = null;
		try {
			in = getBufferedReader(source);
			while (reader.getSqlStatement(in) != null) {
				count++;
			}
			return count;
		} catch (IOException e) {
			throw new JdbcException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected BufferedReader getBufferedReader(SqlSource source) {
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

	protected int execute(BufferedReader reader) {
		Connection conn = null;
		Statement statement = null;
		int count = 0;
		try {
			conn = null; // DataSourceUtils.doGetConnection(dataSource);
			// conn.setAutoCommit(autoCommit);
			statement = conn.createStatement();
			String sql = null;// sqlReader.getSqlStatement(reader);
			while (sql != null) {
				count++;
				// if (showSql) {
				// logger.info("{} - [{}]", count, Str.flatten(sql));
				// }
				executeSQL(statement, sql);
				sql = null;// sqlReader.getSqlStatement(reader);
			}
			conn.commit();
			return count;
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			IOUtils.closeQuietly(reader);
			closeQuietly(null, conn, statement);
		}
	}

	protected void executeSQL(Statement statement, String sql) throws SQLException {
		try {
			statement.execute(sql);
		} catch (SQLException ignored) {
			; // Ignore
		}
	}

	protected void closeQuietly(DataSource dataSource, Connection conn, Statement statement) {
		closeQuietly(statement);
		closeQuietly(conn, dataSource);
	}

	protected void closeQuietly(Statement statement) {
		if (statement == null) {
			return;
		}
		try {
			statement.close();
		} catch (SQLException ignored) {
			; // Ignore
		}
	}

	protected void closeQuietly(Connection conn, DataSource dataSource) {
		if (conn == null) {
			return;
		}
		try {
			DataSourceUtils.doReleaseConnection(conn, dataSource);
		} catch (SQLException ignored) {
			; // Ignore
		}
	}

	@Override
	public void executeSql(JdbcContext context) {
		long count = 0;
		for (SqlSource source : context.getSources()) {
			count += executeSql(context, source);
		}
		logger.info("Executed {} sql statements", count);
	}

	protected long executeSql(JdbcContext context, SqlSource source) {
		Connection conn = null;
		Statement statement = null;
		int count = 0;
		BufferedReader in = null;
		try {
			in = getBufferedReader(source);
			conn = DataSourceUtils.doGetConnection(context.getDataSource());
			// conn.setAutoCommit(autoCommit);
			statement = conn.createStatement();
			String sql = context.getReader().getSqlStatement(in);
			while (sql != null) {
				count++;
				if (context.isShow()) {
					logger.info("{} - [{}]", count, Str.flatten(sql));
				}
				executeSQL(statement, sql);
				sql = context.getReader().getSqlStatement(in);
			}
			conn.commit();
			return count;
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			IOUtils.closeQuietly(in);
			closeQuietly(context.getDataSource(), conn, statement);
		}
	}

}
