package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.util.Assert;

public enum Vendor {

	ORACLE(VendorCodes.ORACLE), MYSQL(VendorCodes.MYSQL);

	private Vendor(String code) {
		Assert.noBlanks(code);
		this.code = code;
	}

	private final String code;

	public String getCode() {
		return code;
	}

}
