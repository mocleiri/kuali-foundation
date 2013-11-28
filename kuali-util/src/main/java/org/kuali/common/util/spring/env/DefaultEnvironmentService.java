package org.kuali.common.util.spring.env;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.springframework.core.env.Environment;

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
 * By default, environment variables are automatically checked if a normal property value cannot be found.
 * 
 * For example, given the key <code>db.vendor</code> the service will also automatically check <code>env.DB_VENDOR</code>
 * </p>
 */
public class DefaultEnvironmentService implements EnvironmentService {

	public static final boolean DEFAULT_CHECK_ENVIRONMENT_VARIABLES = true;
	public static final boolean DEFAULT_RESOLVE_STRINGS = true;
	public static final Mode DEFAULT_MISSING_PROPERTY_MODE = Mode.ERROR;
	public static final String ENV_PREFIX = "env";

	private final boolean checkEnvironmentVariables;
	private final boolean resolveStrings;
	private final Environment env;
	private final Mode missingPropertyMode;

	public DefaultEnvironmentService(Environment env) {
		this(env, DEFAULT_CHECK_ENVIRONMENT_VARIABLES, DEFAULT_RESOLVE_STRINGS, DEFAULT_MISSING_PROPERTY_MODE);
	}

	public DefaultEnvironmentService(Environment env, boolean checkEnvironmentVariables) {
		this(env, checkEnvironmentVariables, DEFAULT_RESOLVE_STRINGS, DEFAULT_MISSING_PROPERTY_MODE);
	}

	public DefaultEnvironmentService(Environment env, boolean checkEnvironmentVariables, boolean resolveStrings, Mode missingPropertyMode) {
		Assert.noNulls(env, missingPropertyMode);
		this.env = env;
		this.missingPropertyMode = missingPropertyMode;
		this.checkEnvironmentVariables = checkEnvironmentVariables;
		this.resolveStrings = resolveStrings;
	}

	@Override
	public boolean containsProperty(String key) {
		Assert.noBlanks(key);
		return env.containsProperty(key);
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
			ModeUtils.validate(missingPropertyMode, getMissingPropertyMessage(context.getKey()));
		}

		// Return the value we've located
		return returnValue;
	}

	protected String getMissingPropertyMessage(String key) {
		if (checkEnvironmentVariables) {
			String envKey = getEnvironmentVariableKey(key);
			return "No value for [" + key + "] or [" + envKey + "]";
		} else {
			return "No value for [" + key + "]";
		}
	}

	protected <T> T getSpringValue(String key, Class<T> type) {
		T value = env.getProperty(key, type);
		if (value == null && checkEnvironmentVariables) {
			String envKey = getEnvironmentVariableKey(key);
			return env.getProperty(envKey, type);
		} else {
			return value;
		}
	}

	protected <T> Class<T> getSpringValueAsClass(String key, Class<T> type) {
		Class<T> value = env.getPropertyAsClass(key, type);
		if (value == null && checkEnvironmentVariables) {
			String envKey = getEnvironmentVariableKey(key);
			return env.getPropertyAsClass(envKey, type);
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
			ModeUtils.validate(missingPropertyMode, getMissingPropertyMessage(key));
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
		if (resolveStrings) {
			return env.resolveRequiredPlaceholders(string);
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

	/**
	 * <pre>
	 *  foo.bar    -> env.FOO_BAR
	 *  foo.barBaz -> env.FOO_BAR_BAZ
	 * </pre>
	 */
	protected String getEnvironmentVariableKey(String key) {
		Assert.noBlanks(key);
		char[] chars = key.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			if (c == '.') {
				sb.append('_');
			} else if (isUpperCase(c)) {
				sb.append('_');
				sb.append(c);
			} else {
				sb.append(c);
			}
		}
		return ENV_PREFIX + "." + sb.toString();
	}

	protected boolean isUpperCase(char c) {
		return c >= 'A' && c <= 'Z';
	}

	public boolean isCheckEnvironmentVariables() {
		return checkEnvironmentVariables;
	}

	public boolean isResolveStrings() {
		return resolveStrings;
	}

	public Environment getEnv() {
		return env;
	}

	public Mode getMissingPropertyMode() {
		return missingPropertyMode;
	}

}
