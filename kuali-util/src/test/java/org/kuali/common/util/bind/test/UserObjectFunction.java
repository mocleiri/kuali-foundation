package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.common.base.Function;

public final class UserObjectFunction<T> implements Function<DefaultMutableTreeNode, String> {

	private Function<T, String> userObjectFunction;

	@Override
	public String apply(DefaultMutableTreeNode node) {
		checkNotNull(node, "'node' cannot be null'");
		@SuppressWarnings("unchecked")
		T userObject = (T) node.getUserObject();
		checkNotNull(userObject, "'userObject' cannot be null'");
		return userObjectFunction.apply(userObject);
	}

	private UserObjectFunction(Builder<T> builder) {
		this.userObjectFunction = builder.userObjectFunction;
	}

	public static <T> UserObjectFunction<T> make(Function<T, String> userObjectFunction) {
		return builder(userObjectFunction).build();
	}

	public static <T> Builder<T> builder(Function<T, String> userObjectFunction) {
		return new Builder<T>(userObjectFunction);
	}

	public static class Builder<T> implements org.kuali.common.util.build.Builder<UserObjectFunction<T>> {

		private final Function<T, String> userObjectFunction;

		public Builder(Function<T, String> userObjectFunction) {
			this.userObjectFunction = userObjectFunction;
		}

		@Override
		public UserObjectFunction<T> build() {
			UserObjectFunction<T> instance = new UserObjectFunction<T>(this);
			validate(instance);
			return instance;
		}

		private static <T> void validate(UserObjectFunction<T> instance) {
			checkNotNull(instance.userObjectFunction, "'userObjectFunction' cannot be null");
		}
	}

}
