package org.kuali.common.devops.aws;

public enum SecurityGroupName {

	SSH("ssh"), //
	HTTP("http"), //
	DEPLOY("deploy"), //
	HTTPS("https");

	private final String value;

	private SecurityGroupName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
