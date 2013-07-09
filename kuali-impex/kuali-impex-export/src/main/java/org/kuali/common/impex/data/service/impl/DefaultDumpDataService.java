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

package org.kuali.common.impex.data.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.data.service.DumpDataContext;
import org.kuali.common.impex.data.service.DumpDataService;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.impex.util.DumpUtils;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.threads.ExecutionStatistics;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LongCounter;
import org.kuali.common.util.PercentCompleteInformer;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDumpDataService implements DumpDataService {

	protected static final Logger logger = LoggerFactory.getLogger(DefaultDumpDataService.class);

	/**
	 * In the absence of a properties file holding detailed table statistics, this value is used. The logic prints a dot to the console each time it thinks it makes 1% progress
	 * towards the overall goal. This default count will cause a dot to get printed for every 5,000 rows that get dumped.
	 */
	protected static final int DEFAULT_DATABASE_ROW_COUNT = 500000;

	@Override
	public DumpTableResult dumpTable(DumpDataContext context, DumpTableContext tableContext, Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		long start = System.currentTimeMillis();
		String query = getSelectAllQuery(tableContext.getTable());
		try {
			stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(query);
			DumpTableResult result = dumpTable(context, tableContext, rs);
			result.setFinish(System.currentTimeMillis());
			result.setStart(start);
			result.setElapsed(result.getFinish() - start);
			return result;
		} catch (SQLException e) {
			throw new IllegalStateException("Unexpected SQL error", e);
		} finally {
			JdbcUtils.closeQuietly(rs);
			JdbcUtils.closeQuietly(stmt);
		}
	}

	protected DumpTableResult dumpTable(DumpDataContext dataContext, DumpTableContext tableContext, ResultSet rs) {
		OutputStream out = null;
		try {

			// Extract the table we are dumping
			Table table = tableContext.getTable();

			// The file we'll be dumping this tables data into
			File outputFile = DataHandler.getFileForTable(dataContext, table.getName());

			// Keep track of rows and data size for this table
			TableTracker tracker = new TableTracker();

			// Convert JDBC metadata into Column objects
			List<Column> columns = getOrderedColumnsFromMetadata(rs.getMetaData(), table);

			// Setup some storage for the data coming out of the table
			List<List<String>> tableData = new ArrayList<List<String>>();

			// Flag to indicate we've started processing data from this table (ie it has at least one row)
			boolean started = false;

			// Iterate through the rows of the table
			while (rs.next()) {

				// In the context of a data dump, processing 1 row == progress
				tableContext.getInformer().incrementProgress();

				// If we get here, the table has at least one row and thus a corresponding MPX file will get created
				// Do some one-time-only file system preparation
				if (!started) {

					// Open an output stream to the file
					out = new BufferedOutputStream(FileUtils.openOutputStream(outputFile));

					// Print the header row
					DumpProgress startProgress = getDumpProgress(out, columns, tableData, dataContext, tableContext, tracker);
					DataHandler.startData(startProgress);

					// Only do this once
					started = true;
				}

				// Extract one complete row of data and add it to our list
				List<String> rowData = getRowData(DataHandler.MPX_DATE_FORMAT, table.getName(), rs, columns);
				tableData.add(rowData);

				// Bump our row counts
				tracker.getCurrentRowCount().increment();
				tracker.getTotalRowCount().increment();

				// Bump our size counts
				long rowSize = getSize(rowData);
				tracker.getCurrentDataSize().increment(rowSize);
				tracker.getTotalDataSize().increment(rowSize);

				// We've exceeded either 50 rows or 50k in data while processing this table
				if (isIntervalLimitExceeded(tracker, dataContext)) {

					// Dump the data we have in memory out to disk
					DumpProgress dataProgress = getDumpProgress(out, columns, tableData, dataContext, tableContext, tracker);
					DataHandler.doData(dataProgress);

					// Clear out our memory storage
					tableData.clear();

					// Reset the counters tracking how much data we have in memory
					tracker.setCurrentDataSize(new LongCounter());
					tracker.setCurrentRowCount(new LongCounter());
				}
			}

			// We've finished iterating over the ResultSet, might be some cleanup yet to do
			DumpProgress finished = getDumpProgress(out, columns, tableData, dataContext, tableContext, tracker);
			DataHandler.finishData(finished);

			// Store some results about the processing of this table
			DumpTableResult result = new DumpTableResult();
			result.setTableContext(tableContext);
			result.setRows(tracker.getTotalRowCount().getValue());
			result.setSize(tracker.getTotalDataSize().getValue());

			// Set the file reference only if the table had at least one row and thus a file was actually created
			if (tracker.getTotalRowCount().getValue() > 0) {
				result.setFiles(Collections.singletonList(outputFile));
			} else {
				List<File> empty = Collections.emptyList();
				result.setFiles(empty);
			}

			return result;
		} catch (SQLException e) {
			throw new IllegalStateException("Unexpected SQL error");
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error");
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * Return true if we have processed 50 rows or 50k of data, whichever comes first.
	 */
	protected boolean isIntervalLimitExceeded(TableTracker tracker, DumpDataContext context) {
		if (tracker.getCurrentRowCount().getValue() > context.getRowCountInterval()) {
			return true;
		}
		if (tracker.getCurrentDataSize().getValue() > context.getDataSizeInterval()) {
			return true;
		}
		return false;
	}

	/**
	 * Convert the data from the row into String form
	 */
	protected List<String> getRowData(String dateFormat, String tableName, ResultSet rs, List<Column> columns) throws SQLException {
		// Allocate some storage
		List<String> rowData = new ArrayList<String>();

		// Cycle through the columns
		for (int i = 0; i < columns.size(); i++) {

			// Extract the column we are working with
			Column column = columns.get(i);

			// ResultSet column indexes are one based not zero based
			int resultSetColumnIndex = i + 1;

			// Use JDBC to turn the value being held by the database into a Java string
			// The import side of Impex must be able to convert this string back into the native value without losing data
			// TODO Refactor things into a Converter API of some kind
			// TODO Need a richer API for dealing with the conversion of database values to Java strings
			// TODO This would allow for vastly superior handling of date/timestamp/timezone matters (among other things)
			String columnValue = getColumnValueAsString(dateFormat, rs, resultSetColumnIndex, column);

			// Add this columns value to the row data
			rowData.add(columnValue);
		}

		return rowData;
	}

	/**
	 * Convert a CLOB to a String
	 */
	protected String getClob(Clob clob) throws SQLException {
		Reader r = null;
		try {
			StringBuilder sb = new StringBuilder();
			r = clob.getCharacterStream();
			char[] buffer = new char[4096];
			int len;
			while ((len = r.read(buffer)) != -1) {
				sb.append(buffer, 0, len);
			}
			return sb.toString();
		} catch (IOException e) {
			throw new SQLException(e);
		} finally {
			IOUtils.closeQuietly(r);
		}
	}

	/**
	 * Use JDBC to extract the data held by the database into a <code>java.lang.String</code> suitable for dumping to disk. The String returned by this method must be completely
	 * disconnected from the ResultSet and database. Once this method returns, invoking a method on the underlying ResultSet or otherwise contacting the database to assist with
	 * processing the data held in this row/column is forbidden.
	 */
	protected String getColumnValueAsString(String dateFormat, ResultSet rs, int index, Column column) throws SQLException {
		// Clob's and Date's need special handling
		switch (column.getType()) {
		case CLOB:
			Clob clob = rs.getClob(index);
			if (clob == null) {
				return null;
			} else {
				return getClob(clob);
			}
		case DATE:
		case TIMESTAMP:
			Timestamp date = rs.getTimestamp(index);
			if (date == null) {
				return null;
			} else {
				// New instance every single time because
				// 1 - SimpleDateFormat is not thread safe AND
				// 2 - FastDateFormat does not format UTC offset the same way SimpleDateFormat does
				// That combination makes date handling *much* tougher than it needs to be.
				// There should be a simple, easy, thread safe way to both parse and format dates using a single pattern without requiring
				// a new instance of an object for every single column in every single row of every single table that holds a date value
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				return sdf.format(date);
			}
		default:
			// Otherwise just invoke toString() on the object
			Object object = rs.getObject(index);
			if (object == null) {
				return null;
			} else {
				return object.toString();
			}
		}
	}

	protected long getTotalRowCount(List<DumpTableContext> contexts) {
		long rowCount = 0;
		for (DumpTableContext context : contexts) {
			rowCount += context.getRowCount();
		}
		// In the absence of a reasonably accurate estimate for the number of rows contained in the db,
		// just print a dot to the console every time we successfully dump 1,000 rows
		return rowCount == 0 ? DEFAULT_DATABASE_ROW_COUNT : rowCount;
	}

	@Override
	public List<DumpTableResult> dumpTables(DumpDataContext context, Schema schema) {

		logger.info("[data:dump:starting]");
		List<DumpTableContext> tableContexts = new ArrayList<DumpTableContext>();

		Collection<Table> includedTables = DumpUtils.getIncludedElements(context.getTableNameFilter(), schema.getTables());

		logger.debug("includedTables.size=" + includedTables.size());

		Properties tableStatistics = PropertyUtils.loadOrCreateSilently(context.getTableStatisticsLocation());

		// Print a dot any time we complete 1% of our requests
		PercentCompleteInformer informer = new PercentCompleteInformer();

		// create table contexts
		for (Table t : includedTables) {
			DumpTableContext tableContext = new DumpTableContext();
			tableContext.setTable(t);
			tableContext.setInformer(informer);
			DumpUtils.populateTableStatistics(tableStatistics, t, tableContext);
			tableContexts.add(tableContext);
		}

		long totalRowCount = getTotalRowCount(tableContexts);

		List<DumpTableResult> results = new ArrayList<DumpTableResult>();

		informer.setTotal(totalRowCount);

		// Each bucket holds a bunch of requests
		List<DumpTableBucket> buckets = getTableBuckets(tableContexts, context, results, informer);

		logger.debug("buckets.size()=" + buckets.size());

		// Create and invoke threads to fill in the metadata
		// Store some context for the thread handler
		ThreadHandlerContext<DumpTableBucket> thc = new ThreadHandlerContext<DumpTableBucket>();
		thc.setList(buckets);
		thc.setHandler(new DumpTableBucketHandler());
		thc.setMax(buckets.size());
		thc.setMin(buckets.size());
		thc.setDivisor(1);

		// Start threads to to concurrently dump data from 15 tables at a time
		informer.start();
		ExecutionStatistics stats = new ThreadInvoker().invokeThreads(thc);
		informer.stop();

		String tables = FormatUtils.getCount(getDumpedTableCount(results));
		String rows = FormatUtils.getCount(getDumpResultRows(results));
		String size = FormatUtils.getSize(getDumpResultSize(results));
		String time = FormatUtils.getTime(stats.getExecutionTime());
		Object[] args = { tables, rows, size, time };
		logger.info("[data:dump:complete] - [tables: {}  rows: {}  size: {}] - {}", args);
		return results;
	}

	protected long getDumpedTableCount(List<DumpTableResult> results) {
		long count = 0;
		for (DumpTableResult result : results) {
			if (result.getRows() > 0) {
				count++;
			}
		}
		return count;
	}

	protected long getDumpResultRows(List<DumpTableResult> results) {
		long rows = 0;
		for (DumpTableResult result : results) {
			rows += result.getRows();
		}
		return rows;
	}

	protected long getDumpResultSize(List<DumpTableResult> results) {
		long size = 0;
		for (DumpTableResult result : results) {
			size += result.getSize();
		}
		return size;
	}

	protected List<DumpTableBucket> getTableBuckets(List<DumpTableContext> tables, DumpDataContext context, List<DumpTableResult> results, PercentCompleteInformer progressTracker) {
		// number of buckets equals thread count, unless thread count > total number of sources
		int bucketCount = Math.min(context.getDataThreads(), tables.size());
		// Sort the sources by size
		Collections.sort(tables);
		// Largest to smallest instead of smallest to largest
		Collections.reverse(tables);
		// Allocate some buckets to hold the sql
		List<DumpTableBucket> buckets = CollectionUtils.getNewList(DumpTableBucket.class, bucketCount);
		// Distribute the sources into buckets as evenly as possible
		// "Evenly" in this case means the total number of rows for all of the tables in each bucket should be roughly the same
		for (DumpTableContext table : tables) {
			// Sort the buckets by size
			Collections.sort(buckets);
			// First bucket in the list is the smallest
			DumpTableBucket smallest = buckets.get(0);
			// Add this source to the bucket
			smallest.getTables().add(table);
			// Update the bucket metadata holding overall size
			smallest.setRowCount(smallest.getRowCount() + table.getRowCount());
			smallest.setSize(smallest.getSize() + table.getSize());
		}
		for (DumpTableBucket bucket : buckets) {
			bucket.setContext(context);
			bucket.setService(this);
			bucket.setResults(results);
			// Randomize the order in which tables get dumped
			Collections.shuffle(bucket.getTables());
		}
		return buckets;
	}

	/**
	 * Generate a SQL statement that selects all data from the table
	 */
	protected String getSelectAllQuery(Table table) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ");
		sb.append(table.getName());
		sb.append(" ORDER BY 'x'");

		String primaryKeyString = ModelUtils.getCsvPrimaryKeyColumnNames(table);

		if (!StringUtils.isBlank(primaryKeyString)) {
			// Order by primary keys (if any) so the data always comes out in a deterministic order
			sb.append(", ");
			sb.append(primaryKeyString);
		}

		return sb.toString();
	}

	/**
	 * Convert the JDBC metadata into a list of <code>Column</code> objects ordered in exactly the same way as the metadata
	 */
	protected List<Column> getOrderedColumnsFromMetadata(ResultSetMetaData md, Table t) throws SQLException {
		List<Column> columns = new ArrayList<Column>();
		Map<String, Column> columnsByName = ModelUtils.getColumnNameMap(t);
		for (int i = 1; i <= md.getColumnCount(); i++) {
			String colName = md.getColumnName(i);
			columns.add(columnsByName.get(colName));
		}
		return columns;
	}

	protected DumpProgress getDumpProgress(OutputStream out, List<Column> columns, List<List<String>> data, DumpDataContext ddc, DumpTableContext dtc, TableTracker tracker) {
		DumpProgress progress = new DumpProgress();
		progress.setOutputStream(out);
		progress.setColumns(columns);
		progress.setCurrentData(data);
		progress.setContext(ddc);
		progress.setTableContext(dtc);
		progress.setTableTracker(tracker);
		return progress;
	}

	protected long getSize(List<String> data) {
		long size = 0;
		for (String s : data) {
			size += ((s == null) ? 0 : s.length());
		}
		return size;
	}
}
