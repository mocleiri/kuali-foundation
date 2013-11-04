package org.kuali.common.aws.ec2.model.security;

public enum CIDR {

	ANY("0.0.0.0/0");

	private final String value;

	private CIDR(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
