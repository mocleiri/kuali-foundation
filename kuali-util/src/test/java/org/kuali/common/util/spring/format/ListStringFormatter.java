package org.kuali.common.util.spring.format;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.springframework.format.Formatter;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public final class ListStringFormatter implements Formatter<List<String>> {

	private final boolean trim;
	private final boolean omitEmpty;
	private final Optional<String> magicEmptyString;
	private final char separator;

	// Not exposed via getters/setters
	private final Splitter splitter;
	private final Joiner joiner;

	@Override
	public List<String> parse(String string, Locale locale) {
		if (magicEmptyString.isPresent() && magicEmptyString.get().equals(string)) {
			return newArrayList();
		} else {
			return newArrayList(splitter.split(string));
		}
	}

	@Override
	public String print(List<String> files, Locale locale) {
		if (magicEmptyString.isPresent() && files.isEmpty()) {
			return magicEmptyString.get();
		} else {
			return joiner.join(files.iterator());
		}
	}

	private ListStringFormatter(Builder builder) {
		this.splitter = builder.splitter;
		this.joiner = builder.joiner;
		this.trim = builder.trim;
		this.omitEmpty = builder.omitEmpty;
		this.separator = builder.separator;
		this.magicEmptyString = builder.magicEmptyString;
	}

	public static ListStringFormatter make(char separator) {
		return builder().separator(separator).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<ListStringFormatter> {

		private char separator = ',';
		private boolean trim = true;
		private boolean omitEmpty = true;
		private Optional<String> magicEmptyString = Optional.of(NullUtils.NONE);

		// Filled in by the build method
		private Splitter splitter;
		private Joiner joiner;

		public Builder separator(char separator) {
			this.separator = separator;
			return this;
		}

		public Builder trim(boolean trim) {
			this.trim = trim;
			return this;
		}

		public Builder magicEmptyString(String magicEmptyString) {
			return magicEmptyString(Optional.of(magicEmptyString));
		}

		public Builder magicEmptyString(Optional<String> magicEmptyString) {
			this.magicEmptyString = magicEmptyString;
			return this;
		}

		public Builder omitEmpty(boolean omitEmpty) {
			this.omitEmpty = omitEmpty;
			return this;
		}

		@Override
		public ListStringFormatter build() {
			this.splitter = getSplitter(this);
			this.joiner = Joiner.on(separator);
			ListStringFormatter instance = new ListStringFormatter(this);
			validate(instance);
			return instance;
		}

		private static Splitter getSplitter(Builder builder) {
			Splitter splitter = Splitter.on(builder.separator);
			if (builder.trim) {
				splitter.trimResults();
			}
			if (builder.omitEmpty) {
				splitter.omitEmptyStrings();
			}
			return splitter;
		}

		private static void validate(ListStringFormatter instance) {
			checkNotNull(instance.splitter, "'splitter' cannot be null");
			checkNotNull(instance.joiner, "'joiner' cannot be null");
			checkNotNull(instance.magicEmptyString, "'magicEmptyString' cannot be null");
			if (instance.magicEmptyString.isPresent()) {
				checkArgument(!StringUtils.isBlank(instance.magicEmptyString.get()), "'magicEmptyString' cannot be blank");
			}
		}

		public boolean isTrim() {
			return trim;
		}

		public void setTrim(boolean trim) {
			this.trim = trim;
		}

		public boolean isOmitEmpty() {
			return omitEmpty;
		}

		public void setOmitEmpty(boolean omitEmpty) {
			this.omitEmpty = omitEmpty;
		}

		public char getSeparator() {
			return separator;
		}

	}

	public boolean isTrim() {
		return trim;
	}

	public boolean isOmitEmpty() {
		return omitEmpty;
	}

	public char getSeparator() {
		return separator;
	}

}
