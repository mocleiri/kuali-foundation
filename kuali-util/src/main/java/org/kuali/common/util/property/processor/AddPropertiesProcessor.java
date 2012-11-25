package org.kuali.common.util.property.processor;

import java.util.Properties;

public class AddPropertiesProcessor implements PropertyProcessor {

	public AddPropertiesProcessor() {
		this(null);
	}

	public AddPropertiesProcessor(Properties properties) {
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
