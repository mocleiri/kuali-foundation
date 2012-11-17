package org.kuali.common.util.property;

import java.util.List;

import org.springframework.util.PropertyPlaceholderHelper;

public interface PropertyContext {

	String getEncoding();

	List<String> getIncludes();

	List<String> getExcludes();

	boolean isIncludeEnvironmentVariables();

	boolean isIncludeSystemProperties();

	boolean isResolvePlaceholders();

	String getPrefix();

	PropertyStyle getStyle();

	PropertyEncContext getEncryptionContext();

	PropertyPlaceholderHelper getHelper();

}