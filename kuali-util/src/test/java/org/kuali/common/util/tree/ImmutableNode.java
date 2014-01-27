package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

public final class ImmutableNode<T> extends MutableNode<T> {

	private static final String UOE_MSG = "Operation not supported for immutable node";

	public static <T> ImmutableNode<T> copyOf(Node<T> node) {
		return new ImmutableNode<T>(node);
	}

	public ImmutableNode(Node<T> node) {
		checkNotNull(node, "'node' cannot be null");
		super.setElement(node.getElement());
		List<Node<T>> children = node.getChildren();
		for (Node<T> child : children) {
			super.add(children.size(), copyOf(child));
		}
	}

	@Override
	public void setElement(T element) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void remove(MutableNode<T> child) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void remove(int index) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void add(List<MutableNode<T>> children) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void add(MutableNode<T> child1, MutableNode<T> child2) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void add(MutableNode<T> child1, MutableNode<T> child2, MutableNode<T> child3) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void add(MutableNode<T> child1, MutableNode<T> child2, MutableNode<T> child3, MutableNode<T> child4) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void add(MutableNode<T> child1, MutableNode<T> child2, MutableNode<T> child3, MutableNode<T> child4, MutableNode<T> child5) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void add(MutableNode<T> child) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void add(int index, MutableNode<T> child) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void removeAllChildren() {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void removeFromParent() {
		throw new UnsupportedOperationException(UOE_MSG);
	}

}
