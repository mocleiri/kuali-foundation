package org.kuali.core.db.torque.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.Table;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

public class MySQLImpexReader implements ImpexReader {

	private static final String QUOTE = "\"";
	private static final String DATE = "DATE";
	private static final String TIMESTAMP = "TIMESTAMP";
	private static final String NULL = "NULL";

	String srcDateFormat = ImpexContext.MPX_DATE_FORMAT;
	String sqlDateFormat = "yyyyMMddHHmmss";
	int maxRows = 50;
	int maxLength = 50 * 1024;

	/**
	 * Read data lines from the .mpx file and combine them into batched up, INSERT INTO sql statements. Individual data lines are merged
	 * together into SQL statements 50 lines at a time or 50K in length whichever comes first.
	 */
	@Override
	public String getInsertSql(Table table, BufferedReader reader) throws IOException {

		// Extract the columns into a list
		List<Column> columns = getColumns(table);

		// Setup some storage
		StringBuilder sb = new StringBuilder();

		// INSERT INTO FOO (BAR1,BAR2) VALUES
		sb.append(getPrefix(table));

		// Track rows processed and total SQL length
		int rows = 0;
		int length = sb.length();

		// Iterate through the .mpx file
		for (;;) {

			// Extract the next line from the reader
			String line = getNextLine(reader);

			// We hit the end of the .mpx file
			if (line == null) {
				break;
			}

			// Convert the line from the mpx file into what MySQL needs
			String fragment = getSqlFragment(columns, line, srcDateFormat, sqlDateFormat);

			// Need to add a comma, unless this is the first set of values
			if (rows != 0) {
				sb.append(",");
			}

			// Enclose the fragment in parenthesis
			sb.append("(");
			sb.append(fragment);
			sb.append(")");

			// increment our counters
			rows++;
			length += fragment.length() + 3; // 2 parenthesis and a comma

			// Have we exceeded any of our limits?
			if (!isProceed(rows, length, maxRows, maxLength)) {
				break;
			}
		}

		if (rows > 0) {
			// We found at least one row to turn into SQL
			return sb.toString();
		} else {
			// Otherwise return null, indicating there is nothing more to process for this ImpexReader
			return null;
		}
	}

	protected boolean isProceed(int rows, int length, int maxRows, int maxLength) {
		if (rows > maxRows) {
			return false;
		}
		if (length > maxLength) {
			return false;
		}
		return true;
	}

	protected String getNextLine(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		if (ImpexUtils.isHeaderLine(line)) {
			return reader.readLine();
		} else {
			return line;
		}
	}

	protected String getSqlFragment(List<Column> columns, String line, String srcDateFormat, String sqlDateFormat) {
		// Remove the .mpx formatting and split the values up into individual tokens
		String[] tokens = ImpexUtils.getOriginalValues(line);
		// Format the raw tokens into SQL appropriate values
		List<String> sqlValues = getSqlValues(columns, tokens, srcDateFormat, sqlDateFormat);
		// Turn them into a comma separated list
		return CollectionUtils.getCSV(sqlValues);
	}

	/**
	 * The strings returned by this method must be suitable for use in an SQL "INSERT INTO" statement without needing any modification. All
	 * characters that need to be escaped must be escaped already, any text values must be enclosed with quotes as appropriate.
	 */
	protected List<String> getSqlValues(List<Column> columns, String[] tokens, String srcDateFormat, String sqlDateFormat) {
		Assert.isTrue(columns.size() == tokens.length);
		List<String> values = new ArrayList<String>();
		for (int i = 0; i < columns.size(); i++) {
			Column column = columns.get(i);
			String token = tokens[i];
			String value = getSqlValue(column, token, srcDateFormat, sqlDateFormat);
			values.add(value);
		}
		return values;
	}

	public String getSqlValue(Column column, String token, String srcDateFormat, String sqlDateFormat) {
		if (token == null) {
			return NULL;
		}
		if (isDate(column)) {
			return getSqlDateValue(token, srcDateFormat, sqlDateFormat);
		}
		if (column.needEscapedValue()) {
			String escaped1 = StringUtils.replace(token, "\\", "\\\\");
			String escaped2 = StringUtils.replace(escaped1, "'", "\\'");
			String escaped3 = StringUtils.replace(escaped2, "\n", "\\n");
			return "'" + escaped3 + "'";
		}
		return token;
	}

	protected String getSqlDateValue(String token, String srcDateFormat, String sqlDateFormat) {
		Date date = getDateFromSource(token, srcDateFormat);
		SimpleDateFormat sqlDateFormatter = new SimpleDateFormat(sqlDateFormat);
		String sqlValue = sqlDateFormatter.format(date);
		return "STR_TO_DATE( '" + sqlValue + "', '%Y%m%d%H%i%s' )";
	}

	protected Date getDateFromSource(String token, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(token);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot parse " + token + " using format [" + dateFormat + "]");
		}
	}

	protected boolean isDate(Column column) {
		String torqueType = getTorqueType(column);
		if (torqueType.equalsIgnoreCase(DATE)) {
			return true;
		}
		if (torqueType.equalsIgnoreCase(TIMESTAMP)) {
			return true;
		}
		return false;
	}

	protected String getTorqueType(Column column) {
		Object torqueType = column.getTorqueType();
		if (torqueType == null) {
			throw new IllegalStateException("No torque type specified for " + column.getName());
		}
		return torqueType.toString();
	}

	@SuppressWarnings("unchecked")
	protected List<Column> getColumns(Table table) {
		return table.getColumns();
	}

	protected List<String> getColumnNames(List<Column> columns) {
		List<String> names = new ArrayList<String>();
		for (Column column : columns) {
			names.add(column.getName());
		}
		return names;
	}

	// INSERT INTO FOO (BAR1,BAR2) VALUES
	protected String getPrefix(Table table) {
		List<Column> columns = getColumns(table);
		List<String> names = getColumnNames(columns);

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO");
		sb.append(" ");
		sb.append(table.getName());
		sb.append(" ");
		sb.append("(");
		sb.append(CollectionUtils.getCSV(names));
		sb.append(")");
		sb.append(" ");
		sb.append("VALUES");
		sb.append(" ");
		return sb.toString();
	}

	protected boolean isHeaderLine(String s) {
		return StringUtils.startsWith(s, QUOTE);
	}

	public String getSrcDateFormat() {
		return srcDateFormat;
	}

	public void setSrcDateFormat(String srcDateFormat) {
		this.srcDateFormat = srcDateFormat;
	}

	public String getSqlDateFormat() {
		return sqlDateFormat;
	}

	public void setSqlDateFormat(String sqlDateFormat) {
		this.sqlDateFormat = sqlDateFormat;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

}
