package org.kuali.common.util.spring.env;

import org.kuali.common.util.serviceloader.ServiceLoaderUtils;
import org.springframework.core.env.Environment;

public class Environments {

	private static Environment instance;

	public synchronized static Environment getDefaultEnvironment() {
		if (instance == null) {
			instance = ServiceLoaderUtils.getFirst(Environment.class);
		}
		return instance;
	}

}
