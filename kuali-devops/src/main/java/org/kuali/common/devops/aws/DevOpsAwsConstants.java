package org.kuali.common.devops.aws;

import org.kuali.common.aws.model.AwsAccount;

public abstract class DevOpsAwsConstants {

	public static final AwsAccount FOUNDATION = new AwsAccount.Builder("3627-3510-8948").accessKeyId("AKIAJFD5IM7IPVVUEBNA").keyName("kuali-key").build();
	public static final AwsAccount KS = new AwsAccount.Builder("0523-3819-3506").accessKeyId("AKIAJZ72UQ5ZCVEDMAPQ").keyName("ks-key").build();
	public static final AwsAccount RICE = new AwsAccount.Builder("7898-1396-8323").accessKeyId("AKIAIZFPMJVCNOYYAZ2Q").keyName("kr-key").build();
	public static final AwsAccount OLE = new AwsAccount.Builder("7867-4615-1229").accessKeyId("AKIAI453FI76LUZ7T7CA").keyName("ole").build();

	// This is the availability zone both the master and the build slaves are pinned to
	public static final String US_EAST_1D = "us-east-1d";

	// amzn-ami-minimal-pv-2013.09 - This is a bare bones Amazon Linux box with virtually nothing installed on it
	public static final String AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09 = "ami-65792c0c";
}
