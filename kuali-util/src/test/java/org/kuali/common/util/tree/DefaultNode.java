package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.base.Optional;

public final class DefaultNode<T> implements Node<T> {

	private final Optional<DefaultNode<T>> parent;
	private final List<DefaultNode<T>> children;
	private final T userObject;

	private DefaultNode(Builder<T> builder) {
		this.parent = builder.parent;
		this.children = builder.children;
		this.userObject = builder.userObject;
	}

	public static class Builder<T> implements org.kuali.common.util.build.Builder<DefaultNode<T>> {

		private Optional<DefaultNode<T>> parent;
		private List<DefaultNode<T>> children;
		private T userObject;

		public Builder<T> parent(Optional<DefaultNode<T>> parent) {
			this.parent = parent;
			return this;
		}

		public Builder<T> children(List<DefaultNode<T>> children) {
			this.children = children;
			return this;
		}

		public Builder<T> userObject(T userObject) {
			this.userObject = userObject;
			return this;
		}

		@Override
		public DefaultNode<T> build() {
			DefaultNode<T> instance = new DefaultNode<T>(this);
			validate(instance);
			return instance;
		}

		private static <T> void validate(DefaultNode<T> instance) {
			checkNotNull(instance.parent, "'parent' cannot be null");
			checkNotNull(instance.children, "'children' cannot be null");
			checkNotNull(instance.userObject, "'userObject' cannot be null");
		}

		public Optional<DefaultNode<T>> getParent() {
			return parent;
		}

		public void setParent(Optional<DefaultNode<T>> parent) {
			this.parent = parent;
		}

		public List<DefaultNode<T>> getChildren() {
			return children;
		}

		public void setChildren(List<DefaultNode<T>> children) {
			this.children = children;
		}

		public T getUserObject() {
			return userObject;
		}

		public void setUserObject(T userObject) {
			this.userObject = userObject;
		}
	}

	@Override
	public Optional<DefaultNode<T>> getParent() {
		return parent;
	}

	@Override
	public List<DefaultNode<T>> getChildren() {
		return children;
	}

	@Override
	public T getUserObject() {
		return userObject;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public List<? extends Node<T>> getPath() {
		return null;
	}

	@Override
	public List<T> getUserObjectPath() {
		return null;
	}

	@Override
	public boolean isRoot() {
		return !parent.isPresent();
	}

	@Override
	public boolean isLeaf() {
		return children.isEmpty();
	}

}
