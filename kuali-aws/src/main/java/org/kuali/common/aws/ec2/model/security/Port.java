package org.kuali.common.aws.ec2.model.security;

public enum Port {

	SSH(22), HTTP(80), HTTPS(443);

	private final int value;

	private Port(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
