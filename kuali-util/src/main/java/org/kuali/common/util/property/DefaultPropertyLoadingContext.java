package org.kuali.common.util.property;

import java.util.List;

import org.kuali.common.util.PropertyUtils;

public class DefaultPropertyLoadingContext implements PropertyLoadingContext {

	List<String> locations;
	String encoding;
	String include;
	String exclude;
	boolean includeEnvironmentVariables;
	boolean includeSystemProperties;
	boolean resolvePlaceholders;
	boolean ignoreMissingLocations;
	String placeHolderPrefix = PropertyUtils.DEFAULT_PLACEHOLDER_PREFIX;
	String placeHolderSuffix = PropertyUtils.DEFAULT_PLACEHOLDER_SUFFIX;
	String prefix;
	PropertyStyle style = PropertyStyle.NORMAL;

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

	@Override
	public String getPlaceHolderPrefix() {
		return placeHolderPrefix;
	}

	public void setPlaceHolderPrefix(String placeHolderPrefix) {
		this.placeHolderPrefix = placeHolderPrefix;
	}

	@Override
	public String getPlaceHolderSuffix() {
		return placeHolderSuffix;
	}

	public void setPlaceHolderSuffix(String placeHolderSuffix) {
		this.placeHolderSuffix = placeHolderSuffix;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public PropertyStyle getStyle() {
		return style;
	}

	public void setStyle(PropertyStyle style) {
		this.style = style;
	}

}
