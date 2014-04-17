package org.kuali.common.aws.s3;

import org.kuali.common.aws.s3.model.CopyObjectResult;

public interface S3Service {

	CopyObjectResult copyObject(String bucket, String srcKey, String dstKey);

}
