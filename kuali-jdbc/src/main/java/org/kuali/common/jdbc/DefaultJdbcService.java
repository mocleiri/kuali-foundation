package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.SimpleFormatter;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;

public class DefaultJdbcService implements JdbcService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcService.class);
	SimpleFormatter formatter = new SimpleFormatter();

	@Override
	public JdbcMetaData getJdbcMetaData(DataSource dataSource) {
		Connection conn = null;
		try {
			conn = DataSourceUtils.doGetConnection(dataSource);
			DatabaseMetaData dbmd = conn.getMetaData();
			return getJdbcMetaData(dbmd);
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			JdbcUtils.closeQuietly(dataSource, conn);
		}
	}

	protected JdbcMetaData getJdbcMetaData(DatabaseMetaData dbmd) throws SQLException {
		JdbcMetaData md = new JdbcMetaData();
		md.setDatabaseProductName(dbmd.getDatabaseProductName());
		md.setDatabaseProductVersion(dbmd.getDatabaseProductVersion());
		md.setDriverName(dbmd.getDriverName());
		md.setDriverVersion(dbmd.getDriverVersion());
		md.setUrl(dbmd.getURL());
		md.setUsername(dbmd.getUserName());
		return md;
	}

	@Override
	public SqlMetaData executeSql(JdbcContext context, String location) {
		return executeSql(context, location, null);
	}

	@Override
	public SqlMetaData executeSql(JdbcContext context, String location, String encoding) {
		return executeSql(context, Collections.singletonList(location), encoding).get(1);
	}

	@Override
	public SqlMetaData executeSqlString(JdbcContext context, String sql) {
		return executeSqlString(context, sql, null);
	}

	@Override
	public SqlMetaData executeSqlString(JdbcContext context, String sql, String encoding) {
		return executeSqlStrings(context, Collections.singletonList(sql), encoding).get(1);
	}

	protected SqlMetaDataList executeSources(JdbcContext context, List<SqlSource> sources) {
		Connection conn = null;
		Statement statement = null;
		long count = 0;
		long start = System.currentTimeMillis();
		try {
			conn = DataSourceUtils.doGetConnection(context.getDataSource());
			boolean originalAutoCommitSetting = conn.getAutoCommit();
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			SqlMetaDataList smdl = new SqlMetaDataList();
			for (SqlSource source : sources) {
				SqlExecutionContext sec = getSqlExecutionContext(context, conn, statement, source, count);
				SqlMetaData smd = executeSqlFromSource(sec);
				smdl.add(smd);
				count += smd.getCount();
				afterExecuteSqlFromSource(sec);
			}
			afterExecuteAllSql(context, conn);
			conn.setAutoCommit(originalAutoCommitSetting);
			smdl.setExecutionTime(System.currentTimeMillis() - start);
			smdl.setCount(count);
			return smdl;
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), conn, statement);
		}
	}

	@Override
	public SqlMetaData getMetaData(SqlContext context, String location) {
		return getMetaData(context, location, null);
	}

	@Override
	public SqlMetaDataList getMetaData(SqlContext context, List<String> locations) {
		// TODO
		return null;
	}

	@Override
	public SqlMetaData getMetaDataFromString(SqlContext context, String sql) {
		return getMetaDataFromString(context, sql, null);
	}

	@Override
	public SqlMetaData getMetaDataFromString(SqlContext context, String sql, String encoding) {
		return getMetaDataFromStrings(context, Collections.singletonList(sql), encoding).get(1);
	}

	@Override
	public SqlMetaDataList getMetaDataFromStrings(SqlContext context, List<String> sql) {
		// TODO
		return null;
	}

	protected SqlMetaData getSqlMetaData(SqlContext context, SqlSource source) {
		return getSqlMetaDataList(context, Collections.singletonList(source)).get(1);
	}

	protected SqlMetaDataList getSqlMetaDataList(SqlContext context, List<SqlSource> sources) {
		SqlMetaDataList smdl = new SqlMetaDataList();
		long count = 0;
		for (SqlSource source : sources) {
			logSource("Examining", source);
			SqlMetaData smd = getSqlMetaData(context, source, count);
			count += smd.getCount();
			smdl.add(smd);
		}
		smdl.setCount(count);
		return smdl;
	}

	protected SqlMetaData getSqlMetaData(SqlContext context, SqlSource source, long runningCount) {
		long count = 0;
		BufferedReader in = null;
		try {
			in = JdbcUtils.getBufferedReader(source);
			String sql = context.getReader().getSqlStatement(in);
			while (sql != null) {
				logger.info("{} - {}", ++count, Str.flatten(sql));
				sql = context.getReader().getSqlStatement(in);
			}
			SqlMetaData metadata = new SqlMetaData();
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

	public List<String> getSqlStatements(SqlContext context, List<SqlSource> sources) {
		List<String> sql = new ArrayList<String>();
		for (SqlSource source : sources) {
			sql.addAll(getSqlStatements(context, source));
		}
		return sql;
	}

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

	protected void logSource(String prefix, SqlSource source) {
		SqlStringType type = source.getType();
		String encoding = source.getEncoding();
		String string = source.getString();
		switch (type) {
		case SQL:
			logger.info(prefix + " SQL [{}, encoding={}]", formatter.getSize(string.length()), encoding);
			return;
		case LOCATION:
			logger.info(prefix + " [{}] encoding={}", string, encoding);
			return;
		default:
			throw new IllegalArgumentException("SQL string type '" + type + "' is unknown");
		}
	}

	protected SqlMetaData executeSqlFromSource(SqlExecutionContext context) {
		logSource("Executing", context.getSource());
		long count = 0;
		BufferedReader in = null;
		try {
			in = JdbcUtils.getBufferedReader(context.getSource());
			SqlReader reader = context.getJdbcContext().getReader();
			long start = System.currentTimeMillis();
			String sql = reader.getSqlStatement(in);
			while (sql != null) {
				logger.info("{} - [{}]", ++count, Str.flatten(sql));
				executeSqlStatement(context, sql);
				afterExecuteSqlStatement(context);
				sql = reader.getSqlStatement(in);
			}
			SqlMetaData ssm = new SqlMetaData();
			ssm.setExecutionTime(System.currentTimeMillis() - start);
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

	protected void afterExecuteAllSql(JdbcContext context, Connection conn) throws SQLException {
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

	@Override
	public SqlMetaDataList executeSqlStrings(JdbcContext context, List<String> sql) {
		return executeSqlStrings(context, sql, new ArrayList<String>());
	}

	@Override
	public SqlMetaDataList getMetaDataFromStrings(SqlContext context, List<String> sql, String encoding) {
		List<String> encodings = CollectionUtils.getPreFilledList(sql.size(), encoding);
		return getMetaDataFromStrings(context, sql, encodings);
	}

	@Override
	public SqlMetaData getMetaData(SqlContext context, String location, String encoding) {
		return getMetaData(context, Collections.singletonList(location), encoding).get(1);
	}

	@Override
	public SqlMetaDataList getMetaData(SqlContext context, List<String> locations, String encoding) {
		List<String> encodings = CollectionUtils.getPreFilledList(locations.size(), encoding);
		return getMetaData(context, locations, encodings);
	}

	@Override
	public SqlMetaDataList executeSql(JdbcContext context, List<String> locations) {
		return executeSql(context, locations, Collections.<String> emptyList());
	}

	@Override
	public SqlMetaDataList executeSql(JdbcContext context, List<String> locations, String encoding) {
		List<String> encodings = CollectionUtils.getPreFilledList(locations.size(), encoding);
		return executeSql(context, locations, encodings);
	}

	@Override
	public SqlMetaDataList executeSqlStrings(JdbcContext context, List<String> sql, String encoding) {
		List<String> encodings = CollectionUtils.getPreFilledList(sql.size(), encoding);
		return executeSqlStrings(context, sql, encodings);
	}

	@Override
	public SqlMetaDataList getMetaData(SqlContext context, List<String> locations, List<String> encodings) {
		encodings = CollectionUtils.toNullIfEmpty(encodings);
		Assert.isTrue(encodings == null || locations.size() == encodings.size());
		List<SqlSource> sources = JdbcUtils.getSqlSources(locations, encodings);
		return getSqlMetaDataList(context, sources);
	}

	@Override
	public SqlMetaDataList getMetaDataFromStrings(SqlContext context, List<String> sql, List<String> encodings) {
		encodings = CollectionUtils.toNullIfEmpty(encodings);
		Assert.isTrue(encodings == null || sql.size() == encodings.size());
		List<SqlSource> sources = JdbcUtils.getSqlSourcesFromStrings(sql, encodings);
		return getSqlMetaDataList(context, sources);
	}

	@Override
	public SqlMetaDataList executeSql(JdbcContext context, List<String> locations, List<String> encodings) {
		encodings = CollectionUtils.toNullIfEmpty(encodings);
		Assert.isTrue(encodings == null || encodings.size() == locations.size());
		List<SqlSource> sources = JdbcUtils.getSqlSources(locations, encodings);
		return executeSources(context, sources);
	}

	@Override
	public SqlMetaDataList executeSqlStrings(JdbcContext context, List<String> sql, List<String> encodings) {
		encodings = CollectionUtils.toNullIfEmpty(encodings);
		Assert.isTrue(encodings == null || encodings.size() == sql.size());
		List<SqlSource> sources = JdbcUtils.getSqlSourcesFromStrings(sql, encodings);
		return executeSources(context, sources);
	}

}
