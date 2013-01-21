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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlBucketContext;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.ThreadsProgressListener;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DefaultJdbcService implements JdbcService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcService.class);

	@Override
	public void executeSql(ExecutionContext context) {
		context.getListener().beforeMetaData(context);
		List<SqlSource> sources = getSqlSources(context);
		context.getListener().beforeExecution(new SqlExecutionEvent(context, sources));
		if (context.getThreads() < 2 || sources.size() < 2) {
			executeSequentially(context, sources);
		} else {
			executeMultiThreaded(context, sources);
		}
		context.getListener().afterExecution(new SqlExecutionEvent(context, sources));
	}

	protected void summarizeBuckets(List<SqlBucket> buckets) {
		List<Object[]> argsList = new ArrayList<Object[]>();
		for (int i = 0; i < buckets.size(); i++) {
			SqlBucket bucket = buckets.get(i);
			List<SqlSource> sources = bucket.getSources();
			String count = FormatUtils.getCount(JdbcUtils.getSqlCount(sources));
			String srcs = FormatUtils.getCount(sources.size());
			String size = FormatUtils.getSize(JdbcUtils.getSqlSize(sources));
			Object[] args = { i + 1, count, srcs, size };
			argsList.add(args);
		}
		List<String> columns = Arrays.asList("Bucket", "SQL Count", "Sources", "Size");
		int[] padding = getPadding(columns, argsList);
		StringBuilder header = new StringBuilder();
		for (int i = 0; i < columns.size(); i++) {
			if (i != 0) {
				header.append("  ");
			}
			header.append(StringUtils.leftPad(columns.get(i), padding[i]));
		}
		logger.info(header.toString());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < padding.length; i++) {
			if (i != 0) {
				sb.append("  ");
			}
			sb.append("{}");
		}
		for (Object[] args : argsList) {
			for (int i = 0; i < args.length; i++) {
				args[i] = StringUtils.leftPad(args[i].toString(), padding[i]);
			}
			logger.info(sb.toString(), args);
		}
	}

	protected int[] getPadding(List<String> columns, List<Object[]> argsList) {
		int[] padding = new int[columns.size()];
		for (int i = 0; i < padding.length; i++) {
			padding[i] = Math.max(padding[i], columns.get(i).length());
		}
		for (Object[] args : argsList) {
			Assert.isTrue(columns.size() == args.length, "Column count must equals args.length");
			for (int i = 0; i < args.length; i++) {
				padding[i] = Math.max(padding[i], args[i].toString().length());
			}
		}
		return padding;
	}

	protected void executeMultiThreaded(ExecutionContext context, List<SqlSource> sources) {
		long sqlCount = JdbcUtils.getSqlCount(sources);
		ThreadsProgressListener listener = new ThreadsProgressListener();
		listener.setTotal(sqlCount);

		List<SqlBucket> buckets = getSqlBuckets(context, sources);
		summarizeBuckets(buckets);
		List<SqlBucketContext> sbcs = getSqlBucketContexts(buckets, context, listener);

		// Store some context for the thread handler
		ThreadHandlerContext<SqlBucketContext> thc = new ThreadHandlerContext<SqlBucketContext>();
		thc.setList(sbcs);
		thc.setHandler(new SqlBucketHandler());
		thc.setMax(buckets.size());
		thc.setMin(buckets.size());
		thc.setDivisor(1);
		// thc.setListener(new PercentCompleteListener<SqlBucketContext>());

		ThreadInvoker invoker = new ThreadInvoker();
		invoker.invokeThreads(thc);
	}

	protected List<SqlBucketContext> getSqlBucketContexts(List<SqlBucket> buckets, ExecutionContext context, SqlListener listener) {
		List<SqlBucketContext> sbcs = new ArrayList<SqlBucketContext>();
		for (SqlBucket bucket : buckets) {
			SqlBucketContext sbc = new SqlBucketContext();
			sbc.setService(this);
			sbc.setBucket(bucket);
			sbc.setContext(getExecutionContext(context, bucket, listener));
			sbcs.add(sbc);
		}
		return sbcs;
	}

	protected ExecutionContext getExecutionContext(ExecutionContext original, SqlBucket bucket, SqlListener listener) {
		List<SqlSource> sources = bucket.getSources();
		ExecutionContext context = new ExecutionContext();
		context.setSql(getSql(sources));
		context.setLocations(getLocations(sources));
		context.setEncoding(original.getEncoding());
		context.setJdbcContext(original.getJdbcContext());
		context.setReader(original.getReader());
		context.setThreads(1);
		context.setExecute(original.isExecute());
		context.setListener(listener);
		return context;
	}

	protected List<SqlBucket> getSqlBuckets(ExecutionContext context, List<SqlSource> sources) {
		// number of buckets equals thread count, unless thread count > total number of sources
		int bucketCount = Math.min(context.getThreads(), sources.size());
		// Sort the sources by size
		Collections.sort(sources);
		// Largest to smallest instead of smallest to largest
		Collections.reverse(sources);
		// Allocate some buckets to hold the sql
		List<SqlBucket> buckets = CollectionUtils.getNewList(SqlBucket.class, bucketCount);
		// Distribute the sources into buckets as evenly as possible
		// "Evenly" in this case means each bucket should be roughly the same size
		for (SqlSource source : sources) {
			// Sort the buckets by size
			Collections.sort(buckets);
			// First bucket in the list is the smallest
			SqlBucket smallest = buckets.get(0);
			// Add this source to the bucket
			smallest.getSources().add(source);
			// Update the bucket metadata holding overall size
			smallest.setCount(smallest.getCount() + source.getMetaData().getCount());
			smallest.setSize(smallest.getSize() + source.getMetaData().getSize());
		}
		return buckets;
	}

	protected void executeSequentially(ExecutionContext context, List<SqlSource> sources) {
		JdbcContext jdbc = context.getJdbcContext();
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DataSourceUtils.doGetConnection(jdbc.getDataSource());
			boolean originalAutoCommitSetting = conn.getAutoCommit();
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			executeSqlSources(conn, statement, context, sources);
			conn.commit();
			conn.setAutoCommit(originalAutoCommitSetting);
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			JdbcUtils.closeQuietly(jdbc.getDataSource(), conn, statement);
		}
	}

	protected List<String> getSql(List<SqlSource> sources) {
		List<String> sql = new ArrayList<String>();
		for (SqlSource source : sources) {
			if (source.getSql() != null) {
				sql.add(source.getSql());
			}
		}
		return sql;
	}

	protected List<String> getLocations(List<SqlSource> sources) {
		List<String> locations = new ArrayList<String>();
		for (SqlSource source : sources) {
			if (source.getLocation() != null) {
				locations.add(source.getLocation());
			}
		}
		return locations;
	}

	protected List<SqlSource> getSqlSources(ExecutionContext context) {
		List<SqlSource> sources = new ArrayList<SqlSource>();
		for (String sql : CollectionUtils.toEmptyList(context.getSql())) {
			SqlSource source = new SqlSource();
			source.setSql(sql);
			source.setMetaData(getMetaDataFromString(context.getReader(), sql));
			sources.add(source);
		}
		for (String location : CollectionUtils.toEmptyList(context.getLocations())) {
			SqlSource source = new SqlSource();
			source.setLocation(location);
			source.setEncoding(context.getEncoding());
			source.setMetaData(getMetaData(context.getReader(), location, context.getEncoding()));
			sources.add(source);
		}
		return sources;
	}

	protected void executeSqlSources(Connection conn, Statement statement, ExecutionContext context, List<SqlSource> sources) {
		for (SqlSource source : sources) {
			if (source.getSql() != null) {
				executeSqlString(conn, statement, context, source.getSql());
			} else {
				executeLocation(conn, statement, context, source.getLocation());
			}
		}
	}

	protected void executeSqlString(Connection conn, Statement statement, ExecutionContext context, String sql) {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReaderFromString(sql);
			executeSql(conn, statement, context, in);
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void executeLocation(Connection conn, Statement statement, ExecutionContext context, String location) {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReader(location, context.getEncoding());
			executeSql(conn, statement, context, in);
		} catch (Exception e) {
			throw new JdbcException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void executeSql(Connection conn, Statement statement, ExecutionContext context, BufferedReader in) throws IOException, SQLException {
		SqlReader reader = context.getReader();
		String sql = reader.getSqlStatement(in);
		while (sql != null) {
			executeSql(statement, sql, context);
			sql = reader.getSqlStatement(in);
		}
	}

	protected void executeSql(Statement statement, String sql, ExecutionContext context) throws SQLException {
		try {
			context.getListener().beforeExecuteSql(sql);
			if (context.isExecute()) {
				statement.execute(sql);
			}
			context.getListener().afterExecuteSql(sql);
		} catch (SQLException e) {
			throw new SQLException("Error executing SQL [" + Str.flatten(sql) + "]", e);
		}
	}

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
			logger.trace("closing connection");
			JdbcUtils.closeQuietly(dataSource, conn);
		}
	}

	@Override
	public List<SqlMetaData> getMetaData(SqlReader reader, List<String> locations, String encoding) {
		List<SqlMetaData> smdl = new ArrayList<SqlMetaData>();
		for (String location : locations) {
			smdl.add(getMetaData(reader, location, encoding));
		}
		return smdl;
	}

	@Override
	public SqlMetaData getMetaData(SqlReader reader, String location, String encoding) {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReader(location, encoding);
			return getSqlMetaData(reader, in);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO exception");
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public List<SqlMetaData> getMetaDataFromStrings(SqlReader reader, List<String> sql) {
		List<SqlMetaData> smdl = new ArrayList<SqlMetaData>();
		for (String s : sql) {
			smdl.add(getMetaDataFromString(reader, s));
		}
		return smdl;
	}

	@Override
	public SqlMetaData getMetaDataFromString(SqlReader reader, String sql) {
		BufferedReader in = null;
		try {
			in = LocationUtils.getBufferedReaderFromString(sql);
			return getSqlMetaData(reader, in);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO exception");
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected SqlMetaData getSqlMetaData(SqlReader reader, BufferedReader in) throws IOException {
		long count = 0;
		long size = 0;
		String sql = reader.getSqlStatement(in);
		while (sql != null) {
			count++;
			size += sql.length();
			sql = reader.getSqlStatement(in);
		}
		SqlMetaData smd = new SqlMetaData();
		smd.setCount(count);
		smd.setSize(size);
		return smd;
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
}
