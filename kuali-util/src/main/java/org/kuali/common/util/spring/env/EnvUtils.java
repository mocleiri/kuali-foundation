package org.kuali.common.util.spring.env;

import org.kuali.common.util.Assert;

public class EnvUtils {

	private static final String ENV_PREFIX = "env";

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
				sb.append('_');
			} else if (isUpperCase(c) && isLowerCase(prevChar)) {
				sb.append('_');
				sb.append(c);
			} else {
				sb.append(c);
			}
			prevChar = c;
		}
		return ENV_PREFIX + "." + sb.toString().toUpperCase();
	}

	protected static boolean isUpperCase(char c) {
		return c >= 'A' && c <= 'Z';
	}

	protected static boolean isLowerCase(char c) {
		return c >= 'a' && c <= 'z';
	}

}
