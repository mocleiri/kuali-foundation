/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.torque.engine.database.model.Database;
import org.apache.torque.engine.database.model.Table;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.threads.ExecutionStatistics;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.core.db.torque.KualiXmlToAppData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.Assert;

/**
 * Default implementation of the ImpexExecutorService
 * 
 * @author andrewlubbers
 */
public class DefaultImpexExecutorService implements ImpexExecutorService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultImpexExecutorService.class);

	private JdbcService jdbcService;

	@Override
	public List<MpxImportResult> importData(ImportContext context, ExecutionContext sqlExecutionContext) throws IOException {
		Assert.notNull(jdbcService, "jdbcService is null");

		logger.info("Impex Executor data import started");
		List<String> mpxLocations = LocationUtils.getLocations(context.getDataLocations());
		List<MpxMetaData> metaData = MpxParser.getMpxMetaDatas(mpxLocations);

		logContext(context, sqlExecutionContext, mpxLocations, metaData);

		// Print a dot any time we complete 1% of our requests
		long rows = getTotalRowCount(metaData);
		MpxBucketProgressListener progressListener = new MpxBucketProgressListener();
		progressListener.setTotal(rows);

		List<MpxImportResult> importResults = new ArrayList<MpxImportResult>();
		List<MpxBucket> mpxBuckets = getMpxBuckets(mpxLocations, context, sqlExecutionContext, importResults, progressListener, metaData);

		// Create and invoke threads to fill in the metadata
		ExecutionStatistics stats = ImpexUtils.invokeThreads(mpxBuckets, new MpxBucketHandler());
		String time = FormatUtils.getTime(stats.getExecutionTime());

		progressListener.progressCompleted();

		logger.info("Data import completed.  Time: {}", time);

		return importResults;
	}

	@Override
	public MpxImportResult importDataLocation(MpxMetaData metaData, ImportContext context, ExecutionContext sqlExecutionContext) {
		List<Table> tables = getTables(context.getDatabaseVendor(), context.getSchemaXmlLocation());
		String filename = LocationUtils.getFilename(metaData.getLocation());
		logger.debug("Importing " + filename);
		String tableName = StringUtils.substring(filename, 0, StringUtils.indexOf(filename, "."));
		Table table = getTableDefinition(tableName, tables);

		Platform platform = PlatformFactory.getPlatformFor(context.getDatabaseVendor());

		SqlProducer sqlProducer = platform.getSqlProducer();
		sqlProducer.setBatchDataSizeLimit(context.getBatchDataSize());
		sqlProducer.setBatchRowCountLimit(context.getBatchRowCount());

		return executeSql(context, sqlProducer, table, metaData, sqlExecutionContext);
	}

	protected Table getTableDefinition(String tableName, List<Table> tables) {
		for (Table table : tables) {
			if (StringUtils.equalsIgnoreCase(tableName, table.getName())) {
				return table;
			}
		}
		throw new IllegalArgumentException("Cannot locate table definition for [" + tableName + "]");
	}

	protected long getTotalRowCount(List<MpxMetaData> metaDatas) {
		long rows = 0;
		for (MpxMetaData md : metaDatas) {
			rows += md.getRowCount();
		}
		return rows;
	}

	protected List<MpxBucket> getMpxBuckets(List<String> locations, ImportContext context, ExecutionContext sqlExecutionContext, List<MpxImportResult> results,
			MpxBucketProgressListener progressListener, List<MpxMetaData> metaDatas) throws IOException {

		// number of buckets equals thread count, unless thread count > total number of locations
		int bucketCount = Math.min(context.getMaxThreadCount(), metaDatas.size());

		// Sort the metadata by row count
		Collections.sort(metaDatas);

		// Largest to smallest instead of smallest to largest
		Collections.reverse(metaDatas);

		// Allocate the appropriate number of buckets
		List<MpxBucket> buckets = new ArrayList<MpxBucket>(bucketCount);
		for (int i = 0; i < bucketCount; i++) {
			buckets.add(new MpxBucket());
		}

		// Distribute the metadata into buckets as evenly as possible
		// "Evenly" in this case means each bucket should contain as close to the same number of rows as possible
		for (MpxMetaData metaData : metaDatas) {
			// Sort the buckets so that the bucket holding the fewest rows is at the top
			Collections.sort(buckets);
			// First bucket in the list is the smallest
			MpxBucket smallest = buckets.get(0);
			// Add this source to the bucket
			smallest.getMpxBeans().add(metaData);
			// Update the bucket metadata holding overall row count
			smallest.setAllRowCounts(smallest.getAllRowCounts() + metaData.getRowCount());
		}

		for (MpxBucket bucket : buckets) {
			bucket.setProgressListener(progressListener);
			bucket.setContext(context);
			bucket.setService(this);
			bucket.setResults(results);
			bucket.setExecutionContext(sqlExecutionContext);
			// Randomize the order in which tables get populated
			Collections.shuffle(bucket.getMpxBeans());
		}

		return buckets;
	}

	protected MpxImportResult executeSql(ImportContext context, SqlProducer sqlProducer, Table table, MpxMetaData metaData, ExecutionContext sqlExecutionContext) {
		String location = metaData.getLocation();

		BufferedReader reader = null;
		MpxImportResult result = new MpxImportResult();
		result.setTableName(table.getName());
		result.setMpxPath(location);
		result.setStart(System.currentTimeMillis());
		try {
			reader = LocationUtils.getBufferedReader(location, context.getEncoding());

			List<String> sqlStrings = new ArrayList<String>(metaData.getRowCount() / context.getBatchRowCount());

			String sql = sqlProducer.getSql(table, reader);
			while (sql != null) {
				sqlStrings.add(sql);

				// after executing sql, add byte length to results
				result.setSize(result.getSize() + sql.length());

				sql = sqlProducer.getSql(table, reader);
			}

			// execute the sql as a batch
			sqlExecutionContext.setSql(sqlStrings);
			jdbcService.executeSql(sqlExecutionContext);

		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(reader);
		}

		result.setFinish(System.currentTimeMillis());
		result.setElapsed(result.getFinish() - result.getStart());

		return result;
	}

	@SuppressWarnings("unchecked")
	protected List<Table> getTables(String databaseVendor, String tablesXmlLocation) {
		try {
			// Get an xml parser for schema.xml
			KualiXmlToAppData xmlParser = new KualiXmlToAppData(databaseVendor, "");

			// Parse schema.xml into a database object
			Database database = xmlParser.parseResource(tablesXmlLocation);

			return database.getTables();
		} catch (Exception e) {
			logger.info("Execption thrown when processing xml location: " + tablesXmlLocation);
			throw new IllegalStateException(e);
		}
	}

	public JdbcService getJdbcService() {
		return jdbcService;
	}

	public void setJdbcService(JdbcService jdbcService) {
		this.jdbcService = jdbcService;
	}

	protected void logContext(ImportContext context, ExecutionContext ec, List<String> locations, List<MpxMetaData> metaData) {
		long rows = 0;
		long size = 0;
		for (MpxMetaData mmd : metaData) {
			rows += mmd.getRowCount();
			size += mmd.getSize();
		}
		logger.info("---------------------------------------------------------------");
		logger.info("Import Context Properties");
		logger.info("---------------------------------------------------------------");
		logger.info("Database Vendor - {}", context.getDatabaseVendor());
		logger.info("Data locations resource(s) - {}", context.getDataLocations());
		logger.info("Schema xml location - {}", context.getSchemaXmlLocation());
		logger.info("Encoding - {}", context.getEncoding());
		logger.info("Max thread count - {}", context.getMaxThreadCount());
		logger.info("Batch Data Size - {}", FormatUtils.getSize(context.getBatchDataSize()));
		logger.info("Batch Row Count - {}", context.getBatchRowCount());

		DriverManagerDataSource dmds = (DriverManagerDataSource) ec.getJdbcContext().getDataSource();
		logger.info("URL - {}", dmds.getUrl());
		logger.info("Username - {}", dmds.getUsername());
		logger.info("Password - {}", dmds.getPassword());
		String locs = FormatUtils.getCount(locations.size());
		String count = FormatUtils.getCount(rows);
		String length = FormatUtils.getSize(size);
		Object[] args = { count, locs, length };
		logger.info("[Row Count: {}  Sources: {}  Size: {}]", args);
	}
}
