/**
 * Copyright 2011-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.s3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.aws.s3.pojo.BucketComparator;

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
