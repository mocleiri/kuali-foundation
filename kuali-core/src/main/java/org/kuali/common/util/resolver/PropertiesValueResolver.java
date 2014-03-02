package org.kuali.common.util.resolver;

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertiesValueResolver implements ValueResolver {

	private static final String PREFIX = "${";
	private static final String SUFFIX = "}";
	private static final String SEPARATOR = ":";

	public static final boolean DEFAULT_IGNORE_UNRESOLVABLE = false;

	private static final PropertyPlaceholderHelper DEFAULT_HELPER = new PropertyPlaceholderHelper(PREFIX, SUFFIX, SEPARATOR, DEFAULT_IGNORE_UNRESOLVABLE);

	private final Properties properties;
	private final PropertyPlaceholderHelper helper;

	public PropertiesValueResolver() {
		this(PropertyUtils.EMPTY);
	}

	public PropertiesValueResolver(Properties properties) {
		this(properties, DEFAULT_HELPER);
	}

	public PropertiesValueResolver(Properties properties, boolean ignoreUnresolvable) {
		this(properties, new PropertyPlaceholderHelper(PREFIX, SUFFIX, SEPARATOR, ignoreUnresolvable));
	}

	public PropertiesValueResolver(Properties properties, PropertyPlaceholderHelper helper) {
		Assert.noNulls(properties, helper);
		this.properties = PropertyUtils.toImmutable(properties);
		this.helper = helper;
	}

	@Override
	public String resolve(String value) {
		return helper.replacePlaceholders(value, properties);
	}

	public Properties getProperties() {
		return properties;
	}

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

}
