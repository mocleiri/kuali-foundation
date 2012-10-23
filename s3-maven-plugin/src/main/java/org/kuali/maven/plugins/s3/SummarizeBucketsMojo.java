package org.kuali.maven.plugins.s3;

import java.util.List;

import org.kuali.common.aws.s3.AccountSummary;

import com.amazonaws.services.s3.AmazonS3Client;

/**
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

	@Override
	protected void execute(AmazonS3Client client) {
		getLog().info(SEPARATOR);
		getLog().info("Summarizing buckets for Access Key: " + getAccessKey());
		getLog().info(SEPARATOR);
		List<String> includes = s3Utils.toList(include);
		List<String> excludes = s3Utils.toList(exclude);
		AccountSummary summary = s3Utils.getAccountSummary(accessKey, secretKey, includes, excludes);
		getLog().info("\n" + s3Utils.toString(summary));
	}

}
