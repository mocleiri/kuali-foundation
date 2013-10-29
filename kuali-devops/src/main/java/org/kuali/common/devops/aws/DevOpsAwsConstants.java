package org.kuali.common.devops.aws;

import org.kuali.common.aws.model.AwsAccount;

public abstract class DevOpsAwsConstants {

	public static final AwsAccount FOUNDATION = new AwsAccount.Builder("3627-3510-8948").accessKeyId("AKIAJFD5IM7IPVVUEBNA").keyName("kuali-key").build();
	public static final AwsAccount KS = new AwsAccount.Builder("0523-3819-3506").accessKeyId("AKIAJZ72UQ5ZCVEDMAPQ").keyName("kuali-key").build();
	public static final AwsAccount RICE = new AwsAccount.Builder("7898-1396-8323").accessKeyId("AKIAIZFPMJVCNOYYAZ2Q").keyName("kuali-key").build();
	public static final AwsAccount OLE = new AwsAccount.Builder("7867-4615-1229").accessKeyId("AKIAI453FI76LUZ7T7CA").keyName("kuali-key").build();

}
