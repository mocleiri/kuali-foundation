package org.kuali.common.util.property;

import java.util.List;

import org.kuali.common.util.EncryptionStrength;
import org.kuali.common.util.property.modifier.PropertyModifier;
import org.springframework.util.PropertyPlaceholderHelper;

public interface DefaultPropertyContextInterface {

	String getEncoding();

	void setEncoding(String encoding);

	List<String> getIncludes();

	void setIncludes(List<String> includes);

	List<String> getExcludes();

	void setExcludes(List<String> excludes);

	boolean isIncludeEnvironmentVariables();

	void setIncludeEnvironmentVariables(boolean includeEnvironmentVariables);

	boolean isIncludeSystemProperties();

	void setIncludeSystemProperties(boolean includeSystemProperties);

	boolean isResolvePlaceholders();

	void setResolvePlaceholders(boolean resolvePlaceholders);

	String getPrefix();

	void setPrefix(String prefix);

	PropertyStyle getStyle();

	void setStyle(PropertyStyle style);

	PropertyPlaceholderHelper getHelper();

	void setHelper(PropertyPlaceholderHelper helper);

	PropertyEncryptionMode getEncryptionMode();

	void setEncryptionMode(PropertyEncryptionMode encryptionMode);

	EncryptionStrength getEncryptionStrength();

	void setEncryptionStrength(EncryptionStrength encryptionStrength);

	String getEncryptionPassword();

	void setEncryptionPassword(String encryptionPassword);

	List<PropertyModifier> getModifiers();

	void setModifiers(List<PropertyModifier> modifiers);

	List<String> getPathProperties();

	void setPathProperties(List<String> pathProperties);

}