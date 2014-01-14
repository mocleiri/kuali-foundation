package org.kuali.common.util.properties;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.property.PropertyFormat;

public final class Location {

	public static final Mode DEFAULT_MISSING_MODE = Mode.ERROR;
	public static final boolean DEFAULT_CACHEABLE = false;
	public static final PropertyFormat DEFAULT_PROPERTY_FORMAT = PropertyFormat.NORMAL;
	public static final String DEFAULT_ENCODING = Charset.defaultCharset().toString();

	private final Mode missingMode;
	private final String encoding;
	private final PropertyFormat format;
	private final String value;
	private final boolean cacheable;

	public Location(String value) {
		this(value, DEFAULT_ENCODING, DEFAULT_MISSING_MODE, DEFAULT_PROPERTY_FORMAT);
	}

	public Location(String value, String encoding) {
		this(value, encoding, DEFAULT_MISSING_MODE, DEFAULT_PROPERTY_FORMAT);
	}

	public Location(String value, String encoding, boolean cacheable) {
		this(value, encoding, DEFAULT_MISSING_MODE, DEFAULT_PROPERTY_FORMAT, cacheable);
	}

	public Location(String value, String encoding, Mode missingMode, PropertyFormat format) {
		this(value, encoding, DEFAULT_MISSING_MODE, DEFAULT_PROPERTY_FORMAT, DEFAULT_CACHEABLE);
	}

	public Location(String value, String encoding, Mode missingMode, PropertyFormat format, boolean cacheable) {
		this.value = value;
		this.encoding = encoding;
		this.missingMode = missingMode;
		this.format = format;
		this.cacheable = cacheable;
		Builder.validate(this);
	}

	public Mode getMissingMode() {
		return missingMode;
	}

	public String getEncoding() {
		return encoding;
	}

	public PropertyFormat getFormat() {
		return format;
	}

	public String getValue() {
		return value;
	}

	public boolean isCacheable() {
		return cacheable;
	}

	private Location(Builder builder) {
		this.missingMode = builder.missingMode;
		this.encoding = builder.encoding;
		this.format = builder.format;
		this.value = builder.value;
		this.cacheable = builder.cacheable;
	}

	public static Builder builder(String value) {
		return new Builder(value);
	}

	/**
	 * Create a new {@code Location} identical to an existing location but with {@code newValue} for its value
	 */
	public static Builder builder(Location existing, String newValue) {
		return new Builder(newValue).cacheable(existing.isCacheable()).encoding(existing.getEncoding()).format(existing.getFormat()).missingMode(existing.getMissingMode());
	}

	public static class Builder {

		private final String value;
		private Mode missingMode = DEFAULT_MISSING_MODE;
		private String encoding = DEFAULT_ENCODING;
		private PropertyFormat format = DEFAULT_PROPERTY_FORMAT;
		private boolean cacheable = DEFAULT_CACHEABLE;

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

		public Builder format(PropertyFormat format) {
			this.format = format;
			return this;
		}

		public Builder cacheable(boolean cacheable) {
			this.cacheable = cacheable;
			return this;
		}

		public Location build() {
			Location instance = new Location(this);
			validate(instance);
			return instance;
		}

		private static void validate(Location instance) {
			checkArgument(!StringUtils.isBlank(instance.value), "value cannot be blank");
			checkArgument(!StringUtils.isBlank(instance.encoding), "encoding cannot be blank");
			checkNotNull(instance.format, "format cannot be null");
			checkNotNull(instance.missingMode, "missingMode cannot be null");
		}

		public Mode getMissingMode() {
			return missingMode;
		}

		public void setMissingMode(Mode missingMode) {
			this.missingMode = missingMode;
		}

		public String getEncoding() {
			return encoding;
		}

		public void setEncoding(String encoding) {
			this.encoding = encoding;
		}

		public PropertyFormat getFormat() {
			return format;
		}

		public void setFormat(PropertyFormat format) {
			this.format = format;
		}

		public boolean isCacheable() {
			return cacheable;
		}

		public void setCacheable(boolean cacheable) {
			this.cacheable = cacheable;
		}

		public String getValue() {
			return value;
		}
	}

}
