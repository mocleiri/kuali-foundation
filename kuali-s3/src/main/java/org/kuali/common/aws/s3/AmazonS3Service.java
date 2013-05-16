package org.kuali.common.aws.s3;

import javax.swing.tree.DefaultMutableTreeNode;

public interface AmazonS3Service {

	DefaultMutableTreeNode getTree(TreeContext context);

}
