package org.kuali.common.aws.ec2.model.status;

public enum InstanceStatusName {

	REACHABILITY("reachability");

	private final String value;

	private InstanceStatusName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
