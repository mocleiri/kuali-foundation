package org.kuali.common.util.property;

import java.io.File;

public class PropertyStorageContext implements PropertyHandlingContext {

	// The file to store properties to
	File file;
	// The encoding to use when storing them (null means use JVM's default encoding)
	String encoding;
	// If true, sort the properties by key when storing
	boolean sort;
	// If not null, add this as a prefix to every key
	String prefix;
	// If not null, include this as a comment in the properties file
	String comment;

	// If set to ENVIRONMENT_VARIABLE, properties are stored in the format environment variables are usually declared as
	PropertyStyle style;

	String include;
	String exclude;
	boolean includeEnvironmentVariables;
	boolean includeSystemProperties;
	boolean resolvePlaceholders;
	String placeHolderPrefix;
	String placeHolderSuffix;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public PropertyStyle getStyle() {
		return style;
	}

	public void setStyle(PropertyStyle style) {
		this.style = style;
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

}
