package org.kuali.common.util.tree;

import javax.swing.tree.DefaultMutableTreeNode;

public class MutableNode<T> extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 641115050054682465L;

	public MutableNode() {
	}

	public MutableNode(T userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
	}

	public MutableNode(T userObject) {
		super(userObject);
	}

}
