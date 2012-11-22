package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DefaultSqlService implements SqlService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSqlService.class);

	@Override
	public SqlMetadata executeSql(JdbcContext context, SqlSource source) {
		return executeSql(context, Collections.singletonList(source));
	}

	@Override
	public SqlMetadata executeSql(JdbcContext context, List<SqlSource> sources) {
		Connection conn = null;
		Statement statement = null;
		long count = 0;
		try {
			conn = DataSourceUtils.doGetConnection(context.getDataSource());
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			List<SqlSourceMetadata> sourceMetadata = new ArrayList<SqlSourceMetadata>();
			for (SqlSource source : sources) {
				SqlExecutionContext sec = getSqlExecutionContext(context, conn, statement, source, count);
				SqlSourceMetadata ssm = executeSqlFromSource(sec);
				sourceMetadata.add(ssm);
				count += ssm.getCount();
				afterExecuteSqlFromSource(sec);
			}
			afterExecuteSql(context, conn);
			SqlMetadata metadata = new SqlMetadata();
			metadata.setCount(count);
			metadata.setSourceMetadata(sourceMetadata);
			return metadata;
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), conn, statement);
		}
	}

	@Override
	public SqlMetadata getSqlMetadata(SqlContext context, SqlSource source) {
		return getSqlMetadata(context, Collections.singletonList(source));
	}

	@Override
	public SqlMetadata getSqlMetadata(SqlContext context, List<SqlSource> sources) {
		List<SqlSourceMetadata> list = new ArrayList<SqlSourceMetadata>();
		long count = 0;
		for (SqlSource source : sources) {
			SqlSourceMetadata ssm = getSqlSourceMetadata(context, source, count);
			count += ssm.getCount();
			list.add(ssm);
		}
		SqlMetadata metadata = new SqlMetadata();
		metadata.setSourceMetadata(list);
		metadata.setCount(count);
		return metadata;
	}

	protected SqlSourceMetadata getSqlSourceMetadata(SqlContext context, SqlSource source, long runningCount) {
		long count = 0;
		BufferedReader in = null;
		try {
			in = JdbcUtils.getBufferedReader(source);
			String sql = context.getReader().getSqlStatement(in);
			while (sql != null) {
				count++;
				showSql(context, sql, runningCount + count);
				sql = context.getReader().getSqlStatement(in);
			}
			SqlSourceMetadata metadata = new SqlSourceMetadata();
			metadata.setCount(count);
			metadata.setSource(source);
			metadata.setReader(context.getReader());
			return metadata;
		} catch (IOException e) {
			throw new JdbcException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public List<String> getSqlStatements(SqlContext context, List<SqlSource> sources) {
		List<String> sql = new ArrayList<String>();
		for (SqlSource source : sources) {
			sql.addAll(getSqlStatements(context, source));
		}
		return sql;
	}

	@Override
	public List<String> getSqlStatements(SqlContext context, SqlSource source) {
		List<String> list = new ArrayList<String>();
		BufferedReader in = null;
		try {
			in = JdbcUtils.getBufferedReader(source);
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

	protected void showSql(SqlContext context, String sql, long count) {
		if (context.isShow()) {
			String log = context.isFlatten() ? Str.flatten(sql) : sql;
			logger.info("{} - SQL - [{}]", count, log);
		} else {
			String log = context.isFlatten() ? Str.flatten(sql) : sql;
			logger.debug("{} - SQL - [{}]", count, log);
		}
	}

	protected void logSource(SqlExecutionContext context) {
		SqlSourceType type = context.getSource().getType();
		switch (type) {
		case STRING:
			return;
		case FILE:
			logger.info("Executing {}", LocationUtils.getCanonicalPath(context.getSource().getFile()));
			return;
		case LOCATION:
			logger.info("Executing {}", context.getSource().getLocation());
			return;
		default:
			throw new IllegalArgumentException(type + " is unknown");
		}
	}

	protected SqlSourceMetadata executeSqlFromSource(SqlExecutionContext context) {
		logSource(context);
		int count = 0;
		BufferedReader in = null;
		try {
			in = JdbcUtils.getBufferedReader(context.getSource());
			SqlReader reader = context.getJdbcContext().getReader();
			String sql = reader.getSqlStatement(in);
			while (sql != null) {
				count++;
				showSql(context.getJdbcContext(), sql, context.getRunningCount() + count);
				executeSqlStatement(context, sql);
				afterExecuteSqlStatement(context);
				sql = reader.getSqlStatement(in);
			}
			SqlSourceMetadata ssm = new SqlSourceMetadata();
			ssm.setCount(count);
			ssm.setReader(context.getJdbcContext().getReader());
			ssm.setSource(context.getSource());
			return ssm;
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void executeSqlStatement(SqlExecutionContext context, String sql) throws SQLException {
		try {
			context.getStatement().execute(sql);
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
