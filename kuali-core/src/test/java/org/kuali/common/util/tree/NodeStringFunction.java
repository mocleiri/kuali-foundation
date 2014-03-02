package org.kuali.common.util.tree;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.google.common.base.Function;

public final class NodeStringFunction<T> implements Function<Node<T>, String> {

	public NodeStringFunction(Function<T, String> function) {
		checkNotNull(function, "function");
		this.function = function;
	}

	/**
	 * Convert the element contained in each node to a string by calling it's toString() method
	 */
	public static <T> NodeStringFunction<T> create() {
		return create(new ToStringFunction<T>());
	}

	/**
	 * Convert the element contained in each node to a string by invoking {@code function} on it
	 */
	public static <T> NodeStringFunction<T> create(Function<T, String> function) {
		return new NodeStringFunction<T>(function);
	}

	private final Function<T, String> function;

	@Override
	public String apply(Node<T> node) {
		checkNotNull(node, "node");
		checkNotNull(node.getElement(), "node.element");
		return function.apply(node.getElement());
	}

	private static class ToStringFunction<T> implements Function<T, String> {

		@Override
		public String apply(T element) {
			checkNotNull(element, "element");
			return element.toString();
		}
	}

}
