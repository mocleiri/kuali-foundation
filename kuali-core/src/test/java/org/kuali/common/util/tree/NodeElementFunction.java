package org.kuali.common.util.tree;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.google.common.base.Function;

public class NodeElementFunction<T> implements Function<Node<T>, T> {

	public static <T> NodeElementFunction<T> create() {
		return new NodeElementFunction<T>();
	}

	@Override
	public T apply(Node<T> node) {
		checkNotNull(node, "node");
		return node.getElement();
	}

}
