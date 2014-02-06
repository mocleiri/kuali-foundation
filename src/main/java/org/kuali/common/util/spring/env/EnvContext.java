package org.kuali.common.util.spring.env;

import java.io.File;

import org.kuali.common.util.Assert;

public final class EnvContext<T> {

	public EnvContext(String key, Class<T> type) {
		this(key, type, null);
	}

	public EnvContext(String key, Class<T> type, T defaultValue) {
		Assert.noNulls(type);
		Assert.noBlanks(key);
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

	public static <T> EnvContext<T> newCtx(String key, Class<T> type, T defaultValue) {
		return new EnvContext<T>(key, type, defaultValue);
	}

	public static EnvContext<String> newString(String key, String defaultValue) {
		return newCtx(key, String.class, defaultValue);
	}

	public static EnvContext<Boolean> newBoolean(String key, Boolean defaultValue) {
		return newCtx(key, Boolean.class, defaultValue);
	}

	public static EnvContext<Integer> newInteger(String key, Integer defaultValue) {
		return newCtx(key, Integer.class, defaultValue);
	}

	public static EnvContext<File> newFile(String key, File defaultValue) {
		return newCtx(key, File.class, defaultValue);
	}

}
