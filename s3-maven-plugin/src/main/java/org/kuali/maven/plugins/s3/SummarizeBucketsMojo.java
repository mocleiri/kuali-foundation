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

import java.io.File;
import java.util.List;

import org.kuali.common.aws.s3.pojo.AccountSummary;

import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Generate summary information for any S3 buckets "owned" by the provided <code>accessKey</code>
 *
 * @goal summarizebuckets
 */
public class SummarizeBucketsMojo extends AbstractS3Mojo {

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

	/**
	 * If true, a CSV file is generated containing bucket summary statistics.
	 *
	 * @parameter expression="${s3.writeCsv}" default-value="true"
	 */
	boolean writeCsv;

	/**
	 * The file where the CSV summary is written. If the file already exists, it is appended to.
	 *
	 * @parameter expression="${s3.csvFile}" default-value="${project.build.directory}/s3/summary.csv"
	 */
	File csvFile;

	@Override
	protected void execute(AmazonS3Client client) {
		getLog().info(SEPARATOR);
		getLog().info("Summarizing buckets for Access Key: " + getAccessKey());
		getLog().info(SEPARATOR);
		List<String> includes = s3Utils.toList(include);
		List<String> excludes = s3Utils.toList(exclude);
		AccountSummary summary = s3Utils.getAccountSummary(accessKey, secretKey, includes, excludes);
		getLog().info("\n\n" + s3Utils.toString(summary));
		if (writeCsv) {
			if (csvFile.exists()) {
				getLog().info("Appending to " + csvFile.getAbsolutePath());
			} else {
				getLog().info("Creating " + csvFile.getAbsolutePath());
			}
			boolean printColumnHeaders = !csvFile.exists();
			String csv = s3Utils.toCSV(summary, printColumnHeaders);
			s3Utils.write(csvFile, csv, true);
		}
	}

}
