package org.kuali.common.aws.s3;

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
			String prefix = "maven/plugins/maven-dnsme-plugin/1.0.0";
			if (System.getProperty("s3.prefix") != null) {
				prefix = System.getProperty("s3.prefix");
			}
			long prefixCountEstimate = TreeContext.DEFAULT_PREFIX_COUNT_ESTIMATE;
			if (System.getProperty("s3.prefixCountEstimate") != null) {
				prefixCountEstimate = Long.parseLong(System.getProperty("s3.prefixCountEstimate"));
			}
			// String prefix = "rice/latest";
			// List<String> excludes = Arrays.asList("cobertura", "apidocs", "clover", "xref-test", "graph", "xref", "testapidocs", "css", "images");
			// List<String> excludes = Arrays.asList("apidocs", "clover", "xref-test", "xref", "testapidocs");
			List<String> excludes = null; // Arrays.asList("apidocs", "clover", "xref-test", "xref", "testapidocs");

			TreeContext context = new TreeContext();
			context.setClient(client);
			context.setBucket(bucket);
			context.setExcludes(excludes);
			context.setPrefix(prefix);
			context.setPrefixCountEstimate(prefixCountEstimate);

			AmazonS3Service service = new DefaultAmazonS3Service();

			service.getTree(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
