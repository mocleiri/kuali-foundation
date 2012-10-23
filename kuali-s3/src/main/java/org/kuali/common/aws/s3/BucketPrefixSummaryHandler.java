package org.kuali.common.aws.s3;

import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;

import com.amazonaws.services.s3.AmazonS3Client;

public class BucketPrefixSummaryHandler implements ElementHandler<S3PrefixContext> {

	public void handleElement(ListIteratorContext<S3PrefixContext> context, int index, S3PrefixContext element) {
		AmazonS3Client client = element.getClient();
		String bucketName = element.getBucketName();
		BucketPrefixSummary summary = element.getSummary();
		S3Utils.getInstance().summarize(client, bucketName, summary);
	}

}
