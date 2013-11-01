package org.kuali.common.aws.model;

public enum AMIs {

	// This is a bare bones Amazon Linux box with virtually nothing except yum installed on it
	AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09("ami-65792c0c");

	private final String value;

	private AMIs(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
