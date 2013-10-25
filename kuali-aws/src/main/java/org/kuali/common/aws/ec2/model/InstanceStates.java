package org.kuali.common.aws.ec2.model;

public enum InstanceStates {

	PENDING("pending"), //
	RUNNING("running"), //
	SHUTTINGDOWN("shutting-down"), //
	TERMINATED("terminated"), //
	STOPPING("stopping"), //
	STOPPED("stopped");

	private final String value;

	private InstanceStates(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
