/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.ProgressContext;
import org.kuali.common.jdbc.context.SqlContext;
import org.kuali.common.jdbc.context.SqlSourceExecutionContext;
import org.kuali.common.util.SimpleFormatter;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DefaultJdbcService implements JdbcService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcService.class);
	protected SimpleFormatter formatter = new SimpleFormatter();

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

	@Override
	public SqlMetaData executeSql(JdbcContext context, String location) {
		return executeSql(context, location, null);
	}

	@Override
	public SqlMetaData executeSql(JdbcContext context, String location, String encoding) {
		return executeSql(context, Collections.singletonList(location), encoding).get(0);
	}

	@Override
	public SqlMetaData executeSqlString(JdbcContext context, String sql) {
		return executeSqlStrings(context, Collections.singletonList(sql)).get(0);
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
			logger.info("Executing SQL from {} sources", sources.size());
			for (int i = 0; i < sources.size(); i++) {
				SqlSource source = sources.get(i);
				SqlSourceExecutionContext sec = getSourceSqlExecutionContext(context, conn, statement, source, count, i, sources.size());
				SqlMetaData smd = executeSqlFromSource(sec);
				smdl.add(smd);
				count += smd.getCount();
				afterExecuteSqlFromSource(sec, smd);
			}
			conn.setAutoCommit(originalAutoCommitSetting);
			smdl.setExecutionTime(System.currentTimeMillis() - start);
			smdl.setCount(count);
			afterExecuteSources(context, conn);
			return smdl;
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), conn, statement);
		}
	}

	@Override
	public SqlMetaData getMetaData(SqlContext context, String location) {
		return getMetaData(context, Collections.singletonList(location), null).get(0);
	}

	@Override
	public SqlMetaDataList getMetaData(SqlContext context, List<String> locations) {
		return getMetaData(context, locations, null);
	}

	@Override
	public SqlMetaData getMetaDataFromString(SqlContext context, String sql) {
		return getMetaDataFromStrings(context, Collections.singletonList(sql)).get(0);
	}

	protected SqlMetaData getSqlMetaData(SqlContext context, SqlSource source) {
		return getSqlMetaDataList(context, Collections.singletonList(source)).get(0);
	}

	protected SqlMetaDataList getSqlMetaDataList(SqlContext context, List<SqlSource> sources) {
		SqlMetaDataList smdl = new SqlMetaDataList();
		long count = 0;
		for (int i = 0; i < sources.size(); i++) {
			SqlSource source = sources.get(i);
			// logSource("Examining", source, i, sources.size());
			SqlMetaData smd = getSqlMetaDataFromSource(context, source);
			count += smd.getCount();
			smdl.add(smd);
		}
		smdl.setCount(count);
		return smdl;
	}

	protected SqlMetaData getSqlMetaDataFromSource(SqlContext context, SqlSource source) {
		long count = 0;
		BufferedReader in = null;
		try {
			in = JdbcUtils.getBufferedReader(source);
			String sql = context.getReader().getSqlStatement(in);
			while (sql != null) {
				logger.debug("{} - {}", ++count, Str.flatten(sql));
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

	protected void logSource(String prefix, SqlSource source, int index, int size) {
		SqlStringType type = source.getType();
		String string = source.getString();
		switch (type) {
		case SQL:
			logger.info((index + 1) + " of " + size + " - " + prefix + " SQL [{} characters]", formatter.getCount(string.length()));
			return;
		case LOCATION:
			logger.info((index + 1) + " of " + size + " - " + prefix + " [{}]", string);
			return;
		default:
			throw new IllegalArgumentException("SQL string type '" + type + "' is unknown");
		}
	}

	protected ProgressContext getProgressContext(SqlSourceExecutionContext context) {
		ProgressContext pc = new ProgressContext();
		boolean showProgress = context.getJdbcContext().isShowProgress();
		pc.setShowProgress(showProgress);
		if (!showProgress) {
			return pc;
		}
		SqlMetaData metaData = getSqlMetaData(context.getJdbcContext(), context.getSource());
		int min = context.getJdbcContext().getShowProgressMin();
		int divisor = context.getJdbcContext().getShowProgressDivisor();
		long progress = Math.max(min, metaData.getCount() / divisor);
		pc.setMin(min);
		pc.setDivisor(divisor);
		pc.setTotalCount(metaData.getCount());
		pc.setProgress(progress);
		return pc;
	}

	protected void beforeExecuteSqlFromSource(SqlSourceExecutionContext context, ProgressContext pc) {
		int min = pc.getMin();
		long count = pc.getTotalCount();
		boolean showProgress = pc.isShowProgress() && count > min;
		if (showProgress) {
			logger.info("Executing {} SQL statements", formatter.getCount(pc.getTotalCount()));
		}
	}

	protected SqlMetaData executeSqlFromSource(SqlSourceExecutionContext context) {
		logSource("Executing", context.getSource(), context.getSourceIndex(), context.getSourcesCount());
		ProgressContext pc = getProgressContext(context);
		beforeExecuteSqlFromSource(context, pc);
		long count = 0;
		BufferedReader in = null;
		try {
			in = JdbcUtils.getBufferedReader(context.getSource());
			SqlReader reader = context.getJdbcContext().getReader();
			long start = System.currentTimeMillis();
			String sql = reader.getSqlStatement(in);
			while (sql != null) {
				logger.debug("{} - [{}]", ++count, Str.flatten(sql));
				executeSqlStatement(context, sql);
				afterExecuteSqlStatement(context, count, pc);
				sql = reader.getSqlStatement(in);
			}
			return getSqlMetaData(start, count, context);
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void executeSqlStatement(SqlSourceExecutionContext context, String sql) throws SQLException {
		try {
			context.getStatement().execute(sql);
		} catch (SQLException e) {
			throw new SQLException("Error executing SQL [" + Str.flatten(sql) + "]", e);
		}
	}

	protected void afterExecuteSources(JdbcContext context, Connection conn) throws SQLException {
		if (CommitMode.PER_EXECUTION.equals(context.getCommitMode())) {
			conn.commit();
		}
	}

	protected void afterExecuteSqlFromSource(SqlSourceExecutionContext context, SqlMetaData metaData) throws SQLException {
		if (CommitMode.PER_SOURCE.equals(context.getJdbcContext().getCommitMode())) {
			context.getConnection().commit();
		}
		JdbcContext jdbcContext = context.getJdbcContext();
		long count = metaData.getCount();
		int min = jdbcContext.getShowProgressMin();
		boolean showProgress = jdbcContext.isShowProgress() && count > min;
		if (showProgress) {
			String s = formatter.getCount(metaData.getCount());
			logger.info("Executed {} of {} SQL statements", s, s);
		}
	}

	protected void afterExecuteSqlStatement(SqlSourceExecutionContext context, long count, ProgressContext pc) throws SQLException {
		if (CommitMode.PER_STATEMENT.equals(context.getJdbcContext().getCommitMode())) {
			context.getConnection().commit();
		}
		if (pc.isShowProgress() && count % pc.getProgress() == 0) {
			String count1 = formatter.getCount(count);
			String count2 = formatter.getCount(pc.getTotalCount());
			logger.info("Executed {} of {} SQL statements", count1, count2);
		}
	}

	@Override
	public SqlMetaData getMetaData(SqlContext context, String location, String encoding) {
		return getMetaData(context, Collections.singletonList(location), encoding).get(0);
	}

	@Override
	public SqlMetaDataList executeSql(JdbcContext context, List<String> locations) {
		return executeSql(context, locations, null);
	}

	@Override
	public SqlMetaDataList getMetaData(SqlContext context, List<String> locations, String encoding) {
		List<SqlSource> sources = JdbcUtils.getSqlSources(locations, encoding);
		return getSqlMetaDataList(context, sources);
	}

	@Override
	public SqlMetaDataList getMetaDataFromStrings(SqlContext context, List<String> sql) {
		List<SqlSource> sources = JdbcUtils.getSqlSourcesFromStrings(sql);
		return getSqlMetaDataList(context, sources);
	}

	@Override
	public SqlMetaDataList executeSql(JdbcContext context, List<String> locations, String encoding) {
		List<SqlSource> sources = JdbcUtils.getSqlSources(locations, encoding);
		return executeSources(context, sources);
	}

	@Override
	public SqlMetaDataList executeSqlStrings(JdbcContext context, List<String> sql) {
		List<SqlSource> sources = JdbcUtils.getSqlSourcesFromStrings(sql);
		return executeSources(context, sources);
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

	protected SqlMetaData getSqlMetaData(long start, long count, SqlSourceExecutionContext context) {
		SqlMetaData ssm = new SqlMetaData();
		ssm.setExecutionTime(System.currentTimeMillis() - start);
		ssm.setCount(count);
		ssm.setReader(context.getJdbcContext().getReader());
		ssm.setSource(context.getSource());
		return ssm;
	}

	protected SqlSourceExecutionContext getSourceSqlExecutionContext(JdbcContext context, Connection conn, Statement stmt, SqlSource source, long runningCount, int index,
	        int sourcesCount) {
		SqlSourceExecutionContext sec = new SqlSourceExecutionContext();
		sec.setSourceIndex(index);
		sec.setJdbcContext(context);
		sec.setConnection(conn);
		sec.setStatement(stmt);
		sec.setSource(source);
		sec.setRunningCount(runningCount);
		sec.setSourcesCount(sourcesCount);
		return sec;
	}

}
