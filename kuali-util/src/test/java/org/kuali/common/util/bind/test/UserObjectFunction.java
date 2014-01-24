package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.common.base.Function;

public final class UserObjectFunction<T> implements Function<DefaultMutableTreeNode, String> {

	public UserObjectFunction(Function<T, String> userObjectFunction) {
		checkNotNull(userObjectFunction, "'userObjectFunction' cannot be null");
		this.userObjectFunction = userObjectFunction;
	}

	public static <T> UserObjectFunction<T> make(Function<T, String> userObjectFunction) {
		return new UserObjectFunction<T>(userObjectFunction);
	}

	private final Function<T, String> userObjectFunction;

	@Override
	public String apply(DefaultMutableTreeNode node) {
		checkNotNull(node, "'node' cannot be null'");
		checkNotNull(node.getUserObject(), "'node.getUserObject()' cannot be null'");
		@SuppressWarnings("unchecked")
		T userObject = (T) node.getUserObject();
		return userObjectFunction.apply(userObject);
	}

}
