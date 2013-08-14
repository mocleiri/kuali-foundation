package org.kuali.common.util.spring.env;

public interface EnvironmentService {

	<T> T getProperty(EnvContext<T> context);

	String getString(String key);

	String getString(String key, String defaultValue);

	Boolean getBoolean(String key);

	Boolean getBoolean(String key, Boolean defaultValue);

	<T> Class<T> getClass(String key, Class<T> type);

}
