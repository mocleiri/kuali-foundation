package org.kuali.common.util.spring.env;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.springframework.core.env.Environment;

public class DefaultEnvironmentService implements EnvironmentService {

	public static final boolean DEFAULT_CHECK_ENVIRONMENT_VARIABLES = false;
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
	public <T> T getProperty(EnvironmentContext<T> context) {
		Assert.noNulls(context);
		T springValue = getSpringValue(context.getKey(), context.getType());
		T returnValue = (springValue == null) ? context.getDefaultValue() : springValue;
		ModeUtils.validate(missingPropertyMode, getMissingPropertyMessage(context));
		return returnValue;
	}

	protected String getMissingPropertyMessage(EnvironmentContext<?> context) {
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
		String text = getProperty(EnvironmentContext.newString(key, defaultValue));
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
		return getProperty(EnvironmentContext.newBoolean(key, defaultValue));
	}

	/**
	 * <pre>
	 *  foo.bar -> env.FOO_BAR
	 * </pre>
	 */
	protected String getEnvironmentVariableKey(String key) {
		return ENV_PREFIX + "." + StringUtils.upperCase(StringUtils.replace(key, ".", "_"));
	}

}
