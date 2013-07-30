package org.kuali.maven.plugins.spring;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.spring.service.SpringService;

public class PropertySourcesContext {

	SpringService service;
	String location;
	Class<?> annotatedClass;
	String propertiesBeanName;
	Properties properties;
	List<String> activeProfiles;
	List<String> defaultProfiles;

	public SpringService getService() {
		return service;
	}

	public void setService(SpringService service) {
		this.service = service;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Class<?> getAnnotatedClass() {
		return annotatedClass;
	}

	public void setAnnotatedClass(Class<?> annotatedClass) {
		this.annotatedClass = annotatedClass;
	}

	public String getPropertiesBeanName() {
		return propertiesBeanName;
	}

	public void setPropertiesBeanName(String propertiesBeanName) {
		this.propertiesBeanName = propertiesBeanName;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public List<String> getActiveProfiles() {
		return activeProfiles;
	}

	public void setActiveProfiles(List<String> activeProfiles) {
		this.activeProfiles = activeProfiles;
	}

	public List<String> getDefaultProfiles() {
		return defaultProfiles;
	}

	public void setDefaultProfiles(List<String> defaultProfiles) {
		this.defaultProfiles = defaultProfiles;
	}

}
