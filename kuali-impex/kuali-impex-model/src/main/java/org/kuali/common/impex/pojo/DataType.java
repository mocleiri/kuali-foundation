package org.kuali.common.impex.pojo;

public enum DataType {

	DATE, TIMESTAMP, CLOB, STRING, CHAR, FLOAT, INTEGER, BIT, BLOB;

	public static boolean isSizeable(DataType type) {
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
	// In the simplified data types used by Kuali apps, scale only has meaning with FLOAT
	public static boolean isScalable(DataType type) {
		return FLOAT.equals(type);
	}

}
