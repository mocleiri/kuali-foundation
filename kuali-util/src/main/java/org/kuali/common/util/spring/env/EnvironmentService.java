package org.kuali.common.util.spring.env;

public interface EnvironmentService {

	<T> T getProperty(EnvironmentContext<T> context);

	String getString(String key);

	String getString(String key, String defaultValue);

	Boolean getBoolean(String key);

	Boolean getBoolean(String key, Boolean defaultValue);

}
