package org.kuali.common.impex.pojo;

public enum DataType {

	DATE, TIMESTAMP, CLOB, STRING, CHAR, FLOAT, INTEGER, BIT, BLOB;

	public boolean isSizeable(DataType type) {
		switch (type) {
		case DATE:
		case TIMESTAMP:
		case BIT:
			return false;
		default:
			return true;
		}
	}

	// scale == decimal digits, aka number of digits after the decimal point.
	// In simplified data types used in Kuali apps, scale only has meaning with FLOAT
	public boolean isScalable(DataType type) {
		return FLOAT.equals(type);
	}

}
