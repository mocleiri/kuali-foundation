package org.kuali.common.util.env;

import java.io.File;

import com.google.common.base.Optional;

public interface EnvironmentService {

	boolean containsProperty(String key);

	<T> T getProperty(String key, Class<T> type, T provided);

	<T> T getProperty(String key, Class<T> type);

	<T> Optional<T> getOptionalProperty(String key, Class<T> type);

	<T> Optional<T> getOptionalProperty(String key, Class<T> type, Optional<T> provided);

	String getString(String key);

	String getString(String key, String provided);

	Optional<String> getOptionalString(String key, Optional<String> provided);

	Optional<String> getOptionalString(String key);

	boolean getBoolean(String key);

	boolean getBoolean(String key, boolean provided);

	Optional<Boolean> getOptionalBoolean(String key);

	Optional<Boolean> getOptionalBoolean(String key, Optional<Boolean> provided);

	File getFile(String key);

	File getFile(String key, File provided);

	Optional<File> getOptionalFile(String key);

	Optional<File> getOptionalFile(String key, Optional<File> provided);

	int getInteger(String key);

	int getInteger(String key, int provided);

	Optional<Integer> getOptionalInteger(String key);

	Optional<Integer> getOptionalInteger(String key, Optional<Integer> provided);

	long getLong(String key);

	long getLong(String key, long provided);

	Optional<Long> getOptionalLong(String key);

	Optional<Long> getOptionalLong(String key, Optional<Long> provided);

	<T> Class<T> getClass(String key, Class<T> type);

	<T> Class<T> getClass(String key, Class<T> type, Class<T> provided);

	<T> Optional<Class<T>> getOptionalClass(String key);

	<T> Optional<Class<T>> getOptionalClass(String key, Optional<Class<T>> provided);

}
