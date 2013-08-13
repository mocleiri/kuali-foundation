package org.kuali.common.util.spring.env;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.springframework.core.env.Environment;

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
	public <T> T getProperty(EnvContext<T> context) {

		// If this is null, we have issues
		Assert.noNulls(context);

		// Extract a value from Spring's Environment abstraction
		T springValue = getSpringValue(context.getKey(), context.getType());

		// If that value is null, use whatever default value they gave us (this might also be null)
		T returnValue = (springValue == null) ? context.getDefaultValue() : springValue;

		// If we could not locate a value, we may need to error out
		if (returnValue == null) {
			ModeUtils.validate(missingPropertyMode, getMissingPropertyMessage(context));
		}

		// Return the value we've located
		return returnValue;
	}

	protected String getMissingPropertyMessage(EnvContext<?> context) {
		if (checkEnvironmentVariables) {
			String envKey = getEnvironmentVariableKey(context.getKey());
			return "No value for [" + context.getKey() + "] or [" + envKey + "]";
		} else {
			return "No value for [" + context.getKey() + "]";
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

	@Override
	public String getString(String key) {
		return getString(key, null);
	}

	@Override
	public String getString(String key, String defaultValue) {
		String text = getProperty(EnvContext.newString(key, defaultValue));
		if (resolveStrings) {
			return env.resolveRequiredPlaceholders(text);
		} else {
			return text;
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

	/**
	 * <pre>
	 *  foo.bar -> env.FOO_BAR
	 * </pre>
	 */
	protected String getEnvironmentVariableKey(String key) {
		return ENV_PREFIX + "." + StringUtils.upperCase(StringUtils.replace(key, ".", "_"));
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
