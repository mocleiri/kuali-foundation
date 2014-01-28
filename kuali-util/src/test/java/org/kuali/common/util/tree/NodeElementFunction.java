package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Function;

public class NodeElementFunction<T> implements Function<Node<T>, T> {

	public static <T> NodeElementFunction<T> create() {
		return new NodeElementFunction<T>();
	}

	@Override
	public T apply(Node<T> node) {
		checkNotNull(node, "'node' cannot be null");
		return node.getElement();
	}

}
