package org.kuali.common.util.property;

public interface PropertyHandlingContext {

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

	PropertyEncryptor getEncryptor();

	boolean isEncrypt();

	boolean isDecrypt();

}