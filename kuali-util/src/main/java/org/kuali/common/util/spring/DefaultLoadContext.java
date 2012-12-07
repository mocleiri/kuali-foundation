package org.kuali.common.util.spring;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultLoadContext implements LoadContext {

	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	GlobalPropertiesMode globalPropertiesMode = Constants.DEFAULT_GLOBAL_PROPERTIES_MODE;
	String encoding;
	String contextLocation;
	Properties properties;
	File workingDir;
	boolean filterContext;
	List<String> filterIncludes;
	List<String> filterExcludes;

	@Override
    public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	@Override
    public GlobalPropertiesMode getGlobalPropertiesMode() {
		return globalPropertiesMode;
	}

	public void setGlobalPropertiesMode(GlobalPropertiesMode globalPropertiesMode) {
		this.globalPropertiesMode = globalPropertiesMode;
	}

	@Override
    public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
    public String getContextLocation() {
		return contextLocation;
	}

	public void setContextLocation(String contextLocation) {
		this.contextLocation = contextLocation;
	}

	@Override
    public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
    public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	@Override
    public boolean isFilterContext() {
		return filterContext;
	}

	public void setFilterContext(boolean filterContext) {
		this.filterContext = filterContext;
	}

	@Override
    public List<String> getFilterIncludes() {
		return filterIncludes;
	}

	public void setFilterIncludes(List<String> filterIncludes) {
		this.filterIncludes = filterIncludes;
	}

	@Override
    public List<String> getFilterExcludes() {
		return filterExcludes;
	}

	public void setFilterExcludes(List<String> filterExcludes) {
		this.filterExcludes = filterExcludes;
	}

}
