package org.kuali.common.aws.s3;

import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Test;

import com.amazonaws.services.s3.AmazonS3Client;

public class DefaultAmazonS3ServiceTest {

	@Test
	public void test() {
		String accessKey = "AKIAJFD5IM7IPVVUEBNA";
		String secretKey = System.getProperty("aws.secretKey");
		try {
			AmazonS3Client client = S3Utils.getInstance().getClient(accessKey, secretKey);
			AmazonS3Service service = new DefaultAmazonS3Service();
			DefaultMutableTreeNode tree = service.getTree(client, "site.origin.kuali.org");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
