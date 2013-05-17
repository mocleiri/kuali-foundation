package org.kuali.common.aws.s3;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3Client;

public class DefaultAmazonS3ServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultAmazonS3ServiceTest.class);

	protected AmazonS3Client getClient() {
		String accessKey = "AKIAJFD5IM7IPVVUEBNA";
		String secretKey = System.getProperty("s3.secretKey");
		return S3Utils.getInstance().getClient(accessKey, secretKey);
	}

	@Test
	public void test() {
		try {
			logger.debug("");
			AmazonS3Client client = getClient();
			String bucket = "site.origin.kuali.org";
			String prefix = "maven/plugins/maven-dnsme-plugin";
			// String prefix = "rice/latest/reference";
			if (System.getProperty("s3.prefix") != null) {
				prefix = System.getProperty("s3.prefix");
			}
			long prefixEstimate = BucketContext.DEFAULT_PREFIX_ESTIMATE;
			if (System.getProperty("s3.prefixEstimate") != null) {
				prefixEstimate = Long.parseLong(System.getProperty("s3.prefixEstimate"));
			}

			// String prefix = "rice/latest";
			// List<String> excludes = Arrays.asList("cobertura", "apidocs", "clover", "xref-test", "graph", "xref", "testapidocs", "css", "images");
			List<String> excludes = Arrays.asList("apidocs", "xref-test", "xref", "testapidocs");

			BucketContext context = new BucketContext();
			context.setClient(client);
			context.setBucket(bucket);
			context.setExcludes(excludes);
			context.setPrefix(prefix);
			context.setPrefixEstimate(prefixEstimate);

			AmazonS3Service service = new DefaultAmazonS3Service();

			service.getTree(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
