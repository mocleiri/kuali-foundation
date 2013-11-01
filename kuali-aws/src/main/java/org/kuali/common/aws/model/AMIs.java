package org.kuali.common.aws.model;

public enum AMIs {

	AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09("ami-65792c0c");

	private final String value;

	private AMIs(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
