package org.kuali.common.aws.s3;

import javax.swing.tree.DefaultMutableTreeNode;

import com.amazonaws.services.s3.AmazonS3Client;

public interface AmazonS3Service {

	DefaultMutableTreeNode getTree(AmazonS3Client client, String bucket);

}
