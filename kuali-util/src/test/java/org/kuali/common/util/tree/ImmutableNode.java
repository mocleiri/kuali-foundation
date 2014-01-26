package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class ImmutableNode<T> extends AbstractNode<T> {

	private final Optional<ImmutableNode<T>> parent;
	private final T element;
	private final ImmutableList<ImmutableNode<T>> children;

	private ImmutableNode(Builder<T> builder) {
		this.parent = Optional.fromNullable(copyOf(builder.parent.orNull()));
		this.children = ImmutableList.copyOf(copyOf(builder.children));
		this.element = builder.element;
	}

	public static <T> ImmutableNode<T> of(T element) {
		return builder(element).build();
	}

	public static <T> ImmutableNode<T> of(T element, List<ImmutableNode<T>> children) {
		return builder(element).children(children).build();
	}

	public static <T> ImmutableNode<T> copyOf(Node<T> root) {
		if (root instanceof ImmutableNode) {
			return (ImmutableNode<T>) root;
		} else {
			return of(root.getElement(), copyOf(root.getChildren()));
		}
	}

	public static <T> List<ImmutableNode<T>> copyOf(List<? extends Node<T>> list) {
		List<ImmutableNode<T>> immutable = Lists.newArrayList();
		for (Node<T> element : list) {
			immutable.add(copyOf(element));
		}
		return immutable;
	}

	public static <T> Builder<T> builder(T element) {
		return new Builder<T>(element);
	}

	public static class Builder<T> implements org.kuali.common.util.build.Builder<ImmutableNode<T>> {

		// Required
		private final T element;

		// Optional
		private Optional<? extends Node<T>> parent = Optional.absent();
		private List<? extends Node<T>> children = ImmutableList.of();

		public Builder(T element) {
			this.element = element;
		}

		public Builder<T> parent(Optional<? extends Node<T>> parent) {
			this.parent = parent;
			return this;
		}

		public Builder<T> parent(Node<T> parent) {
			return parent(Optional.of(parent));
		}

		public Builder<T> children(List<? extends Node<T>> children) {
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
			checkNotNull(instance.element, "'element' cannot be null");
		}

		public Optional<? extends Node<T>> getParent() {
			return parent;
		}

		public void setParent(Optional<? extends Node<T>> parent) {
			this.parent = parent;
		}

		public List<? extends Node<T>> getChildren() {
			return children;
		}

		public void setChildren(List<? extends Node<T>> children) {
			this.children = children;
		}

		public T getElement() {
			return element;
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
	public T getElement() {
		return element;
	}

}
