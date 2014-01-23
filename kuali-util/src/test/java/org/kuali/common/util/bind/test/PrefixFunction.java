package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public final class PrefixFunction implements Function<String, String> {

	private final String prefix;
	private final Optional<String> separator;

	@Override
	@Nullable
	public String apply(@Nullable String input) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		if (separator.isPresent()) {
			sb.append(separator.get());
		}
		sb.append(input);
		return sb.toString();
	}

	private PrefixFunction(Builder builder) {
		this.prefix = builder.prefix;
		this.separator = builder.separator;
	}

	public static PrefixFunction create(String prefix) {
		return builder(prefix).build();
	}

	public static PrefixFunction create(String prefix, String separator) {
		return builder(prefix).separator(separator).build();
	}

	public static Builder builder(String prefix) {
		return new Builder(prefix);
	}

	public static class Builder {

		private final String prefix;
		private Optional<String> separator = Optional.absent();

		public Builder(String prefix) {
			this.prefix = prefix;
		}

		public Builder separator(String separator) {
			this.separator = Optional.of(separator);
			return this;
		}

		public PrefixFunction build() {
			PrefixFunction instance = new PrefixFunction(this);
			validate(instance);
			return instance;
		}

		private static void validate(PrefixFunction instance) {
			checkArgument(!StringUtils.isBlank(instance.prefix), "'prefix' cannot be blank");
			checkNotNull(instance.separator, "'separator' cannot be null");
		}
	}

}
