/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.aws.s3;

import static com.google.common.base.Preconditions.checkArgument;
import static org.kuali.common.aws.ec2.model.Regions.DEFAULT_REGION;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import org.kuali.common.aws.model.ImmutableAWSCredentials;
import org.kuali.common.aws.s3.model.CopyObjectResult;
import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CopyObjectRequest;

@IdiotProofImmutable
public final class DefaultS3Service implements S3Service {

	private final ImmutableAWSCredentials credentials;
	private final Region region;

	// Mutable! Don't expose via a getter
	private final AmazonS3Client client;

	/**
	 * Copy an object in a bucket to another name. Copies the original objects metadata and ACL.
	 */
	@Override
	public CopyObjectResult copyObject(String bucket, String srcKey, String dstKey) {
		checkNotBlank(bucket, "bucket");
		checkNotBlank(srcKey, "srcKey");
		checkNotBlank(dstKey, "dstKey");
		checkArgument(!srcKey.equals(dstKey), "srcKey cannot be the same as dstKey -> [%s] == [%s]", srcKey, dstKey);
		CopyObjectRequest request = new CopyObjectRequest(bucket, srcKey, bucket, dstKey);
		request.setAccessControlList(client.getObjectAcl(bucket, srcKey));
		return CopyObjectResult.copyOf(client.copyObject(request));
	}

	private DefaultS3Service(Builder builder) {
		this.credentials = ImmutableAWSCredentials.copyOf(builder.credentials);
		this.region = builder.region;

		// Setup the client
		this.client = new AmazonS3Client(credentials);
		this.client.setRegion(region);
	}

	public static Builder builder(AWSCredentials credentials) {
		return new Builder(credentials);
	}

	public static class Builder extends ValidatingBuilder<DefaultS3Service> {

		private final AWSCredentials credentials;
		private Region region = RegionUtils.getRegion(DEFAULT_REGION.getName());

		public Builder(AWSCredentials credentials) {
			this.credentials = credentials;
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

	public AWSCredentials getCredentials() {
		return credentials;
	}

	public Region getRegion() {
		return region;
	}

}
