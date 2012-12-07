package org.kuali.common.util.spring;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.property.GlobalPropertiesMode;
import org.springframework.util.PropertyPlaceholderHelper;

public interface LoadContext {

	String getEncoding();

	String getContextLocation();

	Properties getProperties();

	File getWorkingDir();

	boolean isFilterContext();

	List<String> getFilterIncludes();

	List<String> getFilterExcludes();

	PropertyPlaceholderHelper getHelper();

	GlobalPropertiesMode getGlobalPropertiesMode();
}
