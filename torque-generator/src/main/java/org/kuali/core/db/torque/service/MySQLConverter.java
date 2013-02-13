package org.kuali.core.db.torque.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.torque.engine.database.model.Column;

public class MySQLConverter implements SqlConverter {

	private static final String DATE = "DATE";
	private static final String TIMESTAMP = "TIMESTAMP";

	String srcDateFormat;
	String sqlDateFormat;

	@Override
	public String getSqlValue(Column column, String token) {
		if (isDate(column)) {
			return getSqlDateValue(token);
		}

		return null;
	}

	protected String getSqlDateValue(String token) {
		Date date = getDateFromMpx(token);
		SimpleDateFormat sqlDateFormatter = new SimpleDateFormat(sqlDateFormat);
		String sqlValue = sqlDateFormatter.format(date);
		return "STR_TO_DATE( '" + sqlValue + "', '%Y%m%d%H%i%s' )";
	}

	protected Date getDateFromMpx(String token) {
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
