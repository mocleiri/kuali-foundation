package org.kuali.common.util;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.property.PropertyLoadingContext;
import org.springframework.beans.factory.FactoryBean;

public class PropertyFactoryBean implements FactoryBean<Properties>, PropertyLoadingContext {

	List<String> locations;
	String encoding;
	String include;
	String exclude;
	boolean includeEnvironmentVariables;
	boolean includeSystemProperties;
	boolean resolvePlaceholders;
	boolean ignoreMissingLocations;

	@Override
	public Properties getObject() throws Exception {

		// Load properties from the locations they specified (in the order they specified)
		Properties properties = PropertyUtils.getProperties(locations, encoding);

		// Add in environment variables?
		if (includeEnvironmentVariables) {
			properties.putAll(PropertyUtils.getEnvAsProperties());
		}

		// Add in system properties?
		if (includeSystemProperties) {
			properties.putAll(System.getProperties());
		}

		// Resolve placeholders?
		if (resolvePlaceholders) {
			properties = PropertyUtils.getResolvedProperties(properties);
		}

		// Trim out unwanted properties
		PropertyUtils.trim(properties, include, exclude);

		// Return the properties we have left
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

	@Override
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	@Override
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	@Override
	public String getExclude() {
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

	@Override
	public boolean isIncludeEnvironmentVariables() {
		return includeEnvironmentVariables;
	}

	public void setIncludeEnvironmentVariables(boolean includeEnvironmentVariables) {
		this.includeEnvironmentVariables = includeEnvironmentVariables;
	}

	@Override
	public boolean isIncludeSystemProperties() {
		return includeSystemProperties;
	}

	public void setIncludeSystemProperties(boolean includeSystemProperties) {
		this.includeSystemProperties = includeSystemProperties;
	}

	@Override
	public boolean isResolvePlaceholders() {
		return resolvePlaceholders;
	}

	public void setResolvePlaceholders(boolean resolvePlaceholders) {
		this.resolvePlaceholders = resolvePlaceholders;
	}

	@Override
    public boolean isIgnoreMissingLocations() {
		return ignoreMissingLocations;
	}

	public void setIgnoreMissingLocations(boolean ignoreMissingLocations) {
		this.ignoreMissingLocations = ignoreMissingLocations;
	}

}
