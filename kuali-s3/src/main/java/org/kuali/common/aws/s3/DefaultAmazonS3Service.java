package org.kuali.common.aws.s3;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;

public class DefaultAmazonS3Service implements AmazonS3Service {

	private static final Logger logger = LoggerFactory.getLogger(DefaultAmazonS3Service.class);
	private static long count = 0;
	private static long skipped = 0;
	private static long requests = 0;

	@Override
	public DefaultMutableTreeNode getTree(TreeContext context) {
		Assert.notNull(context.getClient(), "client is null");
		Assert.hasText(context.getDelimiter(), "delimiter has no text");
		Assert.hasText(context.getBucket(), "bucket has no text");
		Bucket b = getBucket(context.getClient(), context.getBucket());
		logger.info("Building tree for [{}:{}]", context.getBucket(), context.getPrefix());
		System.out.print("[INFO] Progress: ");
		buildTree(context, b);
		System.out.println();
		return null;
	}

	protected void buildTree(TreeContext context, Bucket bucket) {
		String prefix = getPrefix(context.getPrefix(), context.getDelimiter());
		ListObjectsRequest request = getListObjectsRequest(bucket, prefix, context.getDelimiter(), null);
		ObjectListing listing = context.getClient().listObjects(request);
		requests++;
		if (requests % 50 == 0) {
			System.out.print(".");
		}
		List<String> commonPrefixes = listing.getCommonPrefixes();
		for (String commonPrefix : commonPrefixes) {
			if (include(context, commonPrefix)) {
				count++;
				log(commonPrefix, count, skipped, requests);
				buildTree(clone(context, commonPrefix), bucket);
			} else {
				skipped++;
				log(commonPrefix, count, skipped, requests);
			}
		}
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

	protected boolean isExclude(TreeContext context, String prefix) {
		for (String exclude : CollectionUtils.toEmptyList(context.getExcludes())) {
			if (isMatch(prefix, exclude, context.getDelimiter())) {
				return true;
			}
		}
		return false;
	}

	protected boolean isInclude(TreeContext context, String prefix) {
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

	protected boolean include(TreeContext context, String prefix) {
		return !isExclude(context, prefix) && isInclude(context, prefix);
	}

	protected TreeContext clone(TreeContext context, String prefix) {
		TreeContext clone = new TreeContext();
		try {
			BeanUtils.copyProperties(clone, context);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		clone.setPrefix(prefix);
		return clone;
	}

	@Override
	public Bucket getBucket(AmazonS3Client client, String bucketName) {
		List<Bucket> buckets = client.listBuckets();
		for (Bucket bucket : buckets) {
			if (StringUtils.equals(bucketName, bucket.getName())) {
				return bucket;
			}
		}
		return null;
	}

	protected ListObjectsRequest getListObjectsRequest(Bucket bucket, String prefix, String delimiter, Integer maxKeys) {
		ListObjectsRequest request = new ListObjectsRequest();
		request.setBucketName(bucket.getName());
		request.setDelimiter(delimiter);
		request.setPrefix(prefix);
		request.setMaxKeys(maxKeys);
		return request;
	}

}
