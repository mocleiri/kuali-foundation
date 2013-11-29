package org.kuali.common.util.builder;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

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

}
