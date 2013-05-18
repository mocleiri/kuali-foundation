package org.kuali.common.aws.s3;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class DefaultBucketService implements BucketService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultBucketService.class);

	@Override
	public List<ObjectListing> getObjectListings(BucketContext context) {
		Assert.notNull(context.getClient(), "client is null");
		Assert.hasText(context.getDelimiter(), "delimiter has no text");
		Assert.hasText(context.getBucket(), "bucket has no text");
		boolean exists = context.getClient().doesBucketExist(context.getBucket());
		Assert.isTrue(exists, "bucket [" + context.getBucket() + "] does not exist");
		if (context.getInformer() != null) {
			Object[] args = { context.getBucket(), context.getDelimiter(), Str.toEmpty(context.getPrefix()) };
			logger.info("Listing Objects - [s3://{}{}{}]", args);
			context.getInformer().start();
		}
		long start = System.currentTimeMillis();
		List<ObjectListing> listings = getObjectListing(context);
		if (context.getInformer() != null) {
			context.getInformer().stop();
			String elapsed = FormatUtils.getTime(System.currentTimeMillis() - start);
			String count = FormatUtils.getCount(listings.size());
			Object[] args = { count, elapsed };
			logger.info("Object Listings - [count:{} elapsed:{}]", args);
			for (ObjectListing listing : listings) {
				showListing(listing);
			}
		}
		return listings;
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

	/**
	 * Examine the bucket starting at <code>prefix</code>. If <code>context.isRecursive()=true</code>, all sub-directories are searched as well.
	 */
	protected List<ObjectListing> getObjectListing(BucketContext context) {
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
				BucketContext clone = clone(context, commonPrefix);
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

	protected void log(String prefix, long count, long skipped, long requests) {
		int padding = 10;
		String r = StringUtils.leftPad(FormatUtils.getCount(requests), padding);
		String t = StringUtils.leftPad(FormatUtils.getCount(count + skipped), padding);
		String c = StringUtils.leftPad(FormatUtils.getCount(count), padding);
		String s = StringUtils.leftPad(FormatUtils.getCount(skipped), padding);
		Object[] args = { r, t, c, s, prefix };
		logger.debug("{} {} {} {} - {}", args);
	}

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

	protected boolean isExclude(BucketContext context, String prefix) {
		for (String exclude : CollectionUtils.toEmptyList(context.getExcludes())) {
			if (isMatch(prefix, exclude, context.getDelimiter())) {
				return true;
			}
		}
		return false;
	}

	protected boolean isInclude(BucketContext context, String prefix) {
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

	protected boolean isRecurse(BucketContext context, String prefix) {
		return context.isRecursive() && !isExclude(context, prefix) && isInclude(context, prefix);
	}

	protected BucketContext clone(BucketContext context, String prefix) {
		BucketContext clone = new BucketContext();
		try {
			BeanUtils.copyProperties(clone, context);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		clone.setPrefix(prefix);
		return clone;
	}

	protected ListObjectsRequest getListObjectsRequest(BucketContext context, String prefix) {
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
