package org.kuali.common.util.base;

import static org.kuali.common.util.base.Assertions.assertNotNull;

import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;

public final class Replacer {

	private final ImmutableBiMap<String, String> tokens;

	public String replace(final String string) {
		String s = string;
		for (Map.Entry<String, String> pair : tokens.entrySet()) {
			s = s.replace(pair.getKey(), pair.getValue());
		}
		return s;
	}

	public String restore(final String string) {
		String s = string;
		for (Map.Entry<String, String> pair : tokens.entrySet()) {
			s = s.replace(pair.getValue(), pair.getKey());
		}
		return s;
	}

	private Replacer(Builder builder) {
		this.tokens = ImmutableBiMap.copyOf(builder.tokens);
	}

	public static Replacer create(String oldToken, String newToken) {
		return builder().add(oldToken, newToken).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private BiMap<String, String> tokens = HashBiMap.create();

		public Builder add(String oldToken, String newToken) {
			this.tokens.put(oldToken, newToken);
			return this;
		}

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
			assertNotNull(instance.tokens, "tokens");
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
