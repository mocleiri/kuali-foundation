package org.kuali.common.jdbc.model;

public enum Vendors {

	ORACLE(VendorConstants.ORACLE), //
	MYSQL(VendorConstants.MYSQL);

	private Vendors(String text) {
		this.text = text;
	}

	private final String text;

	public String getText() {
		return text;
	}

}
