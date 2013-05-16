package org.kuali.common.aws.s3;

import javax.swing.tree.DefaultMutableTreeNode;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

public interface AmazonS3Service {

	DefaultMutableTreeNode getTree(TreeContext context);

	Bucket getBucket(AmazonS3Client client, String bucketName);

}
