package org.kuali.common.util.tree;

import java.util.List;

public final class ImmutableNode<T> extends MutableNode<T> {

	private static final String UOE_MSG = "Operation not supported for immutable node";

	public static <T> ImmutableNode<T> of(Node<T> node) {
		return new ImmutableNode<T>(node);
	}

	private ImmutableNode(Node<T> node) {
		super(node.getElement());
		List<? extends Node<T>> children = super.getChildren();
		super.removeAllChildren();
		for (Node<T> child : children) {
			super.add(new ImmutableNode<T>(child));
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
