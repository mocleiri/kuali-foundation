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

	private final String name;
	private final String location;

	private Regions(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	/**
	 * http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/java-dg-region-selection.html
	 * 
	 * The AWS SDK for Java uses the US East (Northern Virginia) Region as the default region if you do not specify a region in your code. However, the AWS Management Console uses
	 * US West (Oregon) Region as its default. Therefore, when using the AWS Management Console in conjunction with your development, be sure to specify the same region in both
	 * your code and the console.
	 */
	public static final Regions DEFAULT_REGION = US_EAST_1;
}
