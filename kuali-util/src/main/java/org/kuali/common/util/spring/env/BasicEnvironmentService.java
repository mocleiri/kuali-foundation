package org.kuali.common.util.spring.env;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.spring.env.model.EnvironmentServiceContext;

/**
 * <p>
 * By default, an exception is thrown if a value cannot be located (unless a default value has been supplied).
 * </p>
 * 
 * <p>
 * By default, an exception is thrown if any placeholders cannot be resolved in any string values.
 * </p>
 * 
 * <p>
 * By default, string values are resolved before being returned
 * </p>
 * 
 * <p>
 * By default, environment variables are automatically checked if a normal property value cannot be found.
 * 
 * For example, given the key <code>db.vendor</code> the service will also automatically check <code>env.DB_VENDOR</code>
 * </p>
 */
public final class BasicEnvironmentService implements EnvironmentService {

	private final EnvironmentServiceContext context;

	public BasicEnvironmentService() {
		this(new EnvironmentServiceContext.Builder().build());
	}

	public BasicEnvironmentService(EnvironmentServiceContext context) {
		Assert.noNulls(context);
		this.context = context;
	}

	@Override
	public boolean containsProperty(String key) {
		Assert.noBlanks(key);
		return context.getEnv().containsProperty(key);
	}

	@Override
	public <T> T getProperty(EnvContext<T> context) {

		// If context is null, we have issues
		Assert.noNulls(context);

		// Extract a value from Spring's Environment abstraction
		T springValue = getSpringValue(context.getKey(), context.getType());

		// If that value is null, use whatever default value they gave us (this might also be null)
		T returnValue = (springValue == null) ? context.getDefaultValue() : springValue;

		// If we could not locate a value, we may need to error out
		if (returnValue == null) {
			ModeUtils.validate(this.context.getMissingPropertyMode(), getMissingPropertyMessage(context.getKey()));
		}

		// Return the value we've located
		return returnValue;
	}

	protected String getMissingPropertyMessage(String key) {
		if (context.isCheckEnvironmentVariables()) {
			String envKey = EnvUtils.getEnvironmentVariableKey(key);
			return "No value for [" + key + "] or [" + envKey + "]";
		} else {
			return "No value for [" + key + "]";
		}
	}

	protected <T> T getSpringValue(String key, Class<T> type) {
		T value = context.getEnv().getProperty(key, type);
		if (value == null && context.isCheckEnvironmentVariables()) {
			String envKey = EnvUtils.getEnvironmentVariableKey(key);
			return context.getEnv().getProperty(envKey, type);
		} else {
			return value;
		}
	}

	protected <T> Class<T> getSpringValueAsClass(String key, Class<T> type) {
		Class<T> value = context.getEnv().getPropertyAsClass(key, type);
		if (value == null && context.isCheckEnvironmentVariables()) {
			String envKey = EnvUtils.getEnvironmentVariableKey(key);
			return context.getEnv().getPropertyAsClass(envKey, type);
		} else {
			return value;
		}
	}

	@Override
	public <T> Class<T> getClass(String key, Class<T> type) {
		return getClass(key, type, null);
	}

	@Override
	public <T> Class<T> getClass(String key, Class<T> type, Class<T> defaultValue) {
		Class<T> springValue = getSpringValueAsClass(key, type);
		Class<T> returnValue = (springValue == null) ? defaultValue : springValue;

		// If we could not locate a value, we may need to error out
		if (returnValue == null) {
			ModeUtils.validate(context.getMissingPropertyMode(), getMissingPropertyMessage(key));
		}

		// Return what we've got
		return returnValue;
	}

	@Override
	public String getString(String key) {
		return getString(key, null);
	}

	@Override
	public String getString(String key, String defaultValue) {
		String string = getProperty(EnvContext.newString(key, defaultValue));
		if (context.isResolveStrings()) {
			return context.getEnv().resolveRequiredPlaceholders(string);
		} else {
			return string;
		}
	}

	@Override
	public Boolean getBoolean(String key) {
		return getBoolean(key, null);
	}

	@Override
	public Boolean getBoolean(String key, Boolean defaultValue) {
		return getProperty(EnvContext.newBoolean(key, defaultValue));
	}

	@Override
	public File getFile(String key) {
		return getFile(key, null);
	}

	@Override
	public File getFile(String key, File defaultValue) {
		return getProperty(EnvContext.newFile(key, defaultValue));
	}

	@Override
	public Integer getInteger(String key, Integer defaultValue) {
		return getProperty(EnvContext.newInteger(key, defaultValue));
	}

	@Override
	public Integer getInteger(String key) {
		return getInteger(key, null);
	}

	public EnvironmentServiceContext getContext() {
		return context;
	}

}
