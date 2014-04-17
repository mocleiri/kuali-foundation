package org.kuali.common.aws.s3;

import org.kuali.common.aws.ec2.model.Regions;
import org.kuali.common.aws.model.ImmutableAWSCredentials;
import org.kuali.common.core.build.ValidatingBuilder;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;

public final class DefaultS3Service implements S3Service {

	private final ImmutableAWSCredentials credentials;
	private final Region region;

	@Override
	public void copyObject(String bucket, String srcKey, String dstKey) {
	}

	private DefaultS3Service(Builder builder) {
		this.credentials = ImmutableAWSCredentials.copyOf(builder.credentials);
		this.region = builder.region;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<DefaultS3Service> {

		private AWSCredentials credentials;
		private Region region = RegionUtils.getRegion(Regions.DEFAULT_REGION.getName());

		public Builder withCredentials(ImmutableAWSCredentials credentials) {
			this.credentials = credentials;
			return this;
		}

		public Builder withRegion(Region region) {
			this.region = region;
			return this;
		}

		@Override
		public DefaultS3Service build() {
			return validate(new DefaultS3Service(this));
		}
	}

	public ImmutableAWSCredentials getCredentials() {
		return credentials;
	}

	public Region getRegion() {
		return region;
	}

}
