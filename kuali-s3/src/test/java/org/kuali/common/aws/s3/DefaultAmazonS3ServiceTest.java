package org.kuali.common.aws.s3;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.PercentCompleteInformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;

public class DefaultAmazonS3ServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultAmazonS3ServiceTest.class);

	@Test
	public void test() {
		try {
			logger.debug("");
			AmazonS3Client client = getClient();
			String bucket = "site.origin.kuali.org";
			String prefix = getProperty("s3.prefix", "maven/plugins/maven-dnsme-plugin/1.0.0");
			long prefixEstimate = getLong("s3.prefixEstimate", BucketContext.DEFAULT_PREFIX_ESTIMATE);

			// String prefix = "rice/latest";
			// List<String> excludes = Arrays.asList("cobertura", "apidocs", "clover", "xref-test", "graph", "xref", "testapidocs", "css", "images");
			List<String> excludes = Arrays.asList("apidocs", "xref-test", "xref", "testapidocs");

			BucketContext context = new BucketContext();
			context.setClient(client);
			context.setBucket(bucket);
			context.setExcludes(excludes);
			context.setPrefix(prefix);
			context.setInformer(new PercentCompleteInformer(prefixEstimate));
			context.setRecursive(true);

			BucketService service = new DefaultBucketService();

			List<ObjectListing> listings = service.listObjects(context);
			logger.info("listings.size()={}", listings.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected AmazonS3Client getClient() {
		String accessKey = "AKIAJFD5IM7IPVVUEBNA";
		String secretKey = System.getProperty("s3.secretKey");
		return S3Utils.getInstance().getClient(accessKey, secretKey);
	}

	protected String getProperty(String key, String defaultValue) {
		if (System.getProperty(key) != null) {
			return System.getProperty(key);
		} else {
			return defaultValue;
		}
	}

	protected long getLong(String key, long defaultValue) {
		return Long.parseLong(getProperty(key, Long.toString(defaultValue)));
	}

}
