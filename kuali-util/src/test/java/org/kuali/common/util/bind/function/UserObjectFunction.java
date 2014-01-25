package org.kuali.common.util.bind.function;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.util.tree.Node;

import com.google.common.base.Function;

public final class UserObjectFunction<T> implements Function<Node<T>, String> {

	public UserObjectFunction(Function<T, String> function) {
		checkNotNull(function, "'function' cannot be null");
		this.function = function;
	}

	public static <T> UserObjectFunction<T> of(Function<T, String> function) {
		return new UserObjectFunction<T>(function);
	}

	private final Function<T, String> function;

	@Override
	public String apply(Node<T> node) {
		checkNotNull(node, "'node' cannot be null'");
		checkNotNull(node.getUserObject(), "'node.getUserObject()' cannot be null'");
		return function.apply(node.getUserObject());
	}

}
