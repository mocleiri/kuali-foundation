package org.kuali.common.aws.s3;

public interface S3Service {

	void copyObject(String bucket, String srcKey, String dstKey);

}
