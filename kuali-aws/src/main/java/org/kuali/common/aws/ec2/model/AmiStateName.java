package org.kuali.common.aws.ec2.model;

public enum AmiStateName {

	AVAILABLE("available"), //
	DEREGISTERED("deregistered"); //

	private final String value;

	private AmiStateName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
