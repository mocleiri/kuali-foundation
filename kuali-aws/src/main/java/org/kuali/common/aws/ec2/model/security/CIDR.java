package org.kuali.common.aws.ec2.model.security;

public enum CIDR {

	ANY("0.0.0.0/0"); // Allow anyone to connect from anywhere

	private final String notation;

	private CIDR(String notation) {
		this.notation = notation;
	}

	public String getNotation() {
		return notation;
	}

}
