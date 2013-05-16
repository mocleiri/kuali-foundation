package org.kuali.common.aws.s3;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.CollectionUtils;
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
	private static long total = 0;

	@Override
	public DefaultMutableTreeNode getTree(TreeContext context) {
		Assert.notNull(context.getClient(), "client is null");
		Assert.notNull(context.getDelimiter(), "delimiter is null");
		Bucket b = getBucket(context.getClient(), context.getBucket());
		buildTree(context, b);
		return null;
	}

	protected void buildTree(TreeContext context, Bucket bucket) {
		String prefix = getPrefix(context.getPrefix(), context.getDelimiter());
		ListObjectsRequest request = getListObjectsRequest(bucket, prefix, context.getDelimiter(), null);
		ObjectListing listing = context.getClient().listObjects(request);
		List<String> commonPrefixes = listing.getCommonPrefixes();
		for (String commonPrefix : commonPrefixes) {
			total++;
			if (include(context, commonPrefix)) {
				count++;
				logger.info(total + " - " + commonPrefix + " - " + count);
				TreeContext newContext = clone(context, commonPrefix);
				buildTree(newContext, bucket);
			} else {
				skipped++;
				logger.info(total + " - " + commonPrefix + " - skipped - " + skipped);
			}
		}
	}

	protected String getPrefix(String prefix, String delimiter) {
		return StringUtils.endsWith(prefix, delimiter) ? prefix : prefix + delimiter;
	}

	protected String getPattern(String pattern, String delimiter) {
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

	protected boolean include(TreeContext context, String prefix) {
		for (String exclude : CollectionUtils.toEmptyList(context.getExcludes())) {
			String suffix = getPattern(exclude, context.getDelimiter());
			if (StringUtils.endsWith(prefix, suffix)) {
				return false;
			}
		}
		if (CollectionUtils.isEmpty(context.getIncludes())) {
			return true;
		}
		for (String include : context.getIncludes()) {
			String suffix = getPattern(include, context.getDelimiter());
			if (StringUtils.endsWith(prefix, suffix)) {
				return true;
			}
		}
		return false;
	}

	protected TreeContext clone(TreeContext context, String prefix) {
		TreeContext newContext = new TreeContext();
		try {
			BeanUtils.copyProperties(newContext, context);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		newContext.setPrefix(prefix);
		return newContext;
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
