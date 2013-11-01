package org.kuali.common.aws.model;

public enum AvailabilityZones {

	// This zone is in the us-east region
	US_EAST_1D("us-east-1d");

	private final String value;

	private AvailabilityZones(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
