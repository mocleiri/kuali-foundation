package org.kuali.common.util.env;

import java.util.Properties;

import org.kuali.common.util.Ascii;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.env.spring.PropertiesEnvironment;
import org.springframework.core.env.Environment;

import com.google.common.base.Optional;

public class EnvUtils {

	public static final Optional<EnvironmentService> ABSENT = Optional.absent();

	private static final String ENV_PREFIX = "env";

	/**
	 * If the environment contains a string under this key, convert it into a long signifying bytes
	 * 
	 * <pre>
	 *   file.size=10m   (file that is 10 megabytes)
	 *   disk.size=100g  (disk that is 100 gigabytes)
	 * </pre>
	 */
	public static long getBytes(EnvironmentService env, String key, long provided) {
		if (env.containsProperty(key)) {
			String size = env.getString(key);
			long bytes = FormatUtils.getBytes(size);
			return bytes;
		} else {
			return provided;
		}
	}

	/**
	 * Return an environment that uses system properties / environment variables
	 */
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
			} else if (Ascii.isUpperCase(c) && Ascii.isLowerCase(prevChar)) {
				// Insert an underscore every time there is a transition from a lower case char to an upper case char
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

}
