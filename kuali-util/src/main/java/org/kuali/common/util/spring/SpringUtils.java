package org.kuali.common.util.spring;

import java.util.Iterator;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

public class SpringUtils {

	private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

	/**
	 * Get a fully resolved property value from the environment. If the property is not found or contains unresolvable placeholders an exception is thrown. This calls
	 * <code>getRequiredProperty</code> and <code>resolveRequiredPlaceholders</code> on <code>env</code>.
	 */
	public static String getProperty(Environment env, String key) {
		String value = env.getRequiredProperty(key);
		return env.resolveRequiredPlaceholders(value);
	}

	public static Properties getAllProperties(ConfigurableEnvironment env) {
		MutablePropertySources mps = env.getPropertySources();
		Properties properties = new Properties();
		Iterator<PropertySource<?>> itr = mps.iterator();
		while (itr.hasNext()) {
			PropertySource<?> source = itr.next();
			logger.info("Adding [{}]", source.getName());
			if (source instanceof EnumerablePropertySource) {
				EnumerablePropertySource<?> eps = (EnumerablePropertySource<?>) source;
				Properties sourceProperties = getProperties(eps);
				properties.putAll(sourceProperties);
			} else {
				logger.info("Unable to obtain properties from property source [{}] -> [{}]", source.getName(), source.getClass().getName());
			}
		}
		return properties;
	}

	public static Properties getProperties(EnumerablePropertySource<?> source) {
		Properties properties = new Properties();
		String[] names = source.getPropertyNames();
		for (String name : names) {
			Object object = source.getProperty(name);
			if (object != null) {
				String value = object.toString();
				properties.setProperty(name, value);
			}
		}
		return properties;
	}

}
