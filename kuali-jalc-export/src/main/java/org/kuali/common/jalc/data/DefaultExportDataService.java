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

package org.kuali.common.jalc.data;

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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.kuali.common.jalc.model.Column;
import org.kuali.common.jalc.model.Table;
import org.kuali.common.jalc.model.util.ModelUtils;
import org.kuali.common.threads.ExecutionStatistics;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PercentCompleteInformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultExportDataService implements ExportDataService {

    protected static final Logger logger = LoggerFactory.getLogger(DefaultExportDataService.class);

    @Override
    public ExportTableResult exportTable(ExportDataContext context, ExportTableContext tableContext, Connection conn) throws ExportDataException {
        Statement stmt = null;
        ResultSet rs = null;
        long start = System.currentTimeMillis();
        try {
            String query = getSelectAllQuery(tableContext.getTable());
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
            ExportTableResult result = dumpTable(context, tableContext, rs);
            result.setFinish(System.currentTimeMillis());
            result.setStart(start);
            result.setElapsed(result.getFinish() - start);
            return result;
        } catch (SQLException e) {
            throw new ExportDataException("SQLException thrown when attempting to retrieve data for export from table: " + tableContext.getTable().getName(), e);
        } finally {
            closeQuietly(rs);
            closeQuietly(stmt);
        }
    }

    protected ExportTableResult dumpTable(ExportDataContext context, ExportTableContext tableContext, ResultSet rs) throws ExportDataException {
        OutputStream out = null;
        try {
            Table table = tableContext.getTable();
            File outFile = DataHandler.getFileForTable(context, table.getName());
            out = new BufferedOutputStream(FileUtils.openOutputStream(outFile));

            long totalDataSize = 0;
            long totalRowCount = 0;
            long currentRowCount = 0;
            long currentDataSize = 0;
            List<Column> orderedColumns = getOrderedColumnsFromMetadata(rs.getMetaData(), tableContext.getTable());

            List<List<String>> data = new ArrayList<List<String>>();
            ExportProgress startProgress = getDumpTableContext(out, orderedColumns, data, currentDataSize, context, currentRowCount, totalRowCount, tableContext, totalDataSize);
            DataHandler.startData(startProgress);
            while (rs.next()) {
                currentRowCount++;
                totalRowCount++;
                List<String> rowData = getRowData(DataHandler.MPX_DATE_FORMAT, table.getName(), rs, orderedColumns, totalRowCount);
                data.add(rowData);
                long rowSize = getSize(rowData);
                currentDataSize += rowSize;
                totalDataSize += rowSize;
                if (currentRowCount > context.getRowCountInterval() || currentDataSize > context.getDataSizeInterval()) {
                    ExportProgress dataProgress = getDumpTableContext(out, orderedColumns, data, currentDataSize, context, currentRowCount, totalRowCount, tableContext, totalDataSize);
                    DataHandler.doData(dataProgress);
                    currentDataSize = 0;
                    currentRowCount = 0;
                    data = new ArrayList<List<String>>();
                }
            }
            ExportProgress finished = getDumpTableContext(out, orderedColumns, data, currentDataSize, context, currentRowCount, totalRowCount, tableContext, totalDataSize);
            DataHandler.finishData(finished);
            ExportTableResult result = new ExportTableResult();
            result.setTableContext(tableContext);
            result.setRows(totalRowCount);
            result.setSize(totalDataSize);
            // set the file reference if a file was actually created
            if (totalRowCount > 0) {
                result.setFiles(Collections.singletonList(outFile));
            } else {
                List<File> empty = Collections.emptyList();
                result.setFiles(empty);
            }

            return result;
        } catch (SQLException e) {
            throw new ExportDataException("SQLException thrown when attempting to get data for table: " + tableContext.getTable().getName(), e);
        } catch (IOException e) {
            throw new ExportDataException("IOException thrown when attempting to export data", e);
        } finally {
            IOUtils.closeQuietly(out);
        }

    }

    /**
     * Convert the data from the row into String form
     */
    protected List<String> getRowData(String dateFormat, String tableName, ResultSet rs, List<Column> columns, long rowCount) throws SQLException {
        // Allocate some storage
        List<String> data = new ArrayList<String>(columns.size());

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
            data.add(getColumnValueAsString(dateFormat, rs, resultSetColumnIndex, column, rowCount, tableName));
        }

        return data;
    }

    /**
     * Convert a CLOB to a String
     */
    protected String getClob(Clob clob) throws SQLException {
        Reader r = null;
        StringBuilder sb = new StringBuilder();
        try {
            r = clob.getCharacterStream();
            char[] buffer = new char[4096];
            int len;
            while ((len = r.read(buffer)) != -1) {
                sb.append(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new SQLException(e);
        } finally {
            IOUtils.closeQuietly(r);
        }
        return sb.toString();
    }

    /**
     * Use JDBC to extract the data held by the database into a <code>java.lang.String</code> suitable for dumping to disk. The String returned by this method must be completely
     * disconnected from the ResultSet and database. Once this method returns, invoking a method on the underlying ResultSet or otherwise contacting the database to assist with
     * processing the data held in this row/column is forbidden.
     */
    protected String getColumnValueAsString(String dateFormat, ResultSet rs, int index, Column column, long rowCount, String tableName) {
        try {
            // Clob's and Date's need special handling
            switch (column.getColumnDataType()) {
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
                        // 1 - SimpleDateFormat is not threadsafe AND
                        // 2 - FasteDateFormat does not format UTC offset the same way SimpleDateFormat does
                        // That combo of things makes it impossible to use a single pattern to express how dates should be both formatted
                        // and parsed
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
        } catch (Exception e) {
            // Don't let an issue extracting one value from one column in one row stop the process
            // Log the table/row/column and continue
            logger.warn("Unexpected error reading row " + rowCount + " column " + column.getName() + " from " + tableName);
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<ExportTableResult> exportTables(ExportDataContext context) {
        List<ExportTableResult> results = new ArrayList<ExportTableResult>();

        // Print a dot any time we complete 1% of our requests
        PercentCompleteInformer progressTracker = new PercentCompleteInformer();
        progressTracker.setTotal(context.getTableContexts().size());

        // Each bucket holds a bunch of requests
        List<ExportTableBucket> buckets = getTableBuckets(context.getTableContexts(), context, results, progressTracker);

        // Create and invoke threads to fill in the metadata
        // Store some context for the thread handler
        ThreadHandlerContext<ExportTableBucket> thc = new ThreadHandlerContext<ExportTableBucket>();
        thc.setList(buckets);
        thc.setHandler(new ExportTableBucketHandler());
        thc.setMax(buckets.size());
        thc.setMin(buckets.size());
        thc.setDivisor(1);

        // Start threads to acquire table metadata concurrently
        ExecutionStatistics stats = new ThreadInvoker().invokeThreads(thc);

        String time = FormatUtils.getTime(stats.getExecutionTime());
        logger.info("Dump tables completed.  Time: {}", time);
        logger.info("Disconnecting from database.");
        return results;
    }

    protected List<ExportTableBucket> getTableBuckets(List<ExportTableContext> tables, ExportDataContext context, List<ExportTableResult> results, PercentCompleteInformer progressTracker) {
        // number of buckets equals thread count, unless thread count > total number of sources
        int bucketCount = Math.min(context.getDataThreads(), tables.size());
        // Sort the sources by size
        Collections.sort(tables);
        // Largest to smallest instead of smallest to largest
        Collections.reverse(tables);
        // Allocate some buckets to hold the sql
        List<ExportTableBucket> buckets = CollectionUtils.getNewList(ExportTableBucket.class, bucketCount);
        // Distribute the sources into buckets as evenly as possible
        // "Evenly" in this case means each bucket should be roughly the same size
        for (ExportTableContext table : tables) {
            // Sort the buckets by size
            Collections.sort(buckets);
            // First bucket in the list is the smallest
            ExportTableBucket smallest = buckets.get(0);
            // Add this source to the bucket
            smallest.getTables().add(table);
            // Update the bucket metadata holding overall size
            smallest.setRowCount(smallest.getRowCount() + table.getRowCount());
            smallest.setSize(smallest.getSize() + table.getSize());
        }
        for (ExportTableBucket bucket : buckets) {
            bucket.setProgressTracker(progressTracker);
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
    protected String getSelectAllQuery(Table table) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(table.getName());
        sb.append(" ORDER BY 'x',");
        // Order by primary keys (if any) so the data always comes out in a deterministic order
        sb.append(ModelUtils.getCsvPrimaryKeyColumnNames(table));

        return sb.toString();
    }

    protected void closeQuietly(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    protected void closeQuietly(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Generate an ordered list of Column objects from the result set metadata
     */
    protected List<Column> getOrderedColumnsFromMetadata(ResultSetMetaData md, Table t) throws SQLException {
        List<Column> results = new ArrayList<Column>(md.getColumnCount());
        Map<String, Column> columnsByName = ModelUtils.getColumnNameMap(t);
        for (int i = 1; i <= md.getColumnCount(); i++) {
            String colName = md.getColumnName(i);
            results.add(columnsByName.get(colName));
        }

        return results;
    }

    protected ExportProgress getDumpTableContext(OutputStream out, List<Column> columns, List<List<String>> data, long cds, ExportDataContext context, long crc, long trc, ExportTableContext table,
                                                   long tds) {
        ExportProgress progress = new ExportProgress();
        progress.setOutputStream(out);
        progress.setColumns(columns);
        progress.setCurrentData(data);
        progress.setCurrentDataSize(cds);
        progress.setContext(context);
        progress.setCurrentRowCount(crc);
        progress.setTotalRowCount(trc);
        progress.setTableContext(table);
        progress.setTotalDataSize(tds);
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
