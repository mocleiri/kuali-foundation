package org.kuali.common.jdbc.model;

public enum Vendor {

	ORACLE(VendorConstants.ORACLE), //
	MYSQL(VendorConstants.MYSQL);

	private Vendor(String text) {
		this.text = text;
	}

	private final String text;

	public String getText() {
		return text;
	}

}
