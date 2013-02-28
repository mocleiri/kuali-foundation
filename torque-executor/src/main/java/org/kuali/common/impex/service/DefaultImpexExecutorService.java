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
	public List<MpxImportResult> importData(ImpexContext context, ExecutionContext sqlExecutionContext) throws IOException {
		Assert.notNull(jdbcService, "Need a non-null JdbcService to import data!");

		logger.info("Impex Executor data import started");

		Platform platform = PlatformFactory.getPlatformFor(context.getDatabaseVendor());
		context.setPlatform(platform);

		List<String> mpxLocations = LocationUtils.getLocations(context.getDataLocations());

		// Print a dot any time we complete 1% of our requests
		MpxBucketProgressListener progressListener = new MpxBucketProgressListener();

		List<MpxImportResult> importResults = new ArrayList<MpxImportResult>();
		List<MpxBucket> mpxBuckets = getMpxBuckets(mpxLocations, context, sqlExecutionContext, importResults, progressListener);

		// Create and invoke threads to fill in the metadata
		ExecutionStatistics stats = ImpexUtils.invokeThreads(mpxBuckets, new MpxBucketHandler());
		String time = FormatUtils.getTime(stats.getExecutionTime());

		progressListener.progressCompleted();

		logger.info("Data import completed.  Time: {}", time);

		return importResults;
	}

	@Override
	public MpxImportResult importDataLocation(MpxMetaData metaData, ImpexContext context, ExecutionContext sqlExecutionContext) {
		List<Table> tables = getTables(context);
		String filename = LocationUtils.getFilename(metaData.getLocation());
		logger.debug("Importing " + filename);
		String tableName = StringUtils.substring(filename, 0, StringUtils.indexOf(filename, "."));
		Table table = getTableDefinition(tableName, tables);
		return executeSql(context, table, metaData, sqlExecutionContext);
	}

	protected Table getTableDefinition(String tableName, List<Table> tables) {
		for (Table table : tables) {
			if (StringUtils.equalsIgnoreCase(tableName, table.getName())) {
				return table;
			}
		}
		throw new IllegalArgumentException("Cannot locate table definition for [" + tableName + "]");
	}

	protected List<MpxBucket> getMpxBuckets(List<String> locations, ImpexContext context, ExecutionContext sqlExecutionContext, List<MpxImportResult> results,
			MpxBucketProgressListener progressListener) throws IOException {
		// number of buckets equals thread count, unless thread count > total number of sources
		int bucketCount = Math.min(context.getDataThreads(), locations.size());

		List<MpxMetaData> metaDatas = MpxParser.getMpxMetaDatas(locations);

		// Sort the sources by size
		Collections.sort(metaDatas);
		// Largest to smallest instead of smallest to largest
		Collections.reverse(metaDatas);

		// Allocate some buckets to hold the files
		List<MpxBucket> buckets = new ArrayList<MpxBucket>(bucketCount);
		for (int i = 0; i < bucketCount; i++) {
			buckets.add(new MpxBucket());
		}

		// Distribute the sources into buckets as evenly as possible
		// "Evenly" in this case means each bucket should be roughly the same size
		for (MpxMetaData metaData : metaDatas) {
			// Sort the buckets by size, so the smallest is at the top, which is the bucket that should be filled next
			Collections.sort(buckets);
			// First bucket in the list is the smallest
			MpxBucket smallest = buckets.get(0);
			// Add this source to the bucket
			smallest.getMpxBeans().add(metaData);
			// Update the bucket metadata holding overall size
			smallest.setAllRowCounts(smallest.getAllRowCounts() + metaData.getRowCount());
		}

		for (MpxBucket bucket : buckets) {
			bucket.setProgressListener(progressListener);
			bucket.setContext(context);
			bucket.setService(this);
			bucket.setResults(results);
			bucket.setExecutionContext(sqlExecutionContext);

			// set the progress tracker total to the sum of all row counts for all buckets
			progressListener.setTotal(progressListener.getTotal() + bucket.getAllRowCounts());

			// Randomize the order in which tables get populated
			Collections.shuffle(bucket.getMpxBeans());
		}

		return buckets;
	}

	protected MpxImportResult executeSql(ImpexContext context, Table table, MpxMetaData metaData, ExecutionContext sqlExecutionContext) {
		SqlProducer sqlProducer = context.getPlatform().getSqlProducer();
		sqlProducer.setBatchDataSizeLimit(context.getDataSizeInterval());
		sqlProducer.setBatchRowCountLimit(context.getRowCountInterval());

		String location = metaData.getLocation();

		BufferedReader reader = null;
		MpxImportResult result = new MpxImportResult();
		result.setTableName(table.getName());
		result.setMpxPath(location);
		result.setStart(System.currentTimeMillis());
		try {
			reader = LocationUtils.getBufferedReader(location, context.getEncoding());

			List<String> sqlStrings = new ArrayList<String>(metaData.getRowCount() / context.getRowCountInterval());

			String sql = sqlProducer.getSql(table, reader);
			while (sql != null) {
				sqlStrings.add(sql);

				// after executing sql, add byte length to results
				result.setSize(result.getSize() + sql.getBytes().length);

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
	protected List<Table> getTables(ImpexContext context) {
		try {
			// Get an xml parser for schema.xml
			KualiXmlToAppData xmlParser = new KualiXmlToAppData(context.getDatabaseVendor(), "");

			// Parse schema.xml into a database object
			String location = context.getTablesXmlLocation();
			Database database = xmlParser.parseResource(location);

			return database.getTables();
		} catch (Exception e) {
			logger.info("Execption thrown when processing xml location: " + context.getTablesXmlLocation());
			throw new IllegalStateException(e);
		}
	}

	public JdbcService getJdbcService() {
		return jdbcService;
	}

	public void setJdbcService(JdbcService jdbcService) {
		this.jdbcService = jdbcService;
	}
}
