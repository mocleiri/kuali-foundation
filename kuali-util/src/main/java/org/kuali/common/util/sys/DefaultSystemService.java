package org.kuali.common.util.sys;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSystemService implements SystemService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSystemService.class);

	@Override
	public Properties getProperties() {
		return System.getProperties();
	}

	@Override
	public Properties getEnvironment() {
		return PropertyUtils.getEnvAsProperties();
	}

	@Override
	public Properties getGlobalProperties() {
		return PropertyUtils.getGlobalProperties();
	}

	@Override
	public void removeSystemProperty(String key) {
		if (System.getProperty(key) != null) {
			logger.debug("Removing system property [{}]", key);
			System.getProperties().remove(key);
		}
	}

}
