package org.kuali.common.util.property;

import org.kuali.common.util.service.PropertyService;

public class DefaultPropertyHandlingContext implements PropertyContext {

	String encoding;
	String include;
	String exclude;
	boolean includeEnvironmentVariables;
	boolean includeSystemProperties;
	boolean resolvePlaceholders;
	String placeHolderPrefix = PropertyService.DEFAULT_PLACEHOLDER_PREFIX;
	String placeHolderSuffix = PropertyService.DEFAULT_PLACEHOLDER_SUFFIX;
	String prefix;
	PropertyStyle style = PropertyStyle.NORMAL;

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
