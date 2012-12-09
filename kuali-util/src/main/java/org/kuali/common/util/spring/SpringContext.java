package org.kuali.common.util.spring;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.property.GlobalPropertiesMode;
import org.springframework.util.PropertyPlaceholderHelper;

public interface SpringContext {

	PropertyPlaceholderHelper getHelper();

	GlobalPropertiesMode getGlobalPropertiesMode();

	String getEncoding();

	String getContextLocation();

	Properties getProperties();

	File getWorkingDir();

	boolean isFilterContext();

	List<String> getFilterIncludes();

	List<String> getFilterExcludes();

}