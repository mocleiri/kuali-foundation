package org.kuali.common.util.spring.env;

import java.util.Properties;

import org.kuali.common.util.property.ImmutableProperties;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

public class PropertiesEnvironment extends AbstractEnvironment {

	private static final String NAME = "properties";

	public PropertiesEnvironment(Properties properties) {
		this.properties = ImmutableProperties.of(properties);
		super.getPropertySources().addLast(new PropertiesPropertySource(NAME, properties));
	}

	private final Properties properties;

	public Properties getProperties() {
		return properties;
	}

}
