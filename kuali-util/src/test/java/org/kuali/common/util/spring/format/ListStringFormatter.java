package org.kuali.common.util.spring.format;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Locale;

import org.kuali.common.util.nullify.NullUtils;
import org.springframework.format.Formatter;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public final class ListStringFormatter implements Formatter<List<String>> {

	private final boolean trim;
	private final boolean omitEmpty;
	private final Optional<String> magicEmptyString;
	private final char separator;

	// Not exposed via getters/setters
	private final Splitter splitter;
	private final Joiner joiner;

	@Override
	public List<String> parse(String files, Locale locale) {
		if (magicEmptyString.isPresent() && files.equals(magicEmptyString.get())) {
			return Lists.newArrayList();
		} else {
			return Lists.newArrayList(splitter.split(files));
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
		return builder(separator).build();
	}

	public static Builder builder(char separator) {
		return new Builder(separator);
	}

	public static class Builder implements org.kuali.common.util.build.Builder<ListStringFormatter> {

		// Required
		private final char separator;

		// Optional
		private boolean trim = false;
		private boolean omitEmpty = false;
		private Optional<String> magicEmptyString = Optional.of(NullUtils.NONE);

		// Filled in by the build method
		private Splitter splitter;
		private Joiner joiner;

		public Builder(char separator) {
			this.separator = separator;
		}

		public Builder trim(boolean trim) {
			this.trim = trim;
			return this;
		}

		public Builder magicEmptyString(String magicEmptyString) {
			this.magicEmptyString = Optional.of(magicEmptyString);
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
