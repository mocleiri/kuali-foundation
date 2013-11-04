package org.kuali.common.aws.ec2.model.security;

public enum Protocol {

	TCP("tcp"), UDP("udp");

	private final String value;

	private Protocol(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
