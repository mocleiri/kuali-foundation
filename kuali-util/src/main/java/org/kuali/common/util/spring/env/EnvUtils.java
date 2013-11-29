package org.kuali.common.util.spring.env;

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.springframework.core.env.Environment;

public class EnvUtils {

	private static final String ENV_PREFIX = "env";

	public static Environment getDefaultEnvironment() {
		Properties global = PropertyUtils.getGlobalProperties();
		return new PropertiesEnvironment(global);
	}

	/**
	 * <pre>
	 *  foo.bar    -> env.FOO_BAR
	 *  foo.barBaz -> env.FOO_BAR_BAZ
	 * </pre>
	 */
	public static String getEnvironmentVariableKey(String key) {
		Assert.noBlanks(key);
		char[] chars = key.toCharArray();
		StringBuilder sb = new StringBuilder();
		char prevChar = 0;
		for (char c : chars) {
			if (c == '.') {
				// Convert dots into dashes
				sb.append('_');
			} else if (isUpperCase(c) && isLowerCase(prevChar)) {
				// Insert an underscore wherever there is a transition from a lower case char to an upper case char
				sb.append('_');
				sb.append(c);
			} else {
				// Just append the char
				sb.append(c);
			}
			// Keep track of the previous char
			prevChar = c;
		}
		// Add a prefix, change to upper case and return
		return ENV_PREFIX + "." + sb.toString().toUpperCase();
	}

	protected static boolean isUpperCase(char c) {
		return c >= 'A' && c <= 'Z';
	}

	protected static boolean isLowerCase(char c) {
		return c >= 'a' && c <= 'z';
	}

}
