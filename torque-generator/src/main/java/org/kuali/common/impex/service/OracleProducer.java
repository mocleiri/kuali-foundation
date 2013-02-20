package org.kuali.common.impex.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.SchemaType;
import org.apache.torque.engine.database.model.Table;
import org.kuali.common.util.CollectionUtils;

public class OracleProducer extends AbstractSqlProducer {

	private static final String INSERT_PREFIX = "INSERT ALL\n";
	private static final String INDENT = "  ";
	private static final String INTO_PREFIX = "INTO ";
	private static final String VALUES_PREFIX = "VALUES ";
	private static final String SPACE = " ";
	private static final String LF = "\n";
	private static final String ARG_LIST_START = "(";
	private static final String ARG_LIST_END = ")";
	private static final String DATE_VALUE_PREFIX = "TO_DATE( '";
	private static final String DATE_VALUE_SUFFIX = "', 'YYYYMMDDHH24MISS' )";

	private static final String BATCH_SEPARATOR = "SELECT * FROM DUAL\n/\n";

	private static final String CLOB_PLACEHOLDER = "EMPTY_CLOB()";
	private static final int CLOB_BATCH_SIZE = 4000;

	private static final String CLOB_BATCH_HEADER_PREFIX = "DECLARE    data CLOB; buffer VARCHAR2(32000);\nBEGIN\n    SELECT ";
	private static final String CLOB_BATCH_HEADER_MIDDLE = " INTO data FROM ";
	private static final String CLOB_BATCH_HEADER_SUFFIX = " \n    WHERE \n";
	private static final String EQUALITY_EXPRESSION = " = ";
	private static final String WHERE_CLAUSE_DELIMITER = " AND ";
	private static final String CLOB_DATA_PREFIX = "    \n    FOR UPDATE;        \n    buffer := '";
	private static final String CLOB_DATA_SUFFIX = "';\n" + "    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);\n" + "END;\n" + "/\n";
	private static final String CLOB_LENGTH_COMMENT = "-- Length: ";
	private static final String CLOB_CHUNKS_COMMENT = "-- Chunks: ";

	@Override
	public String getSql(Table table, BufferedReader reader) throws IOException {
		boolean hasClobColumns = false;
		List<Column> columns = ImpexUtils.getColumns(table);
		for (Column col : columns) {
			if (isColumnClobType(col)) {
				hasClobColumns = true;
			}
		}

		List<LongClobRow> longClobs = null;
		if (hasClobColumns) {
			longClobs = new ArrayList<LongClobRow>();
		}

		StringBuilder sqlBuilder = new StringBuilder();
		String line = readLineSkipHeader(reader);
		int rowCount = 0;
		// Iterate through the .mpx file
		for (;;) {

			// We hit the end of the .mpx file
			if (line == null) {
				break;
			}

			// Convert the tokens from the .mpx file into RowData
			List<DataBean> rowBeans = buildRowData(columns, getMpxParser().parseMpxLine(line));

			sqlBuilder.append(buildBatchSql(table, rowBeans));
			rowCount++;

            // if the table has any CLOB columns, we need to handle those separately
			if (hasClobColumns) {
				List<DataBean> clobs = new ArrayList<DataBean>();
				List<DataBean> primaryKeys = new ArrayList<DataBean>();
				for (DataBean data : rowBeans) {
					// if the column is a CLOB type, and the data string is long enough,
					// add the data bean to the list of clobs that need to be split up
					if (isColumnClobType(data.getColumn()) && data.getValue().length() > CLOB_BATCH_SIZE) {
						clobs.add(data);
					}

					// if the column is a primary key, add it to the tracked list
					if (data.getColumn().isPrimaryKey()) {
						primaryKeys.add(data);
					}
				}
				LongClobRow longClob = new LongClobRow();
				longClob.longClobs = clobs;
				longClob.primaryKeys = primaryKeys;
				longClobs.add(longClob);
			}

			// include the length of the batch separator to the total length of sql so far,
			// to determine if we have reached the end of a batch
			if (batchLimitReached(rowCount, (sqlBuilder.length() + BATCH_SEPARATOR.length()))) {
				break;
			}

			// read the next line and start the loop over
			line = reader.readLine();
		}

		sqlBuilder.append(BATCH_SEPARATOR);

		if (hasClobColumns && !longClobs.isEmpty()) {
			sqlBuilder.append(buildClobBatches(table, longClobs));
		}

		// return null to indicate no rows were processed
		if (rowCount == 0) {
			return null;
		} else {
			return sqlBuilder.toString();
		}
	}

