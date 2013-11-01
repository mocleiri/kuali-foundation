package org.kuali.common.aws.model;

import org.kuali.common.util.nullify.NullUtils;

public enum AvailabilityZones {

	// This is a zone in the us-east region
	US_EAST_1D("us-east-1d"), //
	NO_PREFERENCE(NullUtils.NONE);

	private final String value;

	private AvailabilityZones(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
