package org.kuali.common.aws.model;

public enum AvailabilityZones {

	// This is a known zone in the us-east-1 region
	US_EAST_1D("us-east-1d"); //

	private final String name;

	private AvailabilityZones(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