	@Override
	protected String getEscapedValue(String token) {
		String escaped = StringUtils.replace(token, "'", "''");
		return "'" + escaped + "'";
	}

	private boolean isColumnClobType(Column column) {
		return ImpexUtils.getColumnType(column).equals(SchemaType.CLOB);
	}

	private String buildClobBatches(Table table, List<LongClobRow> longClobRows) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
		StringBuilder sqlBuilder = new StringBuilder();

		// handle the clob data that is too long
		for (LongClobRow clobRow : longClobRows) {
			List<DataBean> primaryKeys = clobRow.primaryKeys;
			List<DataBean> longClobs = clobRow.longClobs;

			for (DataBean data : longClobs) {

				// the number of 4000 charachter chunks in the data
				List<String> dataChunks = chunkClob(data.getValue());
				sqlBuilder.append(buildClobHeader(dataChunks, data));

				// for each chunk, write sql that will append the chunk into the clob column
				for (String chunk : dataChunks) {
					sqlBuilder.append(CLOB_BATCH_HEADER_PREFIX).append(data.getColumn().getName()).append(CLOB_BATCH_HEADER_MIDDLE).append(table.getName())
					        .append(CLOB_BATCH_HEADER_SUFFIX);

					String clauseDelimiter = "";
					for (DataBean pk : primaryKeys) {
						sqlBuilder.append(clauseDelimiter);
						sqlBuilder.append(SPACE).append(pk.getColumn().getName()).append(EQUALITY_EXPRESSION).append(getSqlValue(pk, dateFormat));

						clauseDelimiter = WHERE_CLAUSE_DELIMITER;
					}

					sqlBuilder.append(CLOB_DATA_PREFIX).append(chunk).append(CLOB_DATA_SUFFIX);

				}
			}
		}

		return sqlBuilder.toString();

	}

	private String buildClobHeader(List<String> dataChunks, DataBean data) {
		StringBuilder headerBuilder = new StringBuilder();
		headerBuilder.append(CLOB_LENGTH_COMMENT).append(data.getValue().length()).append(LF);
		headerBuilder.append(CLOB_CHUNKS_COMMENT).append(dataChunks.size()).append(LF);

		return headerBuilder.toString();
	}

	/**
	 * Split a long data string into clob chunks, which have a maximum size of CLOB_BATCH_SIZE and have all single quotes "'" replaced with
	 * the escaped version (two single quotes, "''")
	 *
	 * @param value
	 *            the full data string
	 * @return a list of strings representing the full data split into chunks
	 */
	private List<String> chunkClob(String value) {
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
			results.add(currentValue);
		}

		return results;
	}

	private String buildBatchSql(Table table, List<DataBean> dataBeans) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(INSERT_PREFIX);

		List<String> values = new ArrayList<String>(dataBeans.size());
		for (DataBean data : dataBeans) {
			values.add(getSqlValue(data, dateFormat));
		}

		// output looks like "  INSERT INTO FOO_BAR_T (FOO, BAR, BAZ)"
		sqlBuilder.append(INDENT).append(INTO_PREFIX).append(table.getName()).append(SPACE).append(ARG_LIST_START).append(getColumnNamesCSV(table)).append(ARG_LIST_END);
		sqlBuilder.append(LF);
		// output looks like "  VALUES ('Test', 1, 2)"
		sqlBuilder.append(INDENT).append(VALUES_PREFIX).append(ARG_LIST_START).append(CollectionUtils.getCSV(values)).append(ARG_LIST_END);
		sqlBuilder.append(LF);

		return sqlBuilder.toString();
	}

	private String getSqlValue(DataBean data, SimpleDateFormat dateFormat) {
		StringBuilder result = new StringBuilder();

		if (data.getDateValue() != null) {
			result.append(DATE_VALUE_PREFIX);
			result.append(dateFormat.format(data.getDateValue()));
			result.append(DATE_VALUE_SUFFIX);
		}
		// if the data type is CLOB, and the data is longer than the batch size, the value should be handled by the CLOB-splitting code
		else if (isColumnClobType(data.getColumn()) && data.getValue().length() > CLOB_BATCH_SIZE) {
			result.append(CLOB_PLACEHOLDER);
		} else {
			result.append(data.getValue());
		}

		return result.toString();
	}

	private class LongClobRow {
		List<DataBean> longClobs;
		List<DataBean> primaryKeys;
	}
}
