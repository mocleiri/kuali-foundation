package org.kuali.common.aws.s3;

import java.util.List;

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
		String secretKey = System.getProperty("s3.secretKey");
		try {
			AmazonS3Client client = S3Utils.getInstance().getClient(accessKey, secretKey);
			String bucket = "site.origin.kuali.org";
			String prefix = "maven/plugins/spring-maven-plugin/latest";
			if (System.getProperty("s3.prefix") != null) {
				prefix = System.getProperty("s3.prefix");
			}
			long prefixCountEstimate = 100;
			if (System.getProperty("s3.prefixCountEstimate") != null) {
				prefixCountEstimate = Long.parseLong(System.getProperty("s3.prefixCountEstimate"));
			}
			// String prefix = "rice/latest";
			// List<String> excludes = Arrays.asList("cobertura", "apidocs", "clover", "xref-test", "graph", "xref", "testapidocs", "css", "images");
			// List<String> excludes = Arrays.asList("apidocs", "clover", "xref-test", "xref", "testapidocs");
			List<String> excludes = null; // Arrays.asList("apidocs", "clover", "xref-test", "xref", "testapidocs");
			String delimiter = "/";

			TreeContext context = new TreeContext();
			context.setClient(client);
			context.setBucket(bucket);
			context.setExcludes(excludes);
			context.setPrefix(prefix);
			context.setDelimiter(delimiter);
			context.setPrefixCountEstimate(prefixCountEstimate);

			AmazonS3Service service = new DefaultAmazonS3Service();
			Bucket b = service.getBucket(client, bucket);
			logger.info("Owner: {}", b.getOwner().getDisplayName());
			logger.info("Name: {}", b.getName());
			logger.info("Created On: {}", b.getCreationDate());

			service.getTree(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
