package org.kuali.common.util.properties;

import java.nio.charset.Charset;

import org.kuali.common.util.Assert;
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
		Assert.noBlanks(value, encoding);
		Assert.noNulls(missingMode, format);
		this.value = value;
		this.encoding = encoding;
		this.missingMode = missingMode;
		this.format = format;
		this.cacheable = cacheable;
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

}
