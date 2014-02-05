package org.kuali.common.util.spring.format;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Locale;

import org.kuali.common.util.base.Replacer;
import org.springframework.format.Formatter;

public final class CsvStringFormatter implements Formatter<String> {

	private final String nullToken;
	private final String emptyToken;
	private final Replacer replacer;

	@Override
	public String print(String string, Locale locale) {
		if (string == null) {
			return nullToken;
		} else if (string.equals("")) {
			return emptyToken;
		} else {
			return replacer.replace(string);
		}

	}

	@Override
	public String parse(String string, Locale locale) {
		if (nullToken.equals(string)) {
			return null;
		} else if (emptyToken.equals(string)) {
			return "";
		} else {
			return replacer.restore(string);
		}
	}

	private CsvStringFormatter(Builder builder) {
		this.nullToken = builder.nullToken;
		this.emptyToken = builder.emptyToken;
		this.replacer = builder.replacer;
	}

	public static CsvStringFormatter create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.kuali.common.util.base.Builder<CsvStringFormatter> {

		private String nullToken = "${csv.null}";
		private String emptyToken = "${csv.empty}";
		private Replacer replacer = Replacer.builder().add("\r", "${csv.cr}").add("\n", "${csv.lf}").add(",", "${csv.comma}").build();

		public Builder nullToken(String nullToken) {
			this.nullToken = nullToken;
			return this;
		}

		public Builder emptyToken(String emptyToken) {
			this.emptyToken = emptyToken;
			return this;
		}

		public Builder replacer(Replacer replacer) {
			this.replacer = replacer;
			return this;
		}

		@Override
		public CsvStringFormatter build() {
			CsvStringFormatter instance = new CsvStringFormatter(this);
			validate(instance);
			return instance;
		}

		private static void validate(CsvStringFormatter instance) {
			checkArgument(!isBlank(instance.nullToken), "'nullToken' cannot be blank");
			checkArgument(!isBlank(instance.emptyToken), "'emptyToken' cannot be blank");
			checkNotNull(instance.replacer, "'replacer' cannot be null");
		}

		public String getNullToken() {
			return nullToken;
		}

		public void setNullToken(String nullToken) {
			this.nullToken = nullToken;
		}

		public String getEmptyToken() {
			return emptyToken;
		}

		public void setEmptyToken(String emptyToken) {
			this.emptyToken = emptyToken;
		}

		public Replacer getReplacer() {
			return replacer;
		}

		public void setReplacer(Replacer replacer) {
			this.replacer = replacer;
		}
	}

	public String getNullToken() {
		return nullToken;
	}

	public String getEmptyToken() {
		return emptyToken;
	}

	public Replacer getReplacer() {
		return replacer;
	}

}
