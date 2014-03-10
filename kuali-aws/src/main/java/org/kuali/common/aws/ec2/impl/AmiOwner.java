package org.kuali.common.aws.ec2.impl;

public enum AmiOwner {

	AMAZON("amazon"), //
	AWS_MARKETPLACE("aws-marketplace"), //
	SELF("self"), //
	ALL("all");

	private final String value;

	private AmiOwner(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
