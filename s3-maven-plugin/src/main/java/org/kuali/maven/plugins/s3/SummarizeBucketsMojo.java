package org.kuali.maven.plugins.s3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.aws.s3.BucketSummary;
import org.kuali.common.aws.s3.SimpleFormatter;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

/**
 * @goal summarizebuckets
 */
public class SummarizeBucketsMojo extends AbstractS3Mojo {
	SimpleFormatter formatter = new SimpleFormatter();

	/**
	 * A comma separated list of buckets to ignore
	 *
	 * @parameter expression="${s3.ignore}"
	 */
	String ignore;

	@Override
	protected void execute(AmazonS3Client client) {
		getLog().info("----------------------------------------------------");
		getLog().info("Listing buckets for Access Key: " + getAccessKey());
		getLog().info("----------------------------------------------------");
		List<String> columns = getColumns();
		List<Bucket> buckets = getBuckets(client, ignore);
		List<String[]> rows = getRows(client, buckets);
		String s = s3Utils.toString(columns, rows);
		getLog().info("\n\n" + s);
	}

	protected List<Bucket> getBuckets(AmazonS3Client client, String ignore) {
		List<Bucket> buckets = client.listBuckets();
		Collections.sort(buckets, new BucketComparator());
		return buckets;
	}

	protected List<String[]> getRows(AmazonS3Client client, List<Bucket> buckets) {
		List<String[]> rows = new ArrayList<String[]>();
		for (Bucket bucket : buckets) {
			BucketSummary summary = s3Utils.summarize(client, bucket.getName());
			rows.add(getRow(bucket, summary));
		}
		return rows;
	}

	protected String[] getRow(Bucket bucket, BucketSummary summary) {
		String[] row = new String[3];
		row[0] = bucket.getName();
		row[1] = formatter.getSize(summary.getSize());
		row[2] = summary.getCount() + "";
		return row;
	}

	protected List<String> getColumns() {
		List<String> columns = new ArrayList<String>();
		columns.add("Buckt");
		columns.add("Size");
		columns.add("Count");
		return columns;
	}

}
