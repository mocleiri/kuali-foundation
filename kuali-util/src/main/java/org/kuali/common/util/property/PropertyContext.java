package org.kuali.common.util.property;

public interface PropertyContext {

	String getEncoding();

	String getInclude();

	String getExclude();

	boolean isIncludeEnvironmentVariables();

	boolean isIncludeSystemProperties();

	boolean isResolvePlaceholders();

	String getPlaceHolderPrefix();

	String getPlaceHolderSuffix();

	String getPrefix();

	PropertyStyle getStyle();

}