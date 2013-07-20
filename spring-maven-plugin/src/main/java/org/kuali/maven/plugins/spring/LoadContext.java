package org.kuali.maven.plugins.spring;

import java.util.Properties;

import org.kuali.common.util.spring.service.SpringService;

public class LoadContext {

	Properties mavenProperties;
	SpringService service;

	public LoadContext() {
		this(null, null);
	}

	public LoadContext(Properties mavenProperties, SpringService service) {
		super();
		this.mavenProperties = mavenProperties;
		this.service = service;
	}

	public Properties getMavenProperties() {
		return mavenProperties;
	}

	public void setMavenProperties(Properties mavenProperties) {
		this.mavenProperties = mavenProperties;
	}

	public SpringService getService() {
		return service;
	}

	public void setService(SpringService service) {
		this.service = service;
	}

}
