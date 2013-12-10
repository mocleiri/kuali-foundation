package org.kuali.common.util.env;

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

}
