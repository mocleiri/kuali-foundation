package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.common.base.Function;

public final class UserObjectFunction<T> implements Function<DefaultMutableTreeNode, String> {

	public UserObjectFunction(Function<T, String> function) {
		checkNotNull(function, "'function' cannot be null");
		this.function = function;
	}

	public static <T> UserObjectFunction<T> make(Function<T, String> function) {
		return new UserObjectFunction<T>(function);
	}

	private final Function<T, String> function;

	@Override
	public String apply(DefaultMutableTreeNode node) {
		checkNotNull(node, "'node' cannot be null'");
		checkNotNull(node.getUserObject(), "'node.getUserObject()' cannot be null'");
		@SuppressWarnings("unchecked")
		T userObject = (T) node.getUserObject();
		return function.apply(userObject);
	}

}
