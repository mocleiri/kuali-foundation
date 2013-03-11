package org.kuali.common.util.service;

import java.util.List;

import org.springframework.core.env.PropertySource;

public class SpringContext {

	List<String> locations;
	List<String> beanNames;
	List<Object> beans;
	List<PropertySource<?>> propertySources;

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public List<String> getBeanNames() {
		return beanNames;
	}

	public void setBeanNames(List<String> beanNames) {
		this.beanNames = beanNames;
	}

	public List<Object> getBeans() {
		return beans;
	}

	public void setBeans(List<Object> beans) {
		this.beans = beans;
	}

	public List<PropertySource<?>> getPropertySources() {
		return propertySources;
	}

	public void setPropertySources(List<PropertySource<?>> propertySources) {
		this.propertySources = propertySources;
	}

}
