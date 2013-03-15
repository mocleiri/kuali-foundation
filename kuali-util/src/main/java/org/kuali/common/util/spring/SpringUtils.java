package org.kuali.common.util.spring;

import org.springframework.core.env.Environment;

public class SpringUtils {

	/**
	 * Get a fully resolved property value from the environment. If the property is not found or contains unresolvable placeholders an exception is thrown. This calls
	 * <code>getRequiredProperty</code> and <code>resolveRequiredPlaceholders</code> on <code>env</code>.
	 */
	public static String getProperty(Environment env, String key) {
		String value = env.getRequiredProperty(key);
		return env.resolveRequiredPlaceholders(value);
	}

}
