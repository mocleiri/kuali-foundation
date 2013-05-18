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
	public ObjectListingResult getObjectListings(ObjectListingRequest request) {

		// Make sure we are configured correctly
		Assert.notNull(request.getClient(), "client is null");
		Assert.hasText(request.getDelimiter(), "delimiter has no text");
		Assert.hasText(request.getBucket(), "bucket has no text");
		boolean exists = request.getClient().doesBucketExist(request.getBucket());
		Assert.isTrue(exists, "bucket [" + request.getBucket() + "] does not exist");

		// Start the informer, if they supplied one
		if (request.getInformer() != null) {
			logger.debug("starting informer");
			request.getInformer().start();
		}

		// Preserve our start time
		long start = System.currentTimeMillis();

		// Connect to Amazon's S3 service and collect summary information about objects in our S3 bucket
		// This can be a recursive and take a while
		List<ObjectListing> listings = getObjectListing(request);

		// Preserve our stop time
		long stop = System.currentTimeMillis();

		// Stop the informer, if they supplied one
		if (request.getInformer() != null) {
			request.getInformer().stop();
		}

		// Aggregate information about this request into a result object
		ObjectListingResult result = new ObjectListingResult();
		result.setListings(listings);
		result.setStartTime(start);
		result.setStopTime(stop);
		result.setElapsed(stop - start);
		return result;
	}

	/**
	 * Examine the bucket starting at <code>prefix</code>. If <code>context.isRecursive()=true</code>, all sub-directories are searched as well.
	 */
	protected List<ObjectListing> getObjectListing(ObjectListingRequest request) {
		String prefix = getPrefix(request.getPrefix(), request.getDelimiter());
		ListObjectsRequest lor = getListObjectsRequest(request, prefix);
		ObjectListing listing = request.getClient().listObjects(lor);
		Assert.isFalse(listing.isTruncated(), "listing is truncated");
		if (request.getInformer() != null) {
			request.getInformer().incrementProgress();
		}
		List<ObjectListing> listings = new ArrayList<ObjectListing>();
		listings.add(listing);
		List<String> commonPrefixes = listing.getCommonPrefixes();
		for (String commonPrefix : commonPrefixes) {
			if (isRecurse(request, commonPrefix)) {
				ObjectListingRequest clone = clone(request, commonPrefix);
				List<ObjectListing> children = getObjectListing(clone);
				listings.addAll(children);
			} else {
				ListObjectsRequest childRequest = getListObjectsRequest(request, commonPrefix);
				ObjectListing childListing = request.getClient().listObjects(childRequest);
				Assert.isFalse(listing.isTruncated(), "listing is truncated");
				if (request.getInformer() != null) {
					request.getInformer().incrementProgress();
				}
				listings.add(childListing);
			}
		}
		return listings;
	}

	/**
	 * If <code>prefix</code> does not end with <code>delimiter</code>, append <code>delimiter</code> to <code>prefix</code>. If <code>prefix</code> is blank or
	 * <code>prefix==delimiter</code> return <code>null</code>
	 */
	protected String getPrefix(String prefix, String delimiter) {
		if (StringUtils.isBlank(prefix) || StringUtils.equals(prefix, delimiter)) {
			return null;
		} else {
			return StringUtils.endsWith(prefix, delimiter) ? prefix : prefix + delimiter;
		}
	}

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

	protected boolean isMatch(String prefix, String pattern, String delimiter) {
		String suffix = getSuffixPattern(pattern, delimiter);
		return StringUtils.endsWith(prefix, suffix);
	}

	protected boolean isExclude(ObjectListingRequest request, String prefix) {
		for (String exclude : CollectionUtils.toEmptyList(request.getExcludes())) {
			if (isMatch(prefix, exclude, request.getDelimiter())) {
				return true;
			}
		}
		return false;
	}

	protected boolean isInclude(ObjectListingRequest request, String prefix) {
		if (CollectionUtils.isEmpty(request.getIncludes())) {
			return true;
		}
		for (String include : request.getIncludes()) {
			if (isMatch(prefix, include, request.getDelimiter())) {
				return true;
			}
		}
		return false;
	}

	protected boolean isRecurse(ObjectListingRequest request, String prefix) {
		return request.isRecursive() && !isExclude(request, prefix) && isInclude(request, prefix);
	}

	protected ObjectListingRequest clone(ObjectListingRequest request, String prefix) {
		ObjectListingRequest clone = new ObjectListingRequest();
		try {
			BeanUtils.copyProperties(clone, request);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		clone.setPrefix(prefix);
		return clone;
	}

	protected ListObjectsRequest getListObjectsRequest(ObjectListingRequest request, String prefix) {
		return getListObjectsRequest(request.getBucket(), prefix, request.getDelimiter(), null);
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
