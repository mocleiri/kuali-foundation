package org.kuali.common.aws.ec2.model.status;

public enum InstanceStatusValue {

	INITIALIZING("initializing"), //
	PASSED("passed");

	private final String value;

	private InstanceStatusValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
