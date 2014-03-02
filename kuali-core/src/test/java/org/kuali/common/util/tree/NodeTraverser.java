package org.kuali.common.util.tree;

import com.google.common.collect.TreeTraverser;

public class NodeTraverser<T> extends TreeTraverser<Node<T>> {

	public static <T> NodeTraverser<T> create() {
		return new NodeTraverser<T>();
	}

	@Override
	public Iterable<Node<T>> children(Node<T> root) {
		return root.getChildren();
	}

}
