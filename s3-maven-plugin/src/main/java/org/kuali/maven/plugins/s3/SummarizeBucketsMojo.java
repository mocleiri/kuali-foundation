package org.kuali.maven.plugins.s3;

import java.io.File;
import java.util.List;

import org.kuali.common.aws.s3.AccountSummary;

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
		getLog().info("\n" + s3Utils.toString(summary));
		if (writeCsv) {
			if (csvFile.exists()) {
				getLog().info("Appending to " + csvFile.getAbsolutePath());
			} else {
				getLog().info("Creating " + csvFile.getAbsolutePath());
			}
			boolean printColumnHeaders = !csvFile.exists();
			s3Utils.write(csvFile, s3Utils.toCSV(summary, printColumnHeaders), true);
		}
	}

}
