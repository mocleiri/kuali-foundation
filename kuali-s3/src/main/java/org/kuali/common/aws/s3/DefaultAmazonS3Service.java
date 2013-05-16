package org.kuali.common.aws.s3;

import javax.swing.tree.DefaultMutableTreeNode;

import com.amazonaws.services.s3.AmazonS3Client;

public class DefaultAmazonS3Service implements AmazonS3Service {

	@Override
	public DefaultMutableTreeNode getTree(AmazonS3Client client, String bucket) {
		return null;
	}

}
