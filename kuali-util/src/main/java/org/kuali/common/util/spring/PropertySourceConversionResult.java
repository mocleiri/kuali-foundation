package org.kuali.common.util.spring;

import java.util.List;
import java.util.Properties;

import org.springframework.core.env.PropertySource;

public class PropertySourceConversionResult {

	List<Properties> propertiesList;
	List<PropertySource<?>> converted;
	List<PropertySource<?>> skipped;

	public List<Properties> getPropertiesList() {
		return propertiesList;
	}

	public void setPropertiesList(List<Properties> propertiesList) {
		this.propertiesList = propertiesList;
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
