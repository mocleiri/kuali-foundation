package org.kuali.common.devops.aws;

public enum SecurityGroupName {

	SSH("ssh"), //
	HTTP("http"), //
	HTTPS("https"), //
	DEPLOY("deploy"); //

	private final String value;

	private SecurityGroupName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
