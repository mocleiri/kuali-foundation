package org.kuali.common.jdbc.sql.model;

public enum Jdbc implements EnvironmentKey {

	URL("jdbc.url"), //
	USERNAME("jdbc.username"), //
	PASSWORD("jdbc.password"), //
	DBA_URL("jdbc.dba.url"), //
	DBA_USERNAME("jdbc.dba.username"), //
	DBA_PASSWORD("jdbc.dba.password"); //

	private Jdbc(String value) {
		this.value = value;
	}

	private String value;

	@Override
	public String getValue() {
		return this.value;
	}

}
