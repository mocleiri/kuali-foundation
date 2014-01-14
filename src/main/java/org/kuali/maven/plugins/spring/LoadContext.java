package org.kuali.maven.plugins.spring;

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.spring.service.SpringService;

public final class LoadContext {

	private final Properties mavenProperties;
	private final SpringService service;

	public LoadContext(Properties mavenProperties, SpringService service) {
		Assert.noNulls(mavenProperties, service);
		this.mavenProperties = mavenProperties;
		this.service = service;
	}

	public Properties getMavenProperties() {
		return mavenProperties;
	}

	public SpringService getService() {
		return service;
	}

}
