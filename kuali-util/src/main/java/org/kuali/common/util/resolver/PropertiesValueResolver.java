package org.kuali.common.util.resolver;

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.ImmutableProperties;
import org.springframework.util.PropertyPlaceholderHelper;

public final class PropertiesValueResolver implements ValueResolver {

	private static final PropertyPlaceholderHelper DEFAULT_HELPER = new PropertyPlaceholderHelper("${", "}", ":", false);

	private final Properties properties;
	private final PropertyPlaceholderHelper helper;

	public PropertiesValueResolver() {
		this(PropertyUtils.EMPTY);
	}

	public PropertiesValueResolver(Properties properties) {
		this(properties, DEFAULT_HELPER);
	}

	public PropertiesValueResolver(Properties properties, PropertyPlaceholderHelper helper) {
		super();
		Assert.notNull(properties, "properties are null");
		Assert.notNull(helper, "helper is null");
		this.properties = new ImmutableProperties(properties);
		this.helper = helper;
	}

	@Override
	public String resolve(String value) {
		return helper.replacePlaceholders(value, properties);
	}

}
