package org.kuali.common.aws.s3;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;

public class DefaultBucketService implements BucketService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultBucketService.class);

	@Override
	public ObjectListingResult getObjectListings(ObjectListingRequest request) {
		Assert.notNull(request.getClient(), "client is null");
		Assert.hasText(request.getDelimiter(), "delimiter has no text");
		Assert.hasText(request.getBucket(), "bucket has no text");
		boolean exists = request.getClient().doesBucketExist(request.getBucket());
		Assert.isTrue(exists, "bucket [" + request.getBucket() + "] does not exist");
		if (request.getInformer() != null) {
			Object[] args = { request.getBucket(), request.getDelimiter(), Str.toEmpty(request.getPrefix()) };
			logger.info("Listing Objects - [s3://{}{}{}]", args);
			request.getInformer().start();
		}
		long start = System.currentTimeMillis();
		List<ObjectListing> listings = getObjectListing(request);
		if (request.getInformer() != null) {
			request.getInformer().stop();
		}
		ObjectListingResult result = new ObjectListingResult();
		result.setListings(listings);
		result.setStartTime(start);
		result.setStopTime(System.currentTimeMillis());
		result.setElapsed(result.getStopTime() - result.getStartTime());
		return result;
	}

	/**
	 * Examine the bucket starting at <code>prefix</code>. If <code>context.isRecursive()=true</code>, all sub-directories are searched as well.
	 */
	protected List<ObjectListing> getObjectListing(ObjectListingRequest context) {
		String prefix = getPrefix(context.getPrefix(), context.getDelimiter());
		ListObjectsRequest request = getListObjectsRequest(context, prefix);
		ObjectListing listing = context.getClient().listObjects(request);
		Assert.isFalse(listing.isTruncated(), "listing is truncated");
		if (context.getInformer() != null) {
			context.getInformer().incrementProgress();
		}
		List<ObjectListing> listings = new ArrayList<ObjectListing>();
		listings.add(listing);
		List<String> commonPrefixes = listing.getCommonPrefixes();
		for (String commonPrefix : commonPrefixes) {
			if (isRecurse(context, commonPrefix)) {
				ObjectListingRequest clone = clone(context, commonPrefix);
				List<ObjectListing> children = getObjectListing(clone);
				listings.addAll(children);
			} else {
				ListObjectsRequest childRequest = getListObjectsRequest(context, commonPrefix);
				ObjectListing childListing = context.getClient().listObjects(childRequest);
				Assert.isFalse(listing.isTruncated(), "listing is truncated");
				if (context.getInformer() != null) {
					context.getInformer().incrementProgress();
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

	protected boolean isExclude(ObjectListingRequest context, String prefix) {
		for (String exclude : CollectionUtils.toEmptyList(context.getExcludes())) {
			if (isMatch(prefix, exclude, context.getDelimiter())) {
				return true;
			}
		}
		return false;
	}

	protected boolean isInclude(ObjectListingRequest context, String prefix) {
		if (CollectionUtils.isEmpty(context.getIncludes())) {
			return true;
		}
		for (String include : context.getIncludes()) {
			if (isMatch(prefix, include, context.getDelimiter())) {
				return true;
			}
		}
		return false;
	}

	protected boolean isRecurse(ObjectListingRequest context, String prefix) {
		return context.isRecursive() && !isExclude(context, prefix) && isInclude(context, prefix);
	}

	protected ObjectListingRequest clone(ObjectListingRequest context, String prefix) {
		ObjectListingRequest clone = new ObjectListingRequest();
		try {
			BeanUtils.copyProperties(clone, context);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		clone.setPrefix(prefix);
		return clone;
	}

	protected ListObjectsRequest getListObjectsRequest(ObjectListingRequest context, String prefix) {
		return getListObjectsRequest(context.getBucket(), prefix, context.getDelimiter(), null);
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
