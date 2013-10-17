package org.kuali.common.util.spring.env;

import java.io.File;

public interface EnvironmentService {

	boolean containsProperty(String key);

	<T> T getProperty(EnvContext<T> context);

	String getString(String key);

	String getString(String key, String defaultValue);

	Boolean getBoolean(String key);

	Boolean getBoolean(String key, Boolean defaultValue);

	File getFile(String key);

	File getFile(String key, File defaultValue);

	Integer getInteger(String key);

	Integer getInteger(String key, Integer defaultValue);

	<T> Class<T> getClass(String key, Class<T> type);

	<T> Class<T> getClass(String key, Class<T> type, Class<T> defaultValue);

}
