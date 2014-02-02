package org.kuali.common.util.base;

import java.util.List;

import com.google.common.base.Preconditions;

public final class CharReplacer {

	private final List<String> oldTokens;
	private final List<String> newTokens;
	private final int tokens;

	public String replace(String string) {
		for (int i = 0; i < tokens; i++) {
			String oldToken = oldTokens.get(i);
			String newToken = newTokens.get(i);
			string = string.replace(oldToken, newToken);
		}
		return string;
	}

	public String restore(String string) {
		for (int i = 0; i < tokens; i++) {
			String oldToken = oldTokens.get(i);
			String newToken = newTokens.get(i);
			string = string.replace(newToken, oldToken);
		}
		return string;
	}

	private CharReplacer(Builder builder) {
		this.oldTokens = builder.oldTokens;
		this.newTokens = builder.newTokens;
		this.tokens = builder.tokens;
	}

	public static class Builder {

		private List<String> oldTokens=
		private List<String> newTokens;

		// Filled in by the build method
		private int tokens;

		public Builder oldTokens(List<String> oldTokens) {
			this.oldTokens = oldTokens;
			return this;
		}

		public Builder newTokens(List<String> newTokens) {
			this.newTokens = newTokens;
			return this;
		}

		public Builder tokens(int tokens) {
			this.tokens = tokens;
			return this;
		}

		public Replacer build() {
			Replacer instance = new Replacer(this);
			validate(instance);
			return instance;
		}

		private static void validate(Replacer instance) {
			Preconditions.checkArgument(!StringUtils.isBlank(instance.oldTokens), "'oldTokens' cannot be blank");
			Preconditions.checkArgument(!StringUtils.isBlank(instance.newTokens), "'newTokens' cannot be blank");

		}
	}

}
