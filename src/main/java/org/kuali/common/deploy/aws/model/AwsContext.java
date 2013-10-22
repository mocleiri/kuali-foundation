package org.kuali.common.deploy.aws.model;

import org.kuali.common.util.Assert;

public final class AwsContext {

	public AwsContext(EC2Context ec2, S3Context s3, SesContext ses) {
		Assert.noNulls(ec2, s3, ses);
		this.ec2 = ec2;
		this.s3 = s3;
		this.ses = ses;
	}

	private final EC2Context ec2;
	private final S3Context s3;
	private final SesContext ses;

	public EC2Context getEc2() {
		return ec2;
	}

	public S3Context getS3() {
		return s3;
	}

	public SesContext getSes() {
		return ses;
	}

}
