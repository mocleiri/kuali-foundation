package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public long getSqlStatementCount(SqlContext context, List<SqlSource> sources) {
		long count = 0;
		for (SqlSource source : sources) {
			count += getSqlStatementCount(context, source, count);
		}
		return count;
	}

	@Override
	public List<String> getSqlStatements(SqlContext context, List<SqlSource> sources) {
		List<String> sql = new ArrayList<String>();
		for (SqlSource source : sources) {
			sql.addAll(getSqlStatements(context, source));
		}
		return sql;
	}

	protected List<String> getSqlStatements(SqlContext context, SqlSource source) {
		List<String> list = new ArrayList<String>();
		BufferedReader in = null;
		try {
			in = getBufferedReader(source);
			String sql = context.getReader().getSqlStatement(in);
			while (sql != null) {
				list.add(sql);
				sql = context.getReader().getSqlStatement(in);
			}
			return list;
		} catch (IOException e) {
			throw new JdbcException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void show(SqlContext context, String sql, long count) {
		if (context.isShow()) {
			String show = context.isFlatten() ? Str.flatten(sql) : sql;
			logger.info("{} - [{}]", count, show);
		}
	}

	protected long getSqlStatementCount(SqlContext context, SqlSource source, long runningCount) {
		long count = 0;
		BufferedReader in = null;
		try {
			in = getBufferedReader(source);
			String sql = context.getReader().getSqlStatement(in);
			while (sql != null) {
				count++;
				show(context, sql, runningCount + count);
				sql = context.getReader().getSqlStatement(in);
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
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	protected void closeQuietly(Connection conn, DataSource dataSource) {
		if (conn == null) {
			return;
		}
		try {
			DataSourceUtils.doReleaseConnection(conn, dataSource);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	@Override
	public long executeSql(JdbcContext context, List<SqlSource> sources) {
		Connection conn = null;
		Statement statement = null;
		long count = 0;
		try {
			conn = DataSourceUtils.doGetConnection(context.getDataSource());
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			for (SqlSource source : sources) {
				SqlExecutionContext sec = getSqlExecutionContext(context, conn, statement, source, count);
				count += executeSqlFromSource(sec);
				afterExecuteSqlFromSource(sec);
			}
			afterExecuteSql(context, conn);
			return count;
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			closeQuietly(context.getDataSource(), conn, statement);
		}
	}

	protected long executeSqlFromSource(SqlExecutionContext context) {
		int count = 0;
		BufferedReader in = null;
		try {
			in = getBufferedReader(context.getSource());
			SqlReader reader = context.getJdbcContext().getReader();
			String sql = reader.getSqlStatement(in);
			while (sql != null) {
				count++;
				show(context.getJdbcContext(), sql, context.getRunningCount() + count);
				executeSqlStatement(context.getStatement(), sql);
				afterExecuteSqlStatement(context);
				sql = reader.getSqlStatement(in);
			}
			return count;
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void executeSqlStatement(Statement statement, String sql) throws SQLException {
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			throw new SQLException("Error executing SQL [" + Str.flatten(sql) + "]", e);
		}
	}

	protected void afterExecuteSql(JdbcContext context, Connection conn) throws SQLException {
		if (CommitMode.PER_EXECUTION.equals(context.getCommitMode())) {
			conn.commit();
		}
	}

	protected void afterExecuteSqlFromSource(SqlExecutionContext context) throws SQLException {
		if (CommitMode.PER_SOURCE.equals(context.getJdbcContext().getCommitMode())) {
			context.getConnection().commit();
		}
	}

	protected void afterExecuteSqlStatement(SqlExecutionContext context) throws SQLException {
		if (CommitMode.PER_STATEMENT.equals(context.getJdbcContext().getCommitMode())) {
			context.getConnection().commit();
		}
	}

	protected SqlExecutionContext getSqlExecutionContext(JdbcContext context, Connection conn, Statement stmt, SqlSource source, long runningCount) {
		SqlExecutionContext sec = new SqlExecutionContext();
		sec.setJdbcContext(context);
		sec.setConnection(conn);
		sec.setStatement(stmt);
		sec.setSource(source);
		sec.setRunningCount(runningCount);
		return sec;
	}

}
