package org.kuali.common.devops.aws;


public abstract class AwsConstants {

	// This is the availability zone where the CI master and build slaves run
	public static final String US_EAST_1D = "us-east-1d";

	// amzn-ami-minimal-pv-2013.09 - This is a bare bones Amazon Linux box with virtually nothing installed on it
	public static final String AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09 = "ami-65792c0c";
}
