package org.kuali.common.impex.pojo;

public enum DataType {

	DATE, TIMESTAMP, CLOB, STRING, CHAR, FLOAT, INTEGER, BIT, BLOB;

	public static boolean isNumeric(DataType type) {
		return FLOAT.equals(type) || INTEGER.equals(type);
	}

}
