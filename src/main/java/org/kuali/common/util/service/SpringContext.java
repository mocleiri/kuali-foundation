package org.kuali.common.util.service;

import java.util.List;

public class SpringContext {

	List<String> locations;
	List<Class<?>> annotatedClasses;
	List<String> beanNames;
	List<Object> beans;
	PropertySourceContext propertySourceContext;

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public List<Class<?>> getAnnotatedClasses() {
		return annotatedClasses;
	}

	public void setAnnotatedClasses(List<Class<?>> annotatedClasses) {
		this.annotatedClasses = annotatedClasses;
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

	public PropertySourceContext getPropertySourceContext() {
		return propertySourceContext;
	}

	public void setPropertySourceContext(PropertySourceContext propertySourceContext) {
		this.propertySourceContext = propertySourceContext;
	}

}
