package org.kuali.common.util.sys;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.ImmutableProperties;
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
		return new ImmutableProperties(PropertyUtils.getEnvAsProperties());
	}

	@Override
	public Properties getGlobalProperties() {
		return PropertyUtils.getGlobalProperties();
	}

	@Override
	public String getGlobalProperty(String key) {
		return getGlobalProperty(key, null);
	}

	@Override
	public String getGlobalProperty(String key, String defaultValue) {
		return PropertyUtils.getGlobalProperty(key, defaultValue);
	}

	@Override
	public void removeSystemProperty(String key) {
		if (System.getProperty(key) != null) {
			logger.debug("Removing system property [{}]", key);
			System.getProperties().remove(key);
		}
	}

}
