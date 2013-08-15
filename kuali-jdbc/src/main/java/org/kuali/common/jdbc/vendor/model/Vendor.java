package org.kuali.common.jdbc.vendor.model;

public enum Vendor {

	ORACLE(Vendors.Codes.ORACLE), MYSQL(Vendors.Codes.MYSQL);

	private Vendor(String code) {
		this.code = code;
	}

	private final String code;

	public String getCode() {
		return code;
	}

}
