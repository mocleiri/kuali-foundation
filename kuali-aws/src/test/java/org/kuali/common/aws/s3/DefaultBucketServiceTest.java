package org.kuali.common.aws.s3;

import java.util.List;

import org.junit.Test;
import org.kuali.common.aws.s3.monitor.S3Utils;
import org.kuali.common.util.LogMsg;
import org.kuali.common.util.PercentCompleteInformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class DefaultBucketServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultBucketServiceTest.class);

	@Test
	public void test() {
		try {
			logger.debug("");
			AmazonS3Client client = getClient();
			String bucket = "site.origin.kuali.org";
			String prefix = getProperty("s3.prefix", "pom/kuali-pom/latest");
			long prefixEstimate = getLong("s3.prefixEstimate", ObjectListingRequest.DEFAULT_PREFIX_ESTIMATE);

			// String prefix = "rice/latest";
			// List<String> excludes = Arrays.asList("cobertura", "apidocs", "clover", "xref-test", "graph", "xref", "testapidocs", "css", "images");
			List<String> excludes = null; // Arrays.asList("apidocs", "xref-test", "xref", "testapidocs");

			Object[] args = { bucket, BucketContext.DEFAULT_DELIMITER, prefix };
			LogMsg startMessage = new LogMsg("Examining bucket - [s3://{}{}{}]", args);
			PercentCompleteInformer informer = new PercentCompleteInformer(prefixEstimate, startMessage);

			BucketContext context = new BucketContext(client, bucket);

			ObjectListingRequest request = new ObjectListingRequest(context);
			request.setExcludes(excludes);
			request.setPrefix(prefix);
			request.setInformer(informer);
			request.setRecursive(true);

			BucketService service = new DefaultBucketService();

			ObjectListingResult result = service.getObjectListings(request);
			for (ObjectListing listing : result.getListings()) {
				showListing(listing);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void showListing(ObjectListing listing) {
		logger.info(" *** [" + listing.getPrefix() + "] *** ");
		for (String commonPrefix : listing.getCommonPrefixes()) {
			logger.info(" Dir: " + commonPrefix);
		}
		for (S3ObjectSummary summary : listing.getObjectSummaries()) {
			logger.info("File: " + summary.getKey());
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
