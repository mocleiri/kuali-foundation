package org.kuali.common.aws.s3;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.common.aws.cloudfront.DefaultListingConverterService;
import org.kuali.common.aws.cloudfront.IndexContext;
import org.kuali.common.aws.cloudfront.IndexDataContext;
import org.kuali.common.aws.cloudfront.ListingConverterContext;
import org.kuali.common.aws.cloudfront.ListingConverterService;
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
			BucketContext bucketContext = new BucketContext("site.origin.kuali.org");
			ListingRequest request = getListingRequest(bucketContext);
			ObjectListingsContext context = new ObjectListingsContext(client, bucketContext, request);
			BucketService service = new DefaultBucketService();
			ListingResult result = service.getObjectListings(context);

			ListingConverterContext lcc = new ListingConverterContext();
			ListingConverterService lcs = new DefaultListingConverterService();
			List<IndexContext> contexts = new ArrayList<IndexContext>();
			for (ObjectListing listing : result.getListings()) {
				IndexDataContext idc = new IndexDataContext();
				idc.setBucketContext(bucketContext);
				idc.setConverterContext(lcc);
				idc.setListing(listing);

				List<String[]> indexData = lcs.getIndexData(idc);

				IndexContext indexContext = new IndexContext();
				indexContext.setListing(listing);
				indexContext.setIndexData(indexData);

				contexts.add(indexContext);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected ListingRequest getListingRequest(BucketContext context) {
		String prefix = getProperty("s3.prefix", "pom/kuali-pom/latest");
		long prefixEstimate = getLong("s3.prefixEstimate", BucketConstants.DEFAULT_PREFIX_ESTIMATE);
		// String prefix = "rice/latest";
		// List<String> excludes = Arrays.asList("cobertura", "apidocs", "clover", "xref-test", "graph", "xref", "testapidocs", "css", "images");
		List<String> excludes = null; // Arrays.asList("apidocs", "xref-test", "xref", "testapidocs");

		Object[] args = { context.getName(), context.getDelimiter(), prefix };
		LogMsg startMessage = new LogMsg("Examining bucket - [s3://{}{}{}]", args);
		PercentCompleteInformer informer = new PercentCompleteInformer(prefixEstimate, startMessage);

		ListingRequest request = new ListingRequest();
		request.setExcludes(excludes);
		request.setPrefix(prefix);
		request.setInformer(informer);
		request.setRecursive(true);
		return request;

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
