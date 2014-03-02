package org.kuali.common.util.spring.format;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public final class ListStringFormatter implements Formatter<List<String>> {

	private final String emptyListToken;
	private final String separator;

	// Not exposed via getters/setters
	private final Splitter splitter;
	private final Joiner joiner;

	@Override
	public List<String> parse(String string, Locale locale) {
		if (emptyListToken.equals(string)) {
			return newArrayList();
		} else {
			return newArrayList(splitter.split(string));
		}
	}

	@Override
	public String print(List<String> strings, Locale locale) {
		if (strings.isEmpty()) {
			return emptyListToken;
		} else {
			return joiner.join(strings);
		}
	}

	private ListStringFormatter(Builder builder) {
		this.splitter = builder.splitter;
		this.joiner = builder.joiner;
		this.separator = builder.separator;
		this.emptyListToken = builder.emptyListToken;
	}

	public static ListStringFormatter make(String separator) {
		return builder().separator(separator).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<ListStringFormatter> {

		private String separator = ",";
		private String emptyListToken = FormatTokens.EMPTY_LIST_TOKEN;

		// Filled in by the build method
		private Splitter splitter;
		private Joiner joiner;

		public Builder emptyListToken(String emptyListToken) {
			this.emptyListToken = emptyListToken;
			return this;
		}

		public Builder separator(String separator) {
			this.separator = separator;
			return this;
		}

		@Override
		public ListStringFormatter build() {
			this.splitter = Splitter.on(separator);
			this.joiner = Joiner.on(separator);
			return new ListStringFormatter(this);
		}

		public String getSeparator() {
			return separator;
		}

	}

	public String getSeparator() {
		return separator;
	}

	public String getEmptyListToken() {
		return emptyListToken;
	}

}
