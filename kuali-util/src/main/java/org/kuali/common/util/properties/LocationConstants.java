package org.kuali.common.util.properties;

import java.nio.charset.Charset;

import org.kuali.common.util.Mode;
import org.kuali.common.util.property.PropertyFormat;

public abstract class LocationConstants {

	public static final Mode DEFAULT_MISSING_MODE = Mode.ERROR;
	public static final boolean DEFAULT_CACHEABLE = false;
	public static final PropertyFormat DEFAULT_PROPERTY_FORMAT = PropertyFormat.NORMAL;
	public static final String DEFAULT_ENCODING = Charset.defaultCharset().toString();

}
