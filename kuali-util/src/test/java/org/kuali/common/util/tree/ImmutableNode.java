package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class ImmutableNode<T> extends AbstractNode<T> {

	private final Optional<ImmutableNode<T>> parent;
	private final T userObject;
	private final ImmutableList<ImmutableNode<T>> children;

	private ImmutableNode(Builder<T> builder) {
		this.parent = builder.node.getParent();
		this.children = ImmutableList.copyOf(builder.children);
		this.userObject = builder.userObject;
	}

	public static <T> ImmutableNode<T> of(T userObject) {
		return builder(userObject).build();
	}

	public static <T> ImmutableNode<T> of(T userObject, List<ImmutableNode<T>> children) {
		return builder(userObject).children(children).build();
	}

	public static <T> ImmutableNode<T> copyOf(Node<T> root) {
		if (root instanceof ImmutableNode) {
			return (ImmutableNode<T>) root;
		} else {
			return of(root.getUserObject(), copyOf(root.getChildren()));
		}
	}

	public static <T> List<ImmutableNode<T>> copyOf(List<? extends Node<T>> list) {
		List<ImmutableNode<T>> immutable = Lists.newArrayList();
		for (Node<T> element : list) {
			immutable.add(copyOf(element));
		}
		return immutable;
	}

	public static <T> Builder<T> builder(T userObject) {
		return builder(new MutableNode<T>(userObject));
	}

	public static <T> Builder<T> builder(MutableNode<T> node) {
		return new Builder<T>(node);
	}

	public static class Builder<T> implements org.kuali.common.util.build.Builder<ImmutableNode<T>> {

		// Required
		private final MutableNode<T> node;

		public Builder(MutableNode<T> node) {
			this.node = node;
		}

		public Builder<T> add(MutableNode<T> child) {
			return add(ImmutableList.of(child));
		}

		public Builder<T> add(List<MutableNode<T>> children) {
			node.add(children);
			return this;
		}

		@Override
		public ImmutableNode<T> build() {
			ImmutableNode<T> instance = new ImmutableNode<T>(this);
			validate(instance);
			return instance;
		}

		private static <T> void validate(ImmutableNode<T> instance) {
			checkNotNull(instance.parent, "'parent' cannot be null");
			checkNotNull(instance.children, "'children' cannot be null");
			checkNotNull(instance.userObject, "'userObject' cannot be null");
		}

	}

	@Override
	public Optional<ImmutableNode<T>> getParent() {
		return parent;
	}

	@Override
	public ImmutableList<ImmutableNode<T>> getChildren() {
		return children;
	}

	@Override
	public T getUserObject() {
		return userObject;
	}

}
