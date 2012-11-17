package org.kuali.common.util.property;

import java.util.List;

public interface PropertyContext {

	String getEncoding();

	List<String> getIncludes();

	List<String> getExcludes();

	boolean isIncludeEnvironmentVariables();

	boolean isIncludeSystemProperties();

	boolean isResolvePlaceholders();

	String getPlaceHolderPrefix();

	String getPlaceHolderSuffix();

	String getPrefix();

	PropertyStyle getStyle();

}