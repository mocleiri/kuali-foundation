package org.kuali.common.util.env.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.ImmutableProperties;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

public final class PropertiesEnvironment extends AbstractEnvironment {

	private static final String NAME = "properties";

	/**
	 * Default environment that uses system properties / environment variables (prefixed with <code>env</code>)
	 */
	public PropertiesEnvironment() {
		this(PropertyUtils.getGlobalProperties());
	}

	/**
	 * Create an environment based on <code>properties</code>
	 */
	public PropertiesEnvironment(Properties properties) {
		this.properties = ImmutableProperties.of(properties);
		super.getPropertySources().addLast(new PropertiesPropertySource(NAME, properties));
	}

	private final Properties properties;

	public Properties getProperties() {
		return properties;
	}

}
