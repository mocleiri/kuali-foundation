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

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.BucketEvent;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.MultiThreadedExecutionListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.SqlEvent;
import org.kuali.common.jdbc.listener.SqlExecutionEvent;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SqlMetaDataEvent;
import org.kuali.common.jdbc.supplier.SimpleStringSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.jdbc.threads.SqlBucket;
import org.kuali.common.jdbc.threads.SqlBucketContext;
import org.kuali.common.jdbc.threads.SqlBucketHandler;
import org.kuali.common.threads.ExecutionStatistics;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PercentCompleteInformer;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

/**
 * @deprecated
 */
@Deprecated
public class DefaultJdbcService implements JdbcService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcService.class);

	@Override
	public ExecutionResult executeSql(JdbcContext context) {
		ExecutionStats stats = new ExecutionStats();
		long start = System.currentTimeMillis();

		// Log a message if provided
		if (!StringUtils.isBlank(context.getMessage())) {
			logger.info(context.getMessage());
		}

		// Make sure we have something to do
		if (CollectionUtils.isEmpty(context.getSuppliers())) {
			logger.info("Skipping execution.  No suppliers");
			return new ExecutionResult(0, start, System.currentTimeMillis(), 0);
		}

		// Calculate metadata
		if (!context.isSkipMetaData()) {
			doMetaData(context);
		}

		// Fire an event before executing any SQL
		long sqlStart = System.currentTimeMillis();
		context.getListener().beforeExecution(new SqlExecutionEvent(context, start, -1));

		// Execute the SQL as dictated by the context
		if (context.isMultithreaded()) {
			stats = executeMultiThreaded(context);
		} else {
			stats = executeSequentially(context);
		}

		// Fire an event now that all SQL execution is complete
		context.getListener().afterExecution(new SqlExecutionEvent(context, sqlStart, System.currentTimeMillis()));

		return new ExecutionResult(stats.getUpdateCount(), start, System.currentTimeMillis(), stats.getStatementCount());
	}

	protected void doMetaData(JdbcContext context) {

		logger.debug("doMetaData()");

		// Fire an event before we begin calculating metadata
		long start = System.currentTimeMillis();
		context.getListener().beforeMetaData(new SqlMetaDataEvent(context, start, -1));

		// Fill in SQL metadata
		for (SqlSupplier supplier : context.getSuppliers()) {
			supplier.fillInMetaData();
		}

		// Fire an event now that metadata calculation is complete
		context.getListener().afterMetaData(new SqlMetaDataEvent(context, start, System.currentTimeMillis()));
	}

	protected ExecutionStats executeMultiThreaded(JdbcContext context) {

		// Divide the SQL we have to execute up into buckets as "evenly" as possible
		List<SqlBucket> buckets = getSqlBuckets(context);

		// Notify the listener now that buckets are created
		context.getListener().bucketsCreated(new BucketEvent(context, buckets));

		// Sort the buckets largest to smallest
		Collections.sort(buckets);
		Collections.reverse(buckets);

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
		long total = JdbcUtils.getSqlCount(context.getSuppliers());
		PercentCompleteInformer informer = new PercentCompleteInformer(total);
		MultiThreadedExecutionListener etl = new MultiThreadedExecutionListener();
		etl.setTrackProgressByUpdateCount(context.isTrackProgressByUpdateCount());
		etl.setInformer(informer);
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new LogSqlListener());
		listeners.add(etl);
		NotifyingListener nl = new NotifyingListener(listeners);

		// Provide some context for each bucket
		List<SqlBucketContext> sbcs = getSqlBucketContexts(buckets, context, nl);

		// Store some context for the thread handler
		ThreadHandlerContext<SqlBucketContext> thc = new ThreadHandlerContext<SqlBucketContext>();
		thc.setList(sbcs);
		thc.setHandler(new SqlBucketHandler());
		thc.setMax(buckets.size());
		thc.setMin(buckets.size());
		thc.setDivisor(1);

		// Start threads to execute SQL from multiple suppliers concurrently
		ThreadInvoker invoker = new ThreadInvoker();
		informer.start();
		ExecutionStatistics stats = invoker.invokeThreads(thc);
		informer.stop();

		// Display thread related stats
		long aggregateTime = etl.getAggregateTime();
		long wallTime = stats.getExecutionTime();
		String avgMillis = FormatUtils.getTime(aggregateTime / buckets.size());
		String aTime = FormatUtils.getTime(aggregateTime);
		String wTime = FormatUtils.getTime(wallTime);
		String sqlCount = FormatUtils.getCount(etl.getAggregateSqlCount());
		String sqlSize = FormatUtils.getSize(etl.getAggregateSqlSize());
		Object[] args = { buckets.size(), wTime, aTime, avgMillis, sqlCount, sqlSize };
		logger.debug("Threads - [count: {}  time: {}  aggregate: {}  avg: {}  sql: {} - {}]", args);

		return new ExecutionStats(etl.getAggregateUpdateCount(), etl.getAggregateSqlCount());
	}

	@Override
	public ExecutionResult executeSql(DataSource dataSource, String sql) {
		return executeSql(dataSource, Arrays.asList(sql));
	}

	@Override
	public ExecutionResult executeSql(DataSource dataSource, List<String> sql) {
		SqlSupplier supplier = new SimpleStringSupplier(sql);
		JdbcContext context = new JdbcContext();
		context.setDataSource(dataSource);
		context.setSuppliers(Arrays.asList(supplier));
		return executeSql(context);
	}

	protected List<SqlBucketContext> getSqlBucketContexts(List<SqlBucket> buckets, JdbcContext context, SqlListener listener) {
		List<SqlBucketContext> sbcs = new ArrayList<SqlBucketContext>();

		for (SqlBucket bucket : buckets) {

			JdbcContext newJdbcContext = getJdbcContext(context, bucket, listener);

			SqlBucketContext sbc = new SqlBucketContext();
			sbc.setService(this);
			sbc.setBucket(bucket);
			sbc.setContext(newJdbcContext);
			sbcs.add(sbc);
		}
		return sbcs;
	}

	protected JdbcContext getJdbcContext(JdbcContext original, SqlBucket bucket, SqlListener listener) {
		JdbcContext context = new JdbcContext();
		context.setSuppliers(bucket.getSuppliers());
		context.setDataSource(original.getDataSource());
		context.setCommitMode(original.getCommitMode());
		context.setThreads(1);
		context.setSkip(original.isSkip());
		context.setListener(listener);
		context.setSkipMetaData(true);
		return context;
	}

	protected List<SqlBucket> getSqlBuckets(JdbcContext context) {

		// Pull out our list of suppliers
		List<SqlSupplier> suppliers = context.getSuppliers();

		// number of buckets equals thread count, unless thread count > total number of sources
		int bucketCount = Math.min(context.getThreads(), suppliers.size());

		// Sort the suppliers by SQL size
		Collections.sort(suppliers);

		// Largest to smallest instead of smallest to largest
		Collections.reverse(suppliers);

		// Allocate some buckets to hold the sql
		List<SqlBucket> buckets = CollectionUtils.getNewList(SqlBucket.class, bucketCount);

		// Distribute the sources into buckets as evenly as possible
		// "Evenly" in this case means each bucket should be roughly the same size
		for (SqlSupplier supplier : suppliers) {

			// Sort the buckets by size
			Collections.sort(buckets);

			// First bucket in the list is the smallest
			SqlBucket smallest = buckets.get(0);

			// Add this source to the bucket
			smallest.getSuppliers().add(supplier);

			// Update the bucket metadata holding overall size
			smallest.setCount(smallest.getCount() + supplier.getMetaData().getCount());
			smallest.setSize(smallest.getSize() + supplier.getMetaData().getSize());
		}

		// Return the buckets
		return buckets;
	}

	protected ExecutionStats executeSequentially(JdbcContext context) {
		Connection conn = null;
		Statement statement = null;
		try {
			long updateCount = 0;
			long statementCount = 0;
			conn = DataSourceUtils.doGetConnection(context.getDataSource());
			boolean originalAutoCommitSetting = conn.getAutoCommit();
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			List<SqlSupplier> suppliers = context.getSuppliers();
			for (SqlSupplier supplier : suppliers) {
				ExecutionStats stats = excecuteSupplier(statement, context, supplier);
				updateCount += stats.getUpdateCount();
				statementCount += stats.getStatementCount();
				conn.commit();
			}
			conn.setAutoCommit(originalAutoCommitSetting);
			return new ExecutionStats(updateCount, statementCount);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), conn, statement);
		}
	}

	protected ExecutionStats excecuteSupplier(Statement statement, JdbcContext context, SqlSupplier supplier) throws SQLException {
		try {
			long updateCount = 0;
			long statementCount = 0;
			supplier.open();
			List<String> sql = supplier.getSql();
			while (sql != null) {
				for (String s : sql) {
					updateCount += executeSql(statement, s, context);
					statementCount++;
				}
				sql = supplier.getSql();
			}
			return new ExecutionStats(updateCount, statementCount);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			supplier.close();
		}
	}

	protected int executeSql(Statement statement, String sql, JdbcContext context) throws SQLException {
		try {
			int updateCount = 0;
			long start = System.currentTimeMillis();
			context.getListener().beforeExecuteSql(new SqlEvent(sql, start));
			if (!context.isSkip()) {

				// Execute the SQL
				statement.execute(sql);

				// Get the number of rows updated as a result of executing this SQL
				updateCount = statement.getUpdateCount();

				// Some SQL statements have nothing to do with updating rows
				updateCount = (updateCount == -1) ? 0 : updateCount;
			}
			context.getListener().afterExecuteSql(new SqlEvent(sql, updateCount, start, System.currentTimeMillis()));
			return updateCount;
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
			throw new IllegalStateException(e);
		} finally {
			logger.trace("closing connection");
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
}
