package org.kuali.core.db.torque.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.torque.engine.database.model.Column;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.Assert;

public class MySQLConverter implements SqlConverter {

	private static final String DATE = "DATE";
	private static final String TIMESTAMP = "TIMESTAMP";
	private static final String NULL = "NULL";

	String srcDateFormat = ImpexContext.MPX_DATE_FORMAT;
	String sqlDateFormat = "yyyyMMddHHmmss";

	@Override
	public List<String> getSqlValues(List<Column> columns, String[] tokens) {
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
			return getSqlDateValue(token);
		}
		if (column.needEscapedValue()) {
			String escaped1 = StringUtils.replace(token, "\\", "\\\\");
			String escaped2 = StringUtils.replace(escaped1, "'", "\\'");
			return "'" + escaped2 + "'";
		}
		return token;
	}

	protected String getSqlDateValue(String token) {
		Date date = getDateFromSource(token);
		SimpleDateFormat sqlDateFormatter = new SimpleDateFormat(sqlDateFormat);
		String sqlValue = sqlDateFormatter.format(date);
		return "STR_TO_DATE( '" + sqlValue + "', '%Y%m%d%H%i%s' )";
	}

	protected Date getDateFromSource(String token) {
		SimpleDateFormat sdf = new SimpleDateFormat(srcDateFormat);
		try {
			return sdf.parse(token);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot parse " + token + " using format [" + srcDateFormat + "]");
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

	public String getSqlDateFormat() {
		return sqlDateFormat;
	}

	public void setSqlDateFormat(String sqlDateFormat) {
		this.sqlDateFormat = sqlDateFormat;
	}

	public String getSrcDateFormat() {
		return srcDateFormat;
	}

	public void setSrcDateFormat(String srcDateFormat) {
		this.srcDateFormat = srcDateFormat;
	}

}
