package org.kuali.common.jdbc.vendor.model.keys;

public enum Liquibase implements KeySuffix {

	DROP("liquibase.drop"), CREATE("liquibase.create");

	private Liquibase(String value) {
		this.value = value;
	}

	private final String value;

	@Override
	public String getKeySuffix() {
		return value;
	}
}
