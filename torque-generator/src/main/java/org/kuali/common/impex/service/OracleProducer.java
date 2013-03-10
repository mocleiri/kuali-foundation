package org.kuali.common.impex.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.SchemaType;
import org.apache.torque.engine.database.model.Table;
import org.kuali.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OracleProducer extends AbstractSqlProducer {

	private static final Logger logger = LoggerFactory.getLogger(OracleProducer.class);

	private static final String INSERT_PREFIX = "INSERT ALL\n";
	private static final String INDENT = "  ";
	private static final String INTO_PREFIX = "INTO ";
	private static final String VALUES_PREFIX = " VALUES ";
	private static final String SPACE = " ";
	private static final String LF = "\n";
	private static final String ARG_LIST_START = "(";
	private static final String ARG_LIST_END = ")";
	private static final String DATE_VALUE_PREFIX = "TO_DATE( '";
	private static final String DATE_VALUE_SUFFIX = "', 'YYYYMMDDHH24MISS' )";

	private static final String BATCH_SEPARATOR = "SELECT * FROM DUAL\n";

	private static final String CLOB_PLACEHOLDER = "EMPTY_CLOB()";
	private static final int CLOB_BATCH_SIZE = 4000;

	private static final String CLOB_BATCH_HEADER_PREFIX = "DECLARE    data CLOB; buffer VARCHAR2(32000);\nBEGIN\n    SELECT ";
	private static final String CLOB_BATCH_HEADER_MIDDLE = " INTO data FROM ";
	private static final String CLOB_BATCH_HEADER_SUFFIX = " \n    WHERE \n";
	private static final String EQUALITY_EXPRESSION = " = ";
	private static final String WHERE_CLAUSE_DELIMITER = " AND ";
	private static final String CLOB_DATA_PREFIX = "    \n    FOR UPDATE;        \n    buffer := '";
	private static final String CLOB_DATA_SUFFIX = "';\n" + "    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);\n" + "END;\n";

	@Override
	public List<String> getSql(Table table, BufferedReader reader) throws IOException {

		// Allocate some storage for the SQL we are generating
		List<String> sql = new ArrayList<String>();

		// Extract the list of columns
		List<Column> columns = ImpexUtils.getColumns(table);

		// Determine if there are clob's
		boolean hasClobColumns = hasClobColumns(columns);

		// Allocate storage for clobs longer than 4K
		List<LongClob> longClobs = new ArrayList<LongClob>();

		// Use a StringBuilder to hold the batch insert statement
		StringBuilder batchInsert = new StringBuilder();

		// Extract one line from the .mpx file
		String line = readLineSkipHeader(reader);

		// Keep track of the number of rows we've processed
		int rowCount = 0;

		// Insert the SQL prefix for Oracle insert's
		batchInsert.append(INSERT_PREFIX);

		// Iterate through the .mpx file
		for (;;) {

			// We hit the end of the .mpx file
			if (line == null) {
				break;
			}

			// Convert the line of text from the .mpx file into strings
			String[] tokens = MpxParser.parseMpxLine(line);

			// Convert the strings into DataBeans
			List<DataBean> rowBeans = buildRowData(columns, tokens);

			// Create SQL from the row beans
			batchInsert.append(buildBatchSql(table, rowBeans));

			// increment our row counter
			rowCount++;

			// Tables with CLOB columns may require special handling
			if (hasClobColumns) {

				// Figure out what the primary key's are
				List<DataBean> primaryKeys = getPrimaryKeys(rowBeans);

				// Check for CLOB's longer than 4K and add them to our list
				addLongClobs(rowBeans, primaryKeys, longClobs);

				// If we found any CLOB's longer than 4K we are done batching
				// Break out of the loop that processes lines from the .mpx file
				if (!CollectionUtils.isEmpty(longClobs)) {
					break;
				}
			}

			// Use the length of the SQL + the length of the batch separator to figure out if we have exceeded our batch length
			int length = batchInsert.length() + BATCH_SEPARATOR.length();
			if (batchLimitReached(rowCount, length)) {
				break;
			}

			// read the next line and start the loop over
			line = reader.readLine();
		}

		// Add the batch separator
		batchInsert.append(BATCH_SEPARATOR);

		// Add the batch SQL insert statement to our results
		sql.add(batchInsert.toString());

		// Add SQL for long clobs if needed
		if (!CollectionUtils.isEmpty(longClobs)) {
			logger.info("Converting {} long CLOB's into SQL", longClobs.size());
			List<String> clobSql = getClobSql(longClobs, table);
			logger.info("Adding {} CLOB SQL statements", clobSql.size());
			sql.addAll(clobSql);
		}

		if (rowCount == 0) {
			// return null to indicate no rows were processed
			return null;
		} else {
			// return the list of SQL we generated
			return sql;
		}
	}

	/**
	 * Convert LongClob objects into SQL
	 */
	protected List<String> getClobSql(List<LongClob> longClobs, Table table) {
		List<String> clobList = new ArrayList<String>();
		String clobSql = continueClob(table, longClobs);
		while (clobSql != null) {
			clobList.add(clobSql);
			clobSql = continueClob(table, longClobs);
		}
		return clobList;
	}

	@Override
	protected String getEscapedValue(Column column, String token) {
		if (isDataBigClob(token, column)) {
			return token;
		} else {
			String escaped = StringUtils.replace(token, "'", "''");
			return "'" + escaped + "'";
		}
	}

	protected boolean isColumnClobType(Column column) {
		return ImpexUtils.getColumnType(column).equals(SchemaType.CLOB);
	}

	protected String continueClob(Table table, List<LongClob> longClobRows) {

		if (CollectionUtils.isEmpty(longClobRows)) {
			return null;
		}

		// find the next clob to work on
		LongClob currentClob = longClobRows.get(0);
		String clobChunk = currentClob.clobChunks.pop();

		SimpleDateFormat dateFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
		StringBuilder sqlBuilder = new StringBuilder();

		// handle the clob data that is too long
		List<DataBean> primaryKeys = currentClob.primaryKeys;

		// write sql that will append the current chunk into the clob column
		sqlBuilder.append(CLOB_BATCH_HEADER_PREFIX).append(currentClob.column.getName()).append(CLOB_BATCH_HEADER_MIDDLE).append(table.getName()).append(CLOB_BATCH_HEADER_SUFFIX);

		String clauseDelimiter = "";
		for (DataBean pk : primaryKeys) {
			sqlBuilder.append(clauseDelimiter);
			sqlBuilder.append(SPACE).append(pk.getColumn().getName()).append(EQUALITY_EXPRESSION).append(getSqlValue(pk, dateFormat));

			clauseDelimiter = WHERE_CLAUSE_DELIMITER;
		}

		sqlBuilder.append(CLOB_DATA_PREFIX).append(clobChunk).append(CLOB_DATA_SUFFIX);

		// check to see if we have more clobs to process for this mpx row
		if (currentClob.clobChunks.isEmpty()) {
			longClobRows.remove(0);
		}

		return sqlBuilder.toString();

	}

	/**
	 * Split a long data string into clob chunks, which have a maximum size of CLOB_BATCH_SIZE and have all single quotes "'" replaced with the escaped version (two single quotes,
	 * "''")
	 * 
	 * @param value
	 *            the full data string
	 * @return a list of strings representing the full data split into chunks
	 */
	protected List<String> chunkClob(String value) {
		List<String> results = new ArrayList<String>();

		String currentValue = value;
		while (currentValue.length() > CLOB_BATCH_SIZE) {
			String chunk = currentValue.substring(0, CLOB_BATCH_SIZE);

			// escape all single quotes
			chunk = chunk.replace("'", "''");
			results.add(chunk);
			currentValue = currentValue.substring(CLOB_BATCH_SIZE);
		}

		if (StringUtils.isNotEmpty(currentValue)) {
			results.add(currentValue.replace("'", "''"));
		}

		return results;
	}

	protected String buildBatchSql(Table table, List<DataBean> dataBeans) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
		StringBuilder sqlBuilder = new StringBuilder();

		List<String> values = new ArrayList<String>(dataBeans.size());
		for (DataBean data : dataBeans) {
			values.add(getSqlValue(data, dateFormat));
		}

		// output looks like "  INSERT INTO FOO_BAR_T (FOO, BAR, BAZ)"
		sqlBuilder.append(INDENT).append(INTO_PREFIX).append(table.getName()).append(SPACE).append(ARG_LIST_START).append(getColumnNamesCSV(table)).append(ARG_LIST_END);
		// output looks like "  VALUES ('Test', 1, 2)"
		sqlBuilder.append(VALUES_PREFIX).append(ARG_LIST_START).append(CollectionUtils.getCSV(values)).append(ARG_LIST_END);
		sqlBuilder.append(LF);

		return sqlBuilder.toString();
	}

	protected String getSqlValue(DataBean data, SimpleDateFormat dateFormat) {
		StringBuilder result = new StringBuilder();

		if (data.getDateValue() != null) {
			result.append(DATE_VALUE_PREFIX);
			result.append(dateFormat.format(data.getDateValue()));
			result.append(DATE_VALUE_SUFFIX);
		} else if (isDataBigClob(data.getValue(), data.getColumn())) {
			// if the data type is CLOB, and the data is longer than the batch size, the value should be handled by the CLOB-splitting code
			result.append(CLOB_PLACEHOLDER);
		} else if (isColumnClobType(data.getColumn()) && data.getValue() == null) {
			// if the data type is CLOB and the value is null, return the EMPTY_CLOB placeholder, since Oracle doesn't like NULL in a CLOB column
			result.append(CLOB_PLACEHOLDER);
		} else {
			result.append(data.getValue());
		}

		return result.toString();
	}

	protected class LongClob {
		Deque<String> clobChunks;
		Column column;
		List<DataBean> primaryKeys;
	}

	protected boolean isDataBigClob(String value, Column column) {
		if (value == null) {
			return false;
		}

		return isColumnClobType(column) && value.length() > CLOB_BATCH_SIZE;
	}

	protected boolean hasClobColumns(List<Column> columns) {
		for (Column col : columns) {
			if (isColumnClobType(col)) {
				return true;
			}
		}
		return false;
	}

	protected List<DataBean> getPrimaryKeys(List<DataBean> rowBeans) {
		List<DataBean> primaryKeys = new ArrayList<DataBean>();
		// first find the primary keys
		for (DataBean data : rowBeans) {
			// if the column is a primary key, add it to the tracked list
			if (data.getColumn().isPrimaryKey()) {
				primaryKeys.add(data);
			}
		}
		return primaryKeys;
	}

	protected void addLongClobs(List<DataBean> rowBeans, List<DataBean> primaryKeys, List<LongClob> longClobs) {
		// now loop trough data beans again and add LongClob entries
		for (DataBean data : rowBeans) {
			// if the column is a CLOB type, and the data string is long enough,
			// add the data bean to the list of clobs that need to be split up
			if (isDataBigClob(data.getValue(), data.getColumn())) {
				List<String> clobChunks = chunkClob(data.getValue());

				LongClob longClob = new LongClob();
				longClob.column = data.getColumn();
				longClob.clobChunks = new ArrayDeque<String>();
				longClob.clobChunks.addAll(clobChunks);
				longClob.primaryKeys = primaryKeys;
				longClobs.add(longClob);
			}
		}
	}

}
