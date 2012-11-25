package org.kuali.common.util.property.modifier;

import java.util.Properties;

public class AddPropertiesModifier implements PropertyProcessor {

	public AddPropertiesModifier() {
		this(null);
	}

	public AddPropertiesModifier(Properties properties) {
		super();
		this.properties = properties;
	}

	Properties properties;

	@Override
	public void process(Properties properties) {
		properties.putAll(this.properties);
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
