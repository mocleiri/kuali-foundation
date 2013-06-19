package org.kuali.common.aws.cloudfront;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.aws.AmazonWebServiceRequestType;
import org.kuali.common.aws.TypedRequest;
import org.kuali.common.util.Str;

import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class DefaultCloudFrontService implements CloudFrontService {

	@Override
	public void fillInWelcomeFileKeys(List<String> welcomeFiles, List<IndexContext> contexts) {
		for (IndexContext context : contexts) {
			ObjectListing listing = context.getListing();
			String welcomeFileKey = CloudFrontUtils.getFirstMatchingKey(listing, welcomeFiles);
			context.setWelcomeFileKey(welcomeFileKey);
		}
	}
	@Override
	public void fillInWelcomeFileKeys(List<String> welcomeFiles, List<IndexContext> contexts) {
		for (IndexContext context : contexts) {
			ObjectListing listing = context.getListing();
			String welcomeFileKey = CloudFrontUtils.getFirstMatchingKey(listing, welcomeFiles);
			context.setWelcomeFileKey(welcomeFileKey);
		}
	}

	@Override
	public List<IndexContext> getIndexContexts(List<ObjectListing> listings) {
		List<IndexContext> contexts = new ArrayList<IndexContext>();
		for (ObjectListing listing : listings) {
			contexts.add(new IndexContext(listing));
		}
		return contexts;
	}

	@Override
	public List<TypedRequest> getIndexObjectRequests(IndexObjectsContext context) {

		ListingConverterService converterService = context.getConverterService();
		ListingConverterContext converterContext = context.getConverterContext();

		HtmlGeneratorService generatorService = context.getGeneratorService();
		HtmlGeneratorContext generatorContext = context.getGeneratorContext();

		List<TypedRequest> requests = new ArrayList<TypedRequest>();
		for (ObjectListing listing : listings) {

			// Convert the object listing into a list of string array's
			List<String[]> data = null; // converterService.convert(converterContext, listing);

			// Convert the string array's into html
			String html = generatorService.getDirectoryListing(generatorContext, listing.getPrefix(), data);

			// Use the html to create s3://bucket/foo/bar
			TypedRequest request1 = getTypedRequestWithoutTrailingDelimiter(context, listing, html);

			// Create s3://bucket/foo/bar/
			// This is either the same html as request1 OR a copy of s3://bucket/foo/bar/index.html
			TypedRequest request2 = getTypedRequest(context, listing, html);

			// Add the requests to our list
			requests.add(request1);
			requests.add(request2);

		}
		return requests;
	}

	protected TypedRequest getTypedRequestWithoutTrailingDelimiter(CloudFrontContext context, ObjectListing listing, String html) {
		// Create s3://bucket/foo/bar
		PutObjectRequest index = getPutHtmlRequestWithoutTrailingDelimiter(context, listing, html);
		return new TypedRequest(index, AmazonWebServiceRequestType.PUT_OBJECT);
	}

	/**
	 * This does one of two things. It either copies <code>/foo/bar/index.html to /foo/bar/</code> OR creates <code>/foo/bar/</code> from <code>html</code>
	 */
	protected TypedRequest getTypedRequest(CloudFrontContext context, ObjectListing listing, String html) {
		String welcomeFileKey = CloudFrontUtils.getFirstMatchingKey(listing, context.getWelcomeFiles());
		if (welcomeFileKey == null) {
			// Create s3://bucket/foo/bar/
			PutObjectRequest put = CloudFrontUtils.getPutHtmlRequest(context, html, listing.getPrefix());
			return new TypedRequest(put, AmazonWebServiceRequestType.PUT_OBJECT);
		} else {
			// Copy s3://bucket/foo/bar/index.html -> s3://bucket/foo/bar/
			CopyObjectRequest copy = CloudFrontUtils.getCopyObjectRequest(context, welcomeFileKey, listing.getPrefix());
			return new TypedRequest(copy, AmazonWebServiceRequestType.COPY_OBJECT);
		}
	}

	protected PutObjectRequest getPutHtmlRequestWithoutTrailingDelimiter(CloudFrontContext context, ObjectListing listing, String html) {
		String delimiter = context.getBucketContext().getDelimiter();
		String objectKey = Str.removeSuffix(listing.getPrefix(), delimiter);
		return CloudFrontUtils.getPutHtmlRequest(context, html, objectKey);
	}

	protected PutObjectRequest getPutHtmlRequest(CloudFrontContext context, ObjectListing listing, String html) {
		return CloudFrontUtils.getPutHtmlRequest(context, html, listing.getPrefix());
	}

}
