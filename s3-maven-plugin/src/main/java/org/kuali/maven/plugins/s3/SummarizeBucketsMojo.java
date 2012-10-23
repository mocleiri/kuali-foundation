package org.kuali.maven.plugins.s3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.kuali.common.aws.s3.BucketPrefixSummary;
import org.kuali.common.aws.s3.SimpleFormatter;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

/**
 * @goal summarizebuckets
 */
public class SummarizeBucketsMojo extends AbstractS3Mojo {
	SimpleFormatter formatter = new SimpleFormatter();

	/**
	 * A comma separated list of buckets to include in the summary
	 *
	 * @parameter expression="${s3.include}"
	 */
	String include;

	/**
	 * A comma separated list of buckets to exclude from the summary
	 *
	 * @parameter expression="${s3.exclude}"
	 */
	String exclude;

	@Override
	protected void execute(AmazonS3Client client) {
		getLog().info(SEPARATOR);
		getLog().info("Summarizing buckets for Access Key: " + getAccessKey());
		getLog().info(SEPARATOR);
		List<String> columns = getColumns();
		List<Bucket> buckets = getBuckets(client, include, exclude);
		List<String[]> rows = getRows(client, buckets);
		String s = s3Utils.toString(columns, rows);
		getLog().info("\n\n" + s);
	}

	protected List<Bucket> getBuckets(AmazonS3Client client, String include, String exclude) {
		List<String> includes = s3Utils.toList(include);
		List<String> excludes = s3Utils.toList(exclude);
		List<Bucket> buckets = client.listBuckets();
		int originalSize = buckets.size();
		getLog().info("Located " + buckets.size() + " total buckets");
		Iterator<Bucket> itr = buckets.iterator();
		while (itr.hasNext()) {
			Bucket bucket = itr.next();
			String bucketName = bucket.getName();
			if (!include(bucketName, includes, excludes)) {
				getLog().info("Excluding '" + bucket.getName() + "'");
				itr.remove();
			}
		}
		if (originalSize != buckets.size()) {
			getLog().info("Summarizing " + buckets.size() + " buckets");
		}
		Collections.sort(buckets, new BucketComparator());
		return buckets;
	}

	protected boolean include(String bucketName, List<String> includes, List<String> excludes) {
		if (excludes.contains(bucketName)) {
			return false;
		} else {
			return includes.size() == 0 || includes.contains(bucketName);
		}
	}

	protected List<String[]> getRows(AmazonS3Client client, List<Bucket> buckets) {
		List<String[]> rows = new ArrayList<String[]>();
		int count = 1;
		for (Bucket bucket : buckets) {
			getLog().info(count + " - " + bucket.getName());
			BucketPrefixSummary summary = s3Utils.summarize(client, bucket.getName());
			rows.add(getRow(bucket, summary));
			count++;
		}
		return rows;
	}

	protected String[] getRow(Bucket bucket, BucketPrefixSummary summary) {
		String[] row = new String[3];
		row[0] = bucket.getName();
		row[1] = formatter.getCount(summary.getCount());
		row[2] = formatter.getSize(summary.getSize());
		return row;
	}

	protected List<String> getColumns() {
		List<String> columns = new ArrayList<String>();
		columns.add("Bucket");
		columns.add("Files");
		columns.add("Size");
		return columns;
	}

}
