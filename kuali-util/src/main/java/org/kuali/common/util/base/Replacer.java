package org.kuali.common.util.base;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;

public final class Replacer {

	private final ImmutableBiMap<String, String> tokens;

	public String replace(String string) {
		for (Map.Entry<String, String> pair : tokens.entrySet()) {
			String oldToken = pair.getKey();
			String newToken = pair.getValue();
			string = string.replace(oldToken, newToken);
		}
		return string;
	}

	public String restore(String string) {
		for (Map.Entry<String, String> pair : tokens.entrySet()) {
			String oldToken = pair.getValue();
			String newToken = pair.getKey();
			string = string.replace(oldToken, newToken);
		}
		return string;
	}

	private Replacer(Builder builder) {
		this.tokens = ImmutableBiMap.copyOf(builder.tokens);
	}

	public static class Builder {

		private BiMap<String, String> tokens = HashBiMap.create();

		public Builder tokens(BiMap<String, String> tokens) {
			this.tokens = tokens;
			return this;
		}

		public Replacer build() {
			Replacer instance = new Replacer(this);
			validate(instance);
			return instance;
		}

		private static void validate(Replacer instance) {
			checkNotNull(instance.tokens, "'tokens' cannot be null");
		}

		public BiMap<String, String> getTokens() {
			return tokens;
		}

		public void setTokens(BiMap<String, String> tokens) {
			this.tokens = tokens;
		}
	}

	public BiMap<String, String> getTokens() {
		return tokens;
	}

}
