package org.kuali.common.devops.aws.sysadmin.model;

public enum TomcatMajorVersion {

	SIX("6"), SEVEN("7");

	private final String value;

	private TomcatMajorVersion(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
