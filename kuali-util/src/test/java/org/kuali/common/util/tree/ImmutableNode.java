package org.kuali.common.util.tree;

import java.util.List;

public final class ImmutableNode<T> extends MutableNode<T> {

	public ImmutableNode(T element) {
		super(element);
	}

	private static final String UOE_MSG = "Operation not supported for immutable node";

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
