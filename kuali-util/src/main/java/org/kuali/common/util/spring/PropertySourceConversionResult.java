package org.kuali.common.util.spring;

import java.util.List;
import java.util.Properties;

import org.springframework.core.env.PropertySource;

public class PropertySourceConversionResult {

	Properties properties;
	List<PropertySource<?>> converted;
	List<PropertySource<?>> skipped;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public List<PropertySource<?>> getConverted() {
		return converted;
	}

	public void setConverted(List<PropertySource<?>> converted) {
		this.converted = converted;
	}

	public List<PropertySource<?>> getSkipped() {
		return skipped;
	}

	public void setSkipped(List<PropertySource<?>> skipped) {
		this.skipped = skipped;
	}

}
