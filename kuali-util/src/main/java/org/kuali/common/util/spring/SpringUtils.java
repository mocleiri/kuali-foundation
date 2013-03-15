package org.kuali.common.util.spring;

import org.springframework.core.env.Environment;

public class SpringUtils {

	/**
	 * Get a fully resolved, required property from the environment by calling <code>getRequiredProperty</code> and <code>resolveRequiredPlaceholders</code> on <code>env</code>.
	 */
	public static String getProperty(Environment env, String key) {
		String value = env.getRequiredProperty(key);
		return env.resolveRequiredPlaceholders(value);
	}

}
