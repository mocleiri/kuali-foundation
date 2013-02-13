package org.kuali.core.db.torque.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.torque.engine.database.model.Column;
import org.codehaus.plexus.util.StringUtils;

public class MySQLConverter implements SqlConverter {

	private static final String DATE = "DATE";
	private static final String TIMESTAMP = "TIMESTAMP";

	String srcDateFormat = ImpexContext.MPX_DATE_FORMAT;
	String sqlDateFormat = "yyyyMMddHHmmss";

	@Override
	public String getSqlValue(Column column, String token) {
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
			throw new IllegalArgumentException("Cannot parse " + token);
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
