package org.kuali.common.util.builder;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.google.common.base.Optional;

public class BuilderUtils {

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
	 * Return a decrypted value by searching the environment under both keys. Default to the provided value if nothing is found
	 */
	public static String getValue(BuilderContext ctx, String key1, String key2, Optional<String> provided) {
		String value = getValue(ctx.getEnv(), key1, key2, provided);
		return EncUtils.decrypt(ctx.getEnc(), value);
	}

	protected static String getValue(EnvironmentService env, String key1, String key2, Optional<String> provided) {
		Optional<String> value1 = SpringUtils.getString(env, key1, provided);
		Optional<String> value2 = SpringUtils.getOptionalString(env, key2);
		if (value1.isPresent()) {
			return value1.get();
		} else if (value2.isPresent()) {
			return value2.get();
		} else {
			return provided.get();
		}
	}
}
