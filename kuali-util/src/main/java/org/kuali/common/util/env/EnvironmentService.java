package org.kuali.common.util.env;

import java.io.File;

import com.google.common.base.Optional;

public interface EnvironmentService {

	boolean containsProperty(String key);

	<T> T getProperty(String key, Class<T> type, T provided);

	<T> T getProperty(String key, Class<T> type);

	<T> Optional<T> getOptional(String key, Class<T> type, Optional<T> provided);

	<T> Optional<T> getOptional(String key, Class<T> type);

	String getString(String key);

	String getString(String key, String provided);

	boolean getBoolean(String key);

	boolean getBoolean(String key, boolean provided);

	File getFile(String key);

	File getFile(String key, File provided);

	int getInteger(String key);

	int getInteger(String key, int provided);

	long getLong(String key);

	long getLong(String key, long provided);

	<T> Class<T> getClass(String key, Class<T> type);

	<T> Class<T> getClass(String key, Class<T> type, Class<T> provided);

}
