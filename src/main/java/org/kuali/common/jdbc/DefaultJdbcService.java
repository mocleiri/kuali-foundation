/**
 * Copyright 2010-2014 The Kuali Foundation
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
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.BucketEvent;
import org.kuali.common.jdbc.listener.SqlEvent;
import org.kuali.common.jdbc.listener.SqlExecutionEvent;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.jdbc.threads.SqlBucket;
import org.kuali.common.jdbc.threads.SqlBucketContext;
import org.kuali.common.jdbc.threads.SqlBucketHandler;
import org.kuali.common.jdbc.threads.ThreadsProgressListener;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DefaultJdbcService implements JdbcService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcService.class);

	@Override
	public void executeSql(ExecutionContext context) {
		if (context.getMessage() != null) {
			logger.info(context.getMessage());
		}
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

	protected void executeMultiThreaded(ExecutionContext context, List<SqlSource> sources) {

		// Divide the SQL we have to execute up into buckets as "evenly" as possible
		List<SqlBucket> buckets = getSqlBuckets(context, sources);

		// Notify the listener now that buckets are created
		context.getListener().bucketsCreated(new BucketEvent(context, buckets));

		// Sort the buckets largest to smallest
		Collections.sort(buckets);
		Collections.reverse(buckets);

		// Progress across all of the threads is coordinated by ThreadsProgressListener
		// The tracking built into the kuali-threads package assumes "progress" equals one element from the list completing
		// It assumes you have a gigantic list where each element in the list = 1 unit of work
		// A large list of files that need to be posted to S3 (for example).
		// If we could randomly split up the SQL and execute it in whatever order we wanted, the built in tracking would work.
		// We cannot do that though, since the SQL in each file needs to execute sequentially in order
		// SQL from different files can execute concurrently, but the SQL inside each file needs to execute in order
		// For example OLE has ~250,000 SQL statements split up across ~300 files
		// In addition, the schema related DDL files need to execute first, then data, then constraints DDL files
		// Some files are HUGE, some are tiny. Printing a dot after each file completes doesn't make sense.
		// Our list of buckets is pretty small, even though the total number of SQL statements is quite large
		// Only printing a dot to the console when each bucket completes is not granular enough

		// This listener prints a dot each time 1% of the total number of SQL statements across all of the buckets has been executed.
		ThreadsProgressListener listener = new ThreadsProgressListener();
		listener.setTotal(JdbcUtils.getSqlCount(sources));

		// Provide some context for each bucket
		List<SqlBucketContext> sbcs = getSqlBucketContexts(buckets, context, listener);

		// Store some context for the thread handler
		ThreadHandlerContext<SqlBucketContext> thc = new ThreadHandlerContext<SqlBucketContext>();
		thc.setList(sbcs);
		thc.setHandler(new SqlBucketHandler());
		thc.setMax(buckets.size());
		thc.setMin(buckets.size());
		thc.setDivisor(1);
		// thc.setListener(new PercentCompleteListener<SqlBucketContext>());

		// Start threads to execute SQL from multiple files concurrently
		ThreadInvoker invoker = new ThreadInvoker();
		invoker.invokeThreads(thc);
	}

	@Override
	public void executeSql(DataSource dataSource, String sql) {
		JdbcContext jdbcContext = new JdbcContext();
		jdbcContext.setDataSource(dataSource);
		ExecutionContext context = new ExecutionContext();
		context.setJdbcContext(jdbcContext);
		context.setSql(Arrays.asList(sql));
		context.setReader(new DefaultSqlReader());
		executeSql(context);
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
			logger.debug("Getting metadata for {}", location);
			SqlSource source = new SqlSource();
			source.setLocation(location);
			source.setEncoding(context.getEncoding());
			source.setMetaData(getMetaData(context.getReader(), location, context.getEncoding()));
			sources.add(source);
		}
		for (SqlSupplier supplier : CollectionUtils.toEmptyList(context.getSuppliers())) {
			SqlMetaData smd = supplier.getSqlMetaData();
			SqlSource source = new SqlSource();
			source.setSupplier(supplier);
			source.setMetaData(smd);
			sources.add(source);
		}
		return sources;
	}

	protected void executeSqlSources(Connection conn, Statement statement, ExecutionContext context, List<SqlSource> sources) throws SQLException {
		for (SqlSource source : sources) {
			if (source.getSql() != null) {
				executeSqlString(conn, statement, context, source.getSql());
			}
			if (source.getLocation() != null) {
				executeLocation(conn, statement, context, source.getLocation());
			}
			if (source.getSupplier() != null) {
				excecuteSupplier(conn, statement, context, source.getSupplier());
			}
			conn.commit();
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

	protected void excecuteSupplier(Connection conn, Statement statement, ExecutionContext context, SqlSupplier supplier) {
		try {
			supplier.open();
			String sql = supplier.getSql();
			while (sql != null) {
				executeSqlString(conn, statement, context, sql);
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			supplier.close();
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
			long start = System.currentTimeMillis();
			context.getListener().beforeExecuteSql(new SqlEvent(sql, start, 0));
			if (context.isExecute()) {
				statement.execute(sql);
			}
			context.getListener().afterExecuteSql(new SqlEvent(sql, start, System.currentTimeMillis()));
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
			return reader.getSqlMetaData(in);
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
			return reader.getSqlMetaData(in);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO exception");
		} finally {
			IOUtils.closeQuietly(in);
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
}
