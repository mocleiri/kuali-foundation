package org.kuali.common.util.env;

import java.io.File;

import com.google.common.base.Optional;

public interface EnvironmentService {

	boolean containsProperty(String key);

	<T> T getProperty(String key, Class<T> type);

	<T> T getProperty(String key, Class<T> type, T provided);

	<T> Optional<T> getOptionalProperty(String key, Class<T> type);

	<T> Optional<T> getOptionalProperty(String key, Class<T> type, Optional<T> provided);

	<T> Class<T> getPropertyAsClass(String key, Class<T> type);

	<T> Class<T> getPropertyAsClass(String key, Class<T> type, Class<T> provided);

	<T> Optional<Class<T>> getOptionalPropertyAsClass(String key);

	<T> Optional<Class<T>> getOptionalPropertyAsClass(String key, Optional<Class<T>> provided);

	String getString(String key);

	String getString(String key, String provided);

	Optional<String> getOptionalString(String key);

	Optional<String> getOptionalString(String key, Optional<String> provided);

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

	double getDouble(String key);

	double getDouble(String key, double provided);

	Optional<Double> getOptionalDouble(String key);

	Optional<Double> getOptionalDouble(String key, Optional<Double> provided);

	long getLong(String key);

	long getLong(String key, long provided);

	Optional<Long> getOptionalLong(String key);

	Optional<Long> getOptionalLong(String key, Optional<Long> provided);

}
