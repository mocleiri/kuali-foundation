package org.kuali.common.util.resolver;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultValueResolver implements ValueResolver {

	private static final PropertyPlaceholderHelper DEFAULT_HELPER = new PropertyPlaceholderHelper("${", "}", ":", false);

	final Properties properties;
	final PropertyPlaceholderHelper helper;

	public DefaultValueResolver() {
		this(PropertyUtils.EMPTY);
	}

	public DefaultValueResolver(Properties properties) {
		this(properties, DEFAULT_HELPER);
	}

	public DefaultValueResolver(Properties properties, PropertyPlaceholderHelper helper) {
		super();
		this.properties = properties;
		this.helper = helper;
	}

	@Override
	public String resolve(String value) {
		Properties global = PropertyUtils.getGlobalProperties();
		Properties resolver = PropertyUtils.combine(properties, global);
		return helper.replacePlaceholders(value, resolver);
	}

}
