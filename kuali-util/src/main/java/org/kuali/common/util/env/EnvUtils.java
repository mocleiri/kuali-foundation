package org.kuali.common.util.env;

import java.util.Properties;

import org.kuali.common.util.Ascii;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.cache.Cache;
import org.kuali.common.util.cache.SimpleCache;
import org.kuali.common.util.env.spring.PropertiesEnvironment;
import org.springframework.core.env.Environment;

import com.google.common.base.Optional;

public class EnvUtils {

	public static final Optional<EnvironmentService> ABSENT = Optional.absent();

	private static final String ENV_PREFIX = "env";
	private static final Cache<String, String> ENV_KEYS = new SimpleCache<String, String>();

	/**
	 * If the environment contains a string under this key, convert it into a long signifying bytes
	 * 
	 * <pre>
	 *   file.size=10m   (file that is 10 megabytes)
	 *   disk.size=100g  (disk that is 100 gigabytes)
	 * </pre>
	 */
	public static long getBytes(EnvironmentService env, String key, String provided) {
		Assert.noBlanks(key, provided);
		long bytes = FormatUtils.getBytes(provided);
		return getBytes(env, key, bytes);
	}

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
		Assert.notBlank(key);
		String envKey = ENV_KEYS.get(key);
		if (envKey == null) {
			envKey = toEnvironmentVariableKey(key);
			ENV_KEYS.put(key, envKey);
		}
		return envKey;
	}

	public static String toEnvironmentVariableKey(String key) {
		Assert.notBlank(key);
		int length = key.length();
		StringBuilder sb = new StringBuilder();
		sb.append(ENV_PREFIX); // Append the prefix indicating an environment property
		sb.append("."); // Append the dot separator
		char prevChar = 0;
		char c;
		for (int index = 0; index < length; index++) {
			c = key.charAt(index);
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
		// Change to upper case and return
		return sb.toString().toUpperCase();
	}

	public static void clearEnvKeysCache() {
		ENV_KEYS.clear();
	}

}
