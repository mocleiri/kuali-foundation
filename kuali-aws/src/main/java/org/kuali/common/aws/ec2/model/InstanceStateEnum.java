package org.kuali.common.aws.ec2.model;

public enum InstanceStateEnum {

	PENDING("pending"), //
	RUNNING("running"), //
	SHUTTINGDOWN("shutting-down"), //
	TERMINATED("terminated"), //
	STOPPING("stopping"), //
	STOPPED("stopped");

	private final String value;

	private InstanceStateEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
