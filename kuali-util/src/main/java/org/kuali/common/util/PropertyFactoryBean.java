package org.kuali.common.util;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;

public class PropertyFactoryBean implements FactoryBean<Properties> {

	List<String> locations;
	String encoding;
	String include;
	String exclude;
	boolean includeEnvironmentVariables;
	boolean includeSystemProperties;
	boolean resolvePlaceholders;

	@Override
	public Properties getObject() throws Exception {
		Properties properties = PropertyUtils.getProperties(locations, encoding);
		if (includeEnvironmentVariables) {
			properties.putAll(PropertyUtils.getEnvAsProperties());
		}
		if (includeSystemProperties) {
			properties.putAll(PropertyUtils.getEnvAsProperties());
		}
		PropertyUtils.trim(properties, include, exclude);
		return properties;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
