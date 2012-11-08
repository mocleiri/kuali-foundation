package org.kuali.common.util.property;

import java.util.List;

public interface PropertyLoadingContext {

	List<String> getLocations();

	String getEncoding();

	String getInclude();

	String getExclude();

	boolean isIncludeEnvironmentVariables();

	boolean isIncludeSystemProperties();

	boolean isResolvePlaceholders();

	boolean isIgnoreMissingLocations();

	String getPlaceHolderPrefix();

	String getPlaceHolderSuffix();

}