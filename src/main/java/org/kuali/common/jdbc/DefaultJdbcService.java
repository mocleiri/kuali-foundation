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

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.BucketEvent;
import org.kuali.common.jdbc.listener.SqlEvent;
import org.kuali.common.jdbc.listener.SqlExecutionEvent;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.supplier.SimpleStringSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.jdbc.threads.SqlBucket;
import org.kuali.common.jdbc.threads.SqlBucketContext;
import org.kuali.common.jdbc.threads.SqlBucketHandler;
import org.kuali.common.jdbc.threads.ThreadsProgressListener;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DefaultJdbcService implements JdbcService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcService.class);

	@Override
	public void executeSql(JdbcContext context) {
		if (context.getMessage() != null) {
			logger.info(context.getMessage());
		}

		// Fill in SQL metadata
		for (SqlSupplier supplier : context.getSuppliers()) {
			supplier.fillInMetaData();
		}

		context.getListener().beforeMetaData(context);
		context.getListener().beforeExecution(new SqlExecutionEvent(context));
		if (context.getThreads() < 2 || context.getSuppliers().size() < 2) {
			executeSequentially(context);
		} else {
			executeMultiThreaded(context);
		}
		context.getListener().afterExecution(new SqlExecutionEvent(context));
	}

	protected void executeMultiThreaded(JdbcContext context) {

		// Divide the SQL we have to execute up into buckets as "evenly" as possible
		List<SqlBucket> buckets = getSqlBuckets(context);

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
		listener.setTotal(JdbcUtils.getSqlCount(context.getSuppliers()));

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

		// Start threads to execute SQL from multiple suppliers concurrently
		ThreadInvoker invoker = new ThreadInvoker();
		invoker.invokeThreads(thc);
	}

	@Override
	public void executeSql(DataSource dataSource, String sql) {
		SqlSupplier supplier = new SimpleStringSupplier(Arrays.asList(sql));
		JdbcContext context = new JdbcContext();
		context.setDataSource(dataSource);
		context.setSuppliers(Arrays.asList(supplier));
		executeSql(context);
	}

	protected List<SqlBucketContext> getSqlBucketContexts(List<SqlBucket> buckets, JdbcContext context, SqlListener listener) {
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

	protected JdbcContext getExecutionContext(JdbcContext original, SqlBucket bucket, SqlListener listener) {
		JdbcContext context = new JdbcContext();
		context.setDataSource(original.getDataSource());
		context.setCommitMode(original.getCommitMode());
		context.setThreads(1);
		context.setExecute(original.isExecute());
		context.setListener(listener);
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

	protected void executeSequentially(JdbcContext context) {
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DataSourceUtils.doGetConnection(context.getDataSource());
			boolean originalAutoCommitSetting = conn.getAutoCommit();
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			List<SqlSupplier> suppliers = context.getSuppliers();
			for (SqlSupplier supplier : suppliers) {
				excecuteSupplier(statement, context, supplier);
				conn.commit();
			}
			conn.setAutoCommit(originalAutoCommitSetting);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), conn, statement);
		}
	}

	protected void excecuteSupplier(Statement statement, JdbcContext context, SqlSupplier supplier) throws SQLException {
		try {
			supplier.open();
			String sql = supplier.getSql();
			while (sql != null) {
				executeSql(statement, sql, context);
				sql = supplier.getSql();
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			supplier.close();
		}
	}

	protected void executeSql(Statement statement, String sql, JdbcContext context) throws SQLException {
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
