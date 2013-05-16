package org.kuali.common.aws.s3;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

public class DefaultAmazonS3ServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultAmazonS3ServiceTest.class);

	@Test
	public void test() {
		String accessKey = "AKIAJFD5IM7IPVVUEBNA";
		String secretKey = System.getProperty("aws.secretKey");
		try {
			String bucketName = "site.origin.kuali.org";
			AmazonS3Client client = S3Utils.getInstance().getClient(accessKey, secretKey);
			AmazonS3Service service = new DefaultAmazonS3Service();
			Bucket bucket = service.getBucket(client, bucketName);
			logger.info("Owner: {}", bucket.getOwner().getDisplayName());
			logger.info("Name: {}", bucket.getName());
			logger.info("Created On: {}", bucket.getCreationDate());
			service.getTree(client, bucketName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
