package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.vendor.model.Vendors.Codes;

public enum VendorCode {

	ORACLE(Codes.ORACLE), MYSQL(Codes.MYSQL);

	private VendorCode(String value) {
		this.value = value;
	}

	private final String value;

	public String getValue() {
		return value;
	}

}
