package org.kuali.maven.plugins.s3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.aws.s3.BucketComparator;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

/**
 * Print the list of buckets owned by the provided access key to the console.
 *
 * @goal listbuckets
 */
public class ListBucketsMojo extends AbstractS3Mojo {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

	@Override
	protected void execute(AmazonS3Client client) {
		getLog().info(SEPARATOR);
		getLog().info("Listing buckets for Access Key: " + getAccessKey());
		getLog().info(SEPARATOR);
		List<Bucket> buckets = client.listBuckets();
		Collections.sort(buckets, new BucketComparator());
		List<String> columns = getColumns();
		List<String[]> rows = getRows(buckets);
		String s = s3Utils.toString(columns, rows);
		getLog().info("\n\n" + s);
	}

	protected List<String[]> getRows(List<Bucket> buckets) {
		List<String[]> rows = new ArrayList<String[]>();
		for (Bucket bucket : buckets) {
			String[] row = new String[3];
			row[0] = bucket.getName();
			row[1] = sdf.format(bucket.getCreationDate());
			row[2] = bucket.getOwner().getDisplayName();
			rows.add(row);
		}
		return rows;
	}

	protected List<String> getColumns() {
		List<String> columns = new ArrayList<String>();
		columns.add("Name");
		columns.add("Created");
		columns.add("Owner");
		return columns;
	}

}
