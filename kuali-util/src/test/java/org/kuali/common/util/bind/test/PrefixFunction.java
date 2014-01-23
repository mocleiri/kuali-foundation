package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public final class PrefixFunction implements Function<String, String> {

	private final String prefix;
	private final Optional<String> separator;

	@Override
	public String apply(String input) {
		checkNotNull(input, "'input' cannot be null");
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

	public static PrefixFunction make(String prefix) {
		return builder(prefix).build();
	}

	public static PrefixFunction make(String prefix, String separator) {
		return builder(prefix).separator(separator).build();
	}

	public static Builder builder(String prefix) {
		return new Builder(prefix);
	}

	public static class Builder implements org.kuali.common.util.build.Builder<PrefixFunction> {

		private final String prefix;
		private Optional<String> separator = Optional.absent();

		public Builder(String prefix) {
			this.prefix = prefix;
		}

		public Builder separator(String separator) {
			this.separator = Optional.of(separator);
			return this;
		}

		@Override
		public PrefixFunction build() {
			PrefixFunction instance = new PrefixFunction(this);
			validate(instance);
			return instance;
		}

		private static void validate(PrefixFunction instance) {
			checkArgument(!StringUtils.isBlank(instance.prefix), "'prefix' cannot be blank");
			checkNotNull(instance.separator, "'separator' cannot be null");
			if (instance.separator.isPresent()) {
				checkArgument(!StringUtils.isBlank(instance.separator.get()), "'separator' cannot be blank");
			}
		}

		public Optional<String> getSeparator() {
			return separator;
		}

		public void setSeparator(Optional<String> separator) {
			this.separator = separator;
		}

		public String getPrefix() {
			return prefix;
		}
	}

}
