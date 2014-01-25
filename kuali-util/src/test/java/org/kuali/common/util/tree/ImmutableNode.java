package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class ImmutableNode<T> extends AbstractNode<T> {

	private final Optional<ImmutableNode<T>> parent;
	private final ImmutableList<ImmutableNode<T>> children;
	private final T userObject;

	private ImmutableNode(Builder<T> builder) {
		this.parent = builder.parent;
		this.children = ImmutableList.copyOf(builder.children);
		this.userObject = builder.userObject;
	}

	public static <T> ImmutableNode<T> of(T userObject) {
		return builder(userObject).build();
	}

	public static <T> ImmutableNode<T> of(T userObject, List<ImmutableNode<T>> children) {
		return builder(userObject).children(children).build();
	}

	public static <T> ImmutableNode<T> of(ImmutableNode<T> parent, T userObject, List<ImmutableNode<T>> children) {
		return builder(userObject).parent(parent).children(children).build();
	}

	public static <T> ImmutableNode<T> of(Node<T> root) {
		if (root instanceof ImmutableNode) {
			return (ImmutableNode<T>) root;
		} else {
			return of(root.getUserObject(), of(root.getChildren()));
		}
	}

	public static <T> List<ImmutableNode<T>> of(List<? extends Node<T>> list) {
		List<ImmutableNode<T>> immutable = Lists.newArrayList();
		for (Node<T> element : list) {
			immutable.add(of(element));
		}
		return immutable;
	}

	public static <T> Builder<T> builder(T userObject) {
		return new Builder<T>(userObject);
	}

	public static class Builder<T> implements org.kuali.common.util.build.Builder<ImmutableNode<T>> {

		// Required
		private final T userObject;

		// Optional
		private Optional<ImmutableNode<T>> parent = Optional.absent();
		private List<ImmutableNode<T>> children = ImmutableList.of();

		public Builder(T userObject) {
			this.userObject = userObject;
		}

		public Builder<T> parent(Optional<ImmutableNode<T>> parent) {
			this.parent = parent;
			return this;
		}

		public Builder<T> parent(ImmutableNode<T> parent) {
			return parent(Optional.of(parent));
		}

		public Builder<T> children(List<ImmutableNode<T>> children) {
			this.children = children;
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

		public Optional<ImmutableNode<T>> getParent() {
			return parent;
		}

		public void setParent(Optional<ImmutableNode<T>> parent) {
			this.parent = parent;
		}

		public List<ImmutableNode<T>> getChildren() {
			return children;
		}

		public void setChildren(List<ImmutableNode<T>> children) {
			this.children = children;
		}

		public T getUserObject() {
			return userObject;
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
