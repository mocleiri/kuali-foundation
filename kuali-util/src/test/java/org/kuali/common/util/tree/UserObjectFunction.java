package org.kuali.common.util.tree;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Function;

public class UserObjectFunction<T> implements Function<MutableNode<T>, T> {

	public static <T> UserObjectFunction<T> make() {
		return new UserObjectFunction<T>();
	}

	@Override
	public T apply(MutableNode<T> node) {
		checkNotNull(node, "'node' cannot be null");
		return node.getUserObject();
	}

}
