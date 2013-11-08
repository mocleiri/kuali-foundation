package org.kuali.common.devops.aws.sysadmin.model;

public enum JDKLevel {

	SIX(6), SEVEN(7);

	private int version;

	private JDKLevel(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

}
