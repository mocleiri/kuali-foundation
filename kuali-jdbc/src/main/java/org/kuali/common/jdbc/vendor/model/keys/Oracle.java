package org.kuali.common.jdbc.vendor.model.keys;

public enum Oracle implements KeySuffix {

	KILL_AND_DROP("killAndDrop"), KILL_AND_DROP_RDS("killAndDrop.rds"), SCHEMA_STATS("schemaStats");

	private Oracle(String value) {
		this.value = value;
	}

	private final String value;

	@Override
	public String getValue() {
		return value;
	}

}
