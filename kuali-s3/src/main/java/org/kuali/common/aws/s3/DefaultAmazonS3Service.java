package org.kuali.common.aws.s3;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.lang.StringUtils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

public class DefaultAmazonS3Service implements AmazonS3Service {

	@Override
	public DefaultMutableTreeNode getTree(AmazonS3Client client, String bucketName) {
		Bucket bucket = getBucket(client, bucketName);
		return null;
	}

	protected Bucket getBucket(AmazonS3Client client, String bucketName) {
		List<Bucket> buckets = client.listBuckets();
		for (Bucket bucket : buckets) {
			if (StringUtils.equals(bucketName, bucket.getName())) {
				return bucket;
			}
		}
		return null;
	}

}
