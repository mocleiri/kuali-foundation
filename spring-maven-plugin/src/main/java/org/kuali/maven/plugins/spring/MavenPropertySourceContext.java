package org.kuali.maven.plugins.spring;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.spring.service.SpringService;

public final class MavenPropertySourceContext {

	private final SpringService service;
	private final String location;
	private final Class<?> config;
	private final String propertiesBeanName;
	private final Properties properties;
	private final List<String> activeProfiles;
	private final List<String> defaultProfiles;

	public SpringService getService() {
		return service;
	}

	public String getLocation() {
		return location;
	}

	public Class<?> getConfig() {
		return config;
	}

	public String getPropertiesBeanName() {
		return propertiesBeanName;
	}

	public Properties getProperties() {
		return properties;
	}

	public List<String> getActiveProfiles() {
		return activeProfiles;
	}

	public List<String> getDefaultProfiles() {
		return defaultProfiles;
	}

	public static class Builder {

		// Required
		private final SpringService service;
		private final Properties properties;

		// Optional
		private String location;
		private Class<?> config;

		private String propertiesBeanName = MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;
		private List<String> activeProfiles = Collections.<String> emptyList();
		private List<String> defaultProfiles = Collections.<String> emptyList();

		public Builder(SpringService service, Properties properties) {
			this.service = service;
			this.properties = properties;
		}

		public MavenPropertySourceContext build() {
			Assert.noNulls(service, properties, activeProfiles, defaultProfiles);
			Assert.noBlanks(propertiesBeanName);
			Assert.isFalse(location == null && config == null, "Either location or config are required");
			Assert.isTrue(location == null || config == null, "Cannot supply both location and config");
			this.activeProfiles = ListUtils.newImmutableArrayList(activeProfiles);
			this.defaultProfiles = ListUtils.newImmutableArrayList(defaultProfiles);
			return new MavenPropertySourceContext(this);
		}

		public Builder location(String location) {
			this.location = location;
			return this;
		}

		public Builder config(Class<?> config) {
			this.config = config;
			return this;
		}

		public Builder propertiesBeanName(String propertiesBeanName) {
			this.propertiesBeanName = propertiesBeanName;
			return this;
		}

		public Builder activeProfiles(List<String> activeProfiles) {
			this.activeProfiles = activeProfiles;
			return this;
		}

		public Builder defaultProfiles(List<String> defaultProfiles) {
			this.defaultProfiles = defaultProfiles;
			return this;
		}

	}

	private MavenPropertySourceContext(Builder builder) {
		this.service = builder.service;
		this.location = builder.location;
		this.config = builder.config;
		this.propertiesBeanName = builder.propertiesBeanName;
		this.properties = builder.properties;
		this.activeProfiles = builder.activeProfiles;
		this.defaultProfiles = builder.defaultProfiles;
	}

}
