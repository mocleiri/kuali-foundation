package org.kuali.common.util.spring.env;

public interface EnvironmentService {

	<T> T getProperty(EnvContext<T> context);

	String getString(String key);

	String getString(String key, String defaultValue);

	Boolean getBoolean(String key);

	Boolean getBoolean(String key, Boolean defaultValue);

	Integer getInteger(String key);

	Integer getInteger(String key, Integer defaultValue);

	<T> Class<? extends T> getClass(String key, Class<? extends T> type);

	<T> Class<? extends T> getClass(String key, Class<? extends T> type, Class<? extends T> defaultValue);

}
