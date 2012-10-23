package org.kuali.maven.plugins.s3;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

/**
 * @goal listbuckets
 */
public class ListBucketsMojo extends AbstractS3Mojo {

	@Override
	protected void execute(AmazonS3Client client) {
		getLog().info("Listing buckets for Access Key: " + getAccessKey());
		List<Bucket> buckets = client.listBuckets();
		for (Bucket bucket : buckets) {
			getLog().info(bucket.getName() + " " + bucket.getCreationDate() + " " + bucket.getOwner().getDisplayName());
		}
	}

}
