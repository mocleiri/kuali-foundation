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

	@Override
	public String getInsertSql(Table table, BufferedReader reader) throws IOException {
		List<Column> columns = getColumns(table);
		String line = reader.readLine();

		if (ImpexUtils.isHeaderLine(line)) {
			line = reader.readLine();
		}

		if (line == null) {
			return null;
		}

		//
		String csv = getSqlCsvFromLine(columns, line);

		//
		StringBuilder sb = new StringBuilder();
		sb.append(getPrefix(table));
		sb.append("(");
		sb.append(csv);
		sb.append(")");
		return sb.toString();
	}

	protected String getSqlCsvFromLine(List<Column> columns, String line) {
		// Split up the values from the .mpx file
		String[] tokens = ImpexUtils.getOriginalValues(line);
		// Convert them into the form they need to be in for SQL
		List<String> sqlValues = getSqlValues(columns, tokens);
		// Turn them into a comma separated list
		return CollectionUtils.getCSV(sqlValues);
	}

	/**
	 * The strings returned by this method must be suitable for use in an SQL "INSERT INTO" statement without needing any modification. All
	 * characters that need to be escaped must be escaped already, any text values must be enclosed with quotes as appropriate.
	 */
	protected List<String> getSqlValues(List<Column> columns, String[] tokens) {
		Assert.isTrue(columns.size() == tokens.length);
		List<String> values = new ArrayList<String>();
		for (int i = 0; i < columns.size(); i++) {
			Column column = columns.get(i);
			String token = tokens[i];
			String value = getSqlValue(column, token);
			values.add(value);
		}
		return values;
	}

	public String getSqlValue(Column column, String token) {
		if (token == null) {
			return NULL;
		}
		if (isDate(column)) {
			return getSqlDateValue(token, srcDateFormat, sqlDateFormat);
		}
		if (column.needEscapedValue()) {
			String escaped1 = StringUtils.replace(token, "\\", "\\\\");
			String escaped2 = StringUtils.replace(escaped1, "'", "\\'");
			return "'" + escaped2 + "'";
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

}
