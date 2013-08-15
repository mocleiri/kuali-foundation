package org.kuali.common.jdbc.vendor.model;

public enum SqlKeys {

	VALIDATE("validate"), // SQL used to validate a connection
	CREATE("create"), // SQL to create a database
	DROP("drop"), // SQL to drop a database
	LIQUIBASE_DROP("liquibase.drop"), // SQL to drop the 2 metadata tables Liquibase needs
	LIQUIBASE_CREATE("liquibase.create"); // SQL to create the 2 metadata tables Liquibase needs

	private SqlKeys(String value) {
		this.value = value;
	}

	private final String value;

	public String getCode() {
		return value;
	}

}
