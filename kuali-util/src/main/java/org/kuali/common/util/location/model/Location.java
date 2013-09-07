package org.kuali.common.util.location.model;

import java.nio.charset.Charset;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Mode;

public final class Location {

	private final Mode missingMode;
	private final String encoding;
	private final String value;
	private final boolean cacheable;

	public Mode getMissingMode() {
		return missingMode;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getValue() {
		return value;
	}

	public boolean isCacheable() {
		return cacheable;
	}

	public static class Builder {

		// Required
		private final String value;

		// Optional
		private Mode missingMode = Mode.ERROR;
		private String encoding = Charset.defaultCharset().toString();
		private boolean cacheable = true;

		public Builder(String value) {
			this.value = value;
		}

		public Builder missingMode(Mode missingMode) {
			this.missingMode = missingMode;
			return this;
		}

		public Builder encoding(String encoding) {
			this.encoding = encoding;
			return this;
		}

		public Builder cacheable(boolean cacheable) {
			this.cacheable = cacheable;
			return this;
		}

		public Location build() {
			Assert.noBlanks(value, encoding);
			Assert.noNulls(missingMode);
			return new Location(this);
		}

	}

	private Location(Builder builder) {
		this.value = builder.value;
		this.missingMode = builder.missingMode;
		this.encoding = builder.encoding;
		this.cacheable = builder.cacheable;
	}

}
