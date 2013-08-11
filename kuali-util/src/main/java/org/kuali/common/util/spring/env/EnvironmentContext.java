package org.kuali.common.util.spring.env;

import org.kuali.common.util.Assert;

public final class EnvironmentContext<T> {

	public EnvironmentContext(String key, Class<T> type) {
		this(key, type, null);
	}

	public EnvironmentContext(String key, Class<T> type, T defaultValue) {
		Assert.noNulls(key, type);
		this.key = key;
		this.type = type;
		this.defaultValue = defaultValue;
	}

	private final String key;
	private final Class<T> type;
	private final T defaultValue;

	public String getKey() {
		return key;
	}

	public Class<T> getType() {
		return type;
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public static <T> EnvironmentContext<T> newCtx(String key, Class<T> type, T defaultValue) {
		return new EnvironmentContext<T>(key, type, defaultValue);
	}

	public static EnvironmentContext<String> newString(String key, String defaultValue) {
		return newCtx(key, String.class, defaultValue);
	}

	public static EnvironmentContext<Boolean> newBoolean(String key, Boolean defaultValue) {
		return newCtx(key, Boolean.class, defaultValue);
	}

}
