package org.kuali.common.aws.model;

public enum Regions {

	AP_NORTHEAST_1("ap-northeast-1", "Asia Pacific (Tokyo)"), //
	AP_SOUTHEAST_1("ap-southeast-1", "Asia Pacific (Singapore)"), //
	AP_SOUTHEAST_2("ap-southeast-2", "Asia Pacific (Sydney)"), //
	EU_WEST_1("eu-west-1", "EU (Ireland)"), //
	SA_EAST_1("sa-east-1", "South America (Sao Paulo)"), //
	US_EAST_1("us-east-1", "US East (Northern Virginia)"), //
	US_WEST_1("us-west-2", "US West (Northern California)"), //
	US_WEST_2("us-west-2", "US West (Oregon)"); //

	private final String id;
	private final String location;

	private Regions(String id, String location) {
		this.id = id;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

}
