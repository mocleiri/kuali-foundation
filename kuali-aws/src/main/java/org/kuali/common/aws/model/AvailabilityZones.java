package org.kuali.common.aws.model;

import org.kuali.common.util.nullify.NullUtils;

public enum AvailabilityZones {

	// This is a zone in the us-east region
	US_EAST_1D("us-east-1d"), //
	NO_PREFERENCE(NullUtils.NONE);

	private final String name;

	private AvailabilityZones(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
