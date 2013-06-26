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

package org.kuali.common.impex.schema.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.UniqueConstraint;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.model.util.NamedElementComparator;
import org.kuali.common.impex.schema.service.SchemaExtractionContext;
import org.kuali.common.impex.schema.service.SchemaExtractionService;
import org.kuali.common.impex.util.ExtractionUtils;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.threads.ExecutionStatistics;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PercentCompleteInformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSchemaExtractionService implements SchemaExtractionService {

	private static Logger log = LoggerFactory.getLogger(DefaultSchemaExtractionService.class);

	protected static final int SINGLE_THREAD_COUNT = 1;

	@Override
	public Schema getSchema(SchemaExtractionContext context) {

		Schema result;

		try {
			if (context.getThreadCount() <= SINGLE_THREAD_COUNT) {
				result = extractSingleThreaded(context);
			} else {
				result = extractMultiThreaded(context);
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Unexpected SQL error", e);
		}

		sortSchemaElements(result);

		return result;
	}

	protected void sortSchemaElements(Schema result) {
		Collections.sort(result.getTables(), NamedElementComparator.getInstance());
		Collections.sort(result.getForeignKeys(), NamedElementComparator.getInstance());
		Collections.sort(result.getSequences(), NamedElementComparator.getInstance());
		Collections.sort(result.getViews(), NamedElementComparator.getInstance());
	}

	protected Schema extractSingleThreaded(SchemaExtractionContext context) throws SQLException {
		long startTime = System.currentTimeMillis();
		log.info("Single threaded schema extraction started");

		Schema result = new Schema();

		List<String> tableNames = getTableNames(context);
		log.debug("Extracting {} tables...", new Object[] { tableNames.size() });
		result.getTables().addAll(extractTables(tableNames, context));
		log.debug("Table extraction complete.");

		result.getViews().addAll(extractViews(context));
		log.debug("View extraction complete");

		result.getSequences().addAll(extractSequences(context));
		log.debug("Sequence extraction complete");

		result.getForeignKeys().addAll(extractForeignKeys(tableNames, context));
		log.debug("Foreign Key extraction complete");

		String timeString = FormatUtils.getTime(System.currentTimeMillis() - startTime);
		log.info("Single threaded schema extraction complete - Time: {}", new Object[] { timeString });
		return result;

	}

	protected Schema extractMultiThreaded(SchemaExtractionContext context) throws SQLException {
		log.info("Multi threaded schema extraction started");

		List<String> tableNames = getTableNames(context);

		// the total number of schema extraction tasks is calculated as follows:
		// - One task for each table name to get table/column data
		int totalTasks = tableNames.size();

		// - One task for each table name to get foreign keys
		totalTasks += tableNames.size();

		// - One task to get all sequences and all views
		totalTasks++;

		// so the total number of tasks to track progress on will be (2 * number of tables) + 1
		PercentCompleteInformer progressTracker = new PercentCompleteInformer();
		progressTracker.setTotal(totalTasks);

		Schema schema = new Schema();

		// one thread will handle all views and sequences, then split the table names among other threads
		int maxTableThreads = context.getThreadCount() - 1;

		List<List<String>> splitNames = CollectionUtils.splitEvenly(tableNames, maxTableThreads);

		List<ExtractSchemaBucket> schemaBuckets = new ArrayList<ExtractSchemaBucket>(splitNames.size() + 1);

		// add one special schema bucket for handling views and sequences
		ExtractSchemaBucket viewSequenceBucket = new ExtractViewsAndSequencesBucket();
		viewSequenceBucket.setContext(context);
		viewSequenceBucket.setSchema(schema);
		schemaBuckets.add(viewSequenceBucket);

		// create one bucket for each group of table names from the split
		for (List<String> names : splitNames) {
			ExtractSchemaBucket bucket = new ExtractSchemaBucket();
			bucket.setTableNames(names);
			bucket.setContext(context);
			bucket.setSchema(schema);

			schemaBuckets.add(bucket);
		}

		// Create and invoke threads to fill in the metadata
		// Store some context for the thread handler
		ThreadHandlerContext<ExtractSchemaBucket> thc = new ThreadHandlerContext<ExtractSchemaBucket>();
		thc.setList(schemaBuckets);
		thc.setHandler(new ExtractSchemaBucketHandler(this));
		thc.setMax(schemaBuckets.size());
		thc.setMin(schemaBuckets.size());
		thc.setDivisor(1);

		// Start threads to acquire table metadata concurrently
		ExecutionStatistics stats = new ThreadInvoker().invokeThreads(thc);

		String time = FormatUtils.getTime(stats.getExecutionTime());
		log.info("Schema extraction completed.  Time: {}", time);

		return schema;
	}

	@Override
	public Collection<Table> extractTables(List<String> tableNames, SchemaExtractionContext context) throws SQLException {
		Collection<Table> results = new ArrayList<Table>(tableNames.size());

		DatabaseMetaData metaData = getMetaDataInstance(context);

		try {
			for (String name : tableNames) {
				results.add(extractTable(name, context.getSchemaName(), metaData));
			}

			return results;
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), metaData.getConnection());
		}
	}

	protected Table extractTable(String tablename, String schemaName, DatabaseMetaData metaData) throws SQLException {
		Table result = new Table(tablename);

		result.setDescription(ExtractionUtils.extractTableComment(tablename, schemaName, metaData));
		result.setColumns(ExtractionUtils.extractTableColumns(tablename, schemaName, metaData));

		List<Index> allTableIndices = ExtractionUtils.extractTableIndices(tablename, schemaName, metaData);

		for (Index index : allTableIndices) {
			if (index.isUnique()) {
				UniqueConstraint u = new UniqueConstraint(index.getColumnNames(), index.getName());
				result.getUniqueConstraints().add(u);
			} else {
				result.getIndices().add(index);
			}
		}
		return result;

	}

	protected List<String> getTableNames(SchemaExtractionContext context) throws SQLException {
		DatabaseMetaData metaData = getMetaDataInstance(context);

		List<String> allTables;
		try {
			allTables = ExtractionUtils.getTableNamesFromMetaData(context.getSchemaName(), metaData);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), metaData.getConnection());
		}

		List<String> filteredNames = new ArrayList<String>();
		for (String name : allTables) {
			if (context.getNameFilter().include(name)) {
				filteredNames.add(name);
			}
		}
		return filteredNames;
	}

	@Override
	public List<View> extractViews(SchemaExtractionContext context) throws SQLException {
		Connection connection = context.getDataSource().getConnection();
		try {
			return context.getViewFinder().findViews(context.getNameFilter(), connection);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), connection);
		}
	}

	@Override
	public List<Sequence> extractSequences(SchemaExtractionContext context) throws SQLException {
		Connection connection = context.getDataSource().getConnection();
		try {
			return context.getSequenceFinder().findSequences(context.getNameFilter(), connection);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), connection);
		}
	}

	@Override
	public List<ForeignKey> extractForeignKeys(List<String> tableNames, SchemaExtractionContext context) throws SQLException {
		DatabaseMetaData metaData = getMetaDataInstance(context);
		try {
			return ExtractionUtils.extractForeignKeys(tableNames, context.getSchemaName(), metaData);
		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), metaData.getConnection());
		}
	}

	protected DatabaseMetaData getMetaDataInstance(SchemaExtractionContext context) throws SQLException {
		return context.getDataSource().getConnection().getMetaData();
	}
}
