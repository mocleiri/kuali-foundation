package org.kuali.common.impex.data.impl.oracle;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.data.MpxHeaderData;
import org.kuali.common.impex.data.impl.AbstractSqlProducer;
import org.kuali.common.impex.data.impl.DataBean;
import org.kuali.common.impex.data.impl.MpxParser;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.model.Table;
import org.kuali.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OracleProducer extends AbstractSqlProducer {

	public static final String SUPPORTED_VENDOR = "oracle";

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
	public List<String> getSql(Table table, MpxHeaderData headerData, BufferedReader reader) throws IOException {
		logger.debug("Producing SQL for table [{}]", table.getName());

		// Allocate some storage for the SQL we are generating
		List<String> sql = new ArrayList<String>();

		// Extract the list of columns
		List<Column> columns = table.getColumns();

		// Determine if there are clob's
		boolean hasClobColumns = hasClobColumns(columns);

		// Allocate storage for clobs longer than 4K
		List<OracleLongClob> longClobs = new ArrayList<OracleLongClob>();

		// Use a StringBuilder to hold the batch insert statement
		StringBuilder batchInsert = new StringBuilder();

		// read the first line
		String line = reader.readLine();

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
			List<DataBean> rowBeans = buildRowData(columns, tokens, headerData);

			// Create SQL from the row beans
			batchInsert.append(buildBatchSql(table, rowBeans, headerData));

			// increment our row counter
			rowCount++;

			// Tables with CLOB columns may require special handling
			if (hasClobColumns && addedLongClobs(rowBeans, longClobs)) {
				// We found at least one CLOB longer than 4K
				// Break out of the batching loop to handle the CLOB's
				break;
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
			List<String> clobSql = getClobSql(longClobs, table);
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

	protected boolean addedLongClobs(List<DataBean> rowBeans, List<OracleLongClob> longClobs) {

		// Figure out what the primary key's are
		List<DataBean> primaryKeys = getPrimaryKeys(rowBeans);

		// Check for CLOB's longer than 4K and add them to our list
		addLongClobs(rowBeans, primaryKeys, longClobs);

		// If we found any CLOB's longer than 4K we are done batching
		// Return true to indicate that large clob handling needs to take place
		return !CollectionUtils.isEmpty(longClobs);
	}

	/**
	 * Convert LongClob objects into SQL
	 */
	protected List<String> getClobSql(List<OracleLongClob> longClobs, Table table) {
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
		return column.getColumnDataType() == DataType.CLOB;
	}

	protected String continueClob(Table table, List<OracleLongClob> longClobRows) {

		if (CollectionUtils.isEmpty(longClobRows)) {
			return null;
		}

		// find the next clob to work on
		OracleLongClob currentClob = longClobRows.get(0);
		String clobChunk = currentClob.getClobChunks().pop();

		SimpleDateFormat dateFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
		StringBuilder sb = new StringBuilder();

		// Handle CLOB data longer than 4K
		List<DataBean> primaryKeys = currentClob.getPrimaryKeys();

		// Create SQL to append the current chunk into the clob column
		sb.append(CLOB_BATCH_HEADER_PREFIX);
		sb.append(currentClob.getColumn().getName());
		sb.append(CLOB_BATCH_HEADER_MIDDLE);
		sb.append(table.getName());
		sb.append(CLOB_BATCH_HEADER_SUFFIX);

		String clauseDelimiter = "";
		for (DataBean pk : primaryKeys) {
			sb.append(clauseDelimiter);
			sb.append(SPACE);
			sb.append(pk.getColumn().getName());
			sb.append(EQUALITY_EXPRESSION);
			sb.append(getSqlValue(pk, dateFormat));

			clauseDelimiter = WHERE_CLAUSE_DELIMITER;
		}

		sb.append(CLOB_DATA_PREFIX);
		sb.append(clobChunk);
		sb.append(CLOB_DATA_SUFFIX);

		// check to see if we have more clobs to process for this mpx row
		if (currentClob.getClobChunks().isEmpty()) {
			longClobRows.remove(0);
		}

		return sb.toString();

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

	protected String buildBatchSql(Table table, List<DataBean> dataBeans, MpxHeaderData headerData) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT);

		// Convert the beans into strings
		List<String> values = new ArrayList<String>(dataBeans.size());
		for (DataBean data : dataBeans) {
			values.add(getSqlValue(data, dateFormat));
		}

		// Create SQL from the strings
		// result is -> "  INSERT INTO FOO_BAR_T (FOO, BAR, BAZ) VALUES ('Test', 1, 2)"
		StringBuilder sb = new StringBuilder();
		sb.append(INDENT);
		sb.append(INTO_PREFIX);
		sb.append(table.getName());
		sb.append(SPACE);
		sb.append(ARG_LIST_START);
		sb.append(CollectionUtils.getCSV(headerData.getColumnNames()));
		sb.append(ARG_LIST_END);
		sb.append(VALUES_PREFIX);
		sb.append(ARG_LIST_START);
		sb.append(CollectionUtils.getCSV(values));
		sb.append(ARG_LIST_END);
		sb.append(LF);
		return sb.toString();
	}

	protected String getSqlValue(DataBean data, SimpleDateFormat dateFormat) {
		if (data.getDateValue() != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(DATE_VALUE_PREFIX);
			sb.append(dateFormat.format(data.getDateValue()));
			sb.append(DATE_VALUE_SUFFIX);
			return sb.toString();
		} else if (isDataBigClob(data.getValue(), data.getColumn())) {
			// if the data type is CLOB, and the data is longer than the batch size, the value is handled by the CLOB-splitting code
			return CLOB_PLACEHOLDER;
		} else if (isColumnClobType(data.getColumn()) && data.getValue() == null) {
			// if the data type is CLOB and the value is null, return the EMPTY_CLOB placeholder, since Oracle doesn't like NULL in a CLOB column
			return CLOB_PLACEHOLDER;
		} else {
			return data.getValue();
		}
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

	protected void addLongClobs(List<DataBean> rowBeans, List<DataBean> primaryKeys, List<OracleLongClob> longClobs) {
		// now loop trough data beans again and add LongClob entries
		for (DataBean data : rowBeans) {
			// if the column is a CLOB type, and the data string is long enough,
			// add the data bean to the list of clobs that need to be split up
			if (isDataBigClob(data.getValue(), data.getColumn())) {
				List<String> clobChunks = chunkClob(data.getValue());

				OracleLongClob longClob = new OracleLongClob();
				longClob.setColumn(data.getColumn());
				longClob.setClobChunks(new ArrayDeque<String>());
				longClob.getClobChunks().addAll(clobChunks);
				longClob.setPrimaryKeys(primaryKeys);
				longClobs.add(longClob);
			}
		}
	}

}
