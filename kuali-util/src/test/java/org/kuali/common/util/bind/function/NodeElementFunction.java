package org.kuali.common.util.bind.function;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.util.tree.Node;

import com.google.common.base.Function;

public final class NodeElementFunction<T> implements Function<Node<T>, String> {

	public NodeElementFunction(Function<T, String> function) {
		checkNotNull(function, "'function' cannot be null");
		this.function = function;
	}

	public static <T> NodeElementFunction<T> of() {
		return of(new ToStringFunction<T>());
	}

	public static <T> NodeElementFunction<T> of(Function<T, String> function) {
		return new NodeElementFunction<T>(function);
	}

	private final Function<T, String> function;

	@Override
	public String apply(Node<T> node) {
		checkNotNull(node, "'node' cannot be null'");
		checkNotNull(node.getElement(), "'node.getElement()' cannot be null'");
		return function.apply(node.getElement());
	}

	private static class ToStringFunction<T> implements Function<T, String> {

		@Override
		public String apply(T element) {
			checkNotNull(element, "'element' cannot be null'");
			return element.toString();
		}
	}

}
