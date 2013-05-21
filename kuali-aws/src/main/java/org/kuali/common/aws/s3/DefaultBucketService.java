package org.kuali.common.aws.s3;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;

public class DefaultBucketService implements BucketService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultBucketService.class);

	@Override
	public ListingResult getObjectListings(ObjectListingsContext context) {

		// Make sure we are configured correctly
		Assert.notNull(context, "context is null");
		Assert.notNull(context.getRequest(), "request is null");
		Assert.notNull(context.getClient(), "client is null");
		Assert.hasText(context.getBucketContext().getDelimiter(), "delimiter has no text");
		Assert.hasText(context.getBucketContext().getName(), "name has no text");
		boolean exists = context.getClient().doesBucketExist(context.getBucketContext().getName());
		Assert.isTrue(exists, "bucket [" + context.getBucketContext().getName() + "] does not exist");

		ListingRequest request = context.getRequest();

		// Start the informer, if they supplied one
		if (request.getInformer() != null) {
			logger.debug("starting informer");
			request.getInformer().start();
		}

		// Preserve the start time
		long start = System.currentTimeMillis();

		// Connect to Amazon's S3 service and collect summary information about objects in the S3 bucket
		// This can be recursive and take a while
		List<ObjectListing> listings = accumulateObjectListings(context, context.getRequest());

		// Preserve the stop time
		long stop = System.currentTimeMillis();

		// Stop the informer, if they supplied one
		if (request.getInformer() != null) {
			request.getInformer().stop();
		}

		// Aggregate information about this request into a result object
		ListingResult result = new ListingResult();
		result.setListings(listings);
		result.setStartTime(start);
		result.setStopTime(stop);
		result.setElapsed(stop - start);
		return result;
	}

	/**
	 * Examine an S3 bucket (potentially recursively) for information about the "directories" and objects it contains.
	 */
	protected List<ObjectListing> accumulateObjectListings(ObjectListingsContext context, ListingRequest request) {

		// Append delimiter to prefix if needed
		String prefix = getPrefix(request.getPrefix(), context.getBucketContext().getDelimiter());

		// Setup some storage for our Object listings
		List<ObjectListing> listings = new ArrayList<ObjectListing>();

		// Connect to S3 and obtain an ObjectListing for this prefix
		ObjectListing listing = getObjectListing(context, prefix);

		// Add the current ObjectListing to the list
		listings.add(listing);

		// Examine the "sub-directories"
		for (String subDirectory : listing.getCommonPrefixes()) {
			doSubDirectory(context, subDirectory, listings);
		}

		// Return the aggregated list of ObjectListings
		return listings;
	}

	protected ObjectListing getObjectListing(ObjectListingsContext context, String prefix) {
		// Create an Amazon request
		ListObjectsRequest lor = getListObjectsRequest(context, prefix);

		// Connect to S3 and extract the object listing
		ObjectListing listing = context.getClient().listObjects(lor);

		// Make sure it isn't truncated (< 1000 objects total)
		Assert.isFalse(listing.isTruncated(), "listing is truncated");

		// Increment progress on the informer, if they supplied one
		if (context.getRequest().getInformer() != null) {
			context.getRequest().getInformer().incrementProgress();
		}

		return listing;
	}

	protected void doSubDirectory(ObjectListingsContext context, String subDirectory, List<ObjectListing> listings) {
		// Determine if we are recursing into this "sub-directory"
		if (isRecurse(context, subDirectory)) {

			// If so, clone the existing request, but update the prefix
			ListingRequest clone = clone(context.getRequest(), subDirectory);

			// Recurse in order to accumulate all ObjectListing's under this one
			List<ObjectListing> children = accumulateObjectListings(context, clone);

			// Add the aggregated child list to our overall list
			listings.addAll(children);

		} else {

			// We are not recursing into the "sub-directory" but we still list the contents of the "sub-directory" itself
			ObjectListing subDirectoryListing = getObjectListing(context, subDirectory);

			// Add the "sub-directory" listing to the overall list
			listings.add(subDirectoryListing);
		}
	}

	/**
	 * If <code>prefix</code> does not end with <code>delimiter</code>, append it. If <code>prefix</code> is blank or <code>prefix==delimiter</code> return <code>null</code>
	 */
	protected String getPrefix(String prefix, String delimiter) {
		if (StringUtils.isBlank(prefix) || StringUtils.equals(prefix, delimiter)) {
			return null;
		} else {
			return StringUtils.endsWith(prefix, delimiter) ? prefix : prefix + delimiter;
		}
	}

	/**
	 * Make sure <code>pattern</code> is bracketed by <code>delimiter</code>.
	 * 
	 * <pre>
	 *   apidocs  -> /apidocs/
	 *   apidocs/ -> /apidocs/
	 *  /apidocs  -> /apidocs/
	 *  /apidocs/ -> /apidocs/
	 * </pre>
	 */
	protected String getSuffixPattern(String pattern, String delimiter) {

		Assert.hasText(pattern, "pattern has no text");
		Assert.hasText(delimiter, "delimiter has no text");

		StringBuilder sb = new StringBuilder();
		if (!StringUtils.startsWith(pattern, delimiter)) {
			sb.append(delimiter);
		}
		sb.append(pattern);
		if (!StringUtils.endsWith(pattern, delimiter)) {
			sb.append(delimiter);
		}
		return sb.toString();
	}

	protected boolean isEndsWithMatch(String prefix, String pattern, String delimiter) {
		String suffix = getSuffixPattern(pattern, delimiter);
		return StringUtils.endsWith(prefix, suffix);
	}

	protected boolean isExclude(ObjectListingsContext context, String prefix) {
		for (String exclude : CollectionUtils.toEmptyList(context.getRequest().getExcludes())) {
			if (isEndsWithMatch(prefix, exclude, context.getBucketContext().getDelimiter())) {
				return true;
			}
		}
		return false;
	}

	protected boolean isInclude(ObjectListingsContext context, String prefix) {
		if (CollectionUtils.isEmpty(context.getRequest().getIncludes())) {
			return true;
		}
		for (String include : context.getRequest().getIncludes()) {
			if (isEndsWithMatch(prefix, include, context.getBucketContext().getDelimiter())) {
				return true;
			}
		}
		return false;
	}

	protected boolean isRecurse(ObjectListingsContext context, String prefix) {
		return context.getRequest().isRecursive() && !isExclude(context, prefix) && isInclude(context, prefix);
	}

	protected ListingRequest clone(ListingRequest request, String prefix) {
		ListingRequest clone = new ListingRequest();
		try {
			BeanUtils.copyProperties(clone, request);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		clone.setPrefix(prefix);
		return clone;
	}

	protected ListObjectsRequest getListObjectsRequest(ObjectListingsContext context, String prefix) {
		String name = context.getBucketContext().getName();
		String delimiter = context.getBucketContext().getDelimiter();
		Integer maxKeys = context.getBucketContext().getMaxKeys();
		return getListObjectsRequest(name, prefix, delimiter, maxKeys);
	}

	protected ListObjectsRequest getListObjectsRequest(String bucket, String prefix, String delimiter, Integer maxKeys) {
		ListObjectsRequest request = new ListObjectsRequest();
		request.setBucketName(bucket);
		request.setDelimiter(delimiter);
		request.setPrefix(prefix);
		request.setMaxKeys(maxKeys);
		return request;
	}

}
