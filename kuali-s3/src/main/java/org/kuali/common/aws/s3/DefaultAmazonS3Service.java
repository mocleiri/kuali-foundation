package org.kuali.common.aws.s3;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;

public class DefaultAmazonS3Service implements AmazonS3Service {

	private static final Logger logger = LoggerFactory.getLogger(DefaultAmazonS3Service.class);
	private static long count = 0;

	@Override
	public DefaultMutableTreeNode getTree(AmazonS3Client client, String bucketName) {
		Bucket bucket = getBucket(client, bucketName);
		buildTree(client, bucket, null, "/");
		return null;
	}

	protected void buildTree(AmazonS3Client client, Bucket bucket, String prefix, String delimiter) {
		ListObjectsRequest request = getListObjectsRequest(bucket, prefix, delimiter, null);
		ObjectListing listing = client.listObjects(request);
		List<String> commonPrefixes = listing.getCommonPrefixes();
		for (String commonPrefix : commonPrefixes) {
			count++;
			logger.info(count + " - " + commonPrefix);
			buildTree(client, bucket, commonPrefix, delimiter);
		}
	}

	@Override
	public Bucket getBucket(AmazonS3Client client, String bucketName) {
		List<Bucket> buckets = client.listBuckets();
		for (Bucket bucket : buckets) {
			if (StringUtils.equals(bucketName, bucket.getName())) {
				return bucket;
			}
		}
		return null;
	}

	protected ListObjectsRequest getListObjectsRequest(Bucket bucket, String prefix, String delimiter, Integer maxKeys) {
		ListObjectsRequest request = new ListObjectsRequest();
		request.setBucketName(bucket.getName());
		request.setDelimiter(delimiter);
		request.setPrefix(prefix);
		request.setMaxKeys(maxKeys);
		return request;
	}

}
