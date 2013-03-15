package org.kuali.common.util.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
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

		// Extract the list of PropertySources from the environment
		List<PropertySource<?>> sources = getPropertySources(env);

		// The Spring iterator provides property sources ordered from highest priority to lowest priority
		// We reverse that order here so we can iterate though the list of Properties objects and use
		// properties.putAll() as an easy way to make sure the highest priority property value always wins
		Collections.reverse(sources);

		// Convert the list of PropertySource's to a list of Properties objects
		List<Properties> list = getPropertiesList(sources);

		// Combine them into a single Properties object
		return PropertyUtils.combine(list);
	}

	public static List<PropertySource<?>> getPropertySources(ConfigurableEnvironment env) {
		MutablePropertySources mps = env.getPropertySources();
		List<PropertySource<?>> sources = new ArrayList<PropertySource<?>>();
		Iterator<PropertySource<?>> itr = mps.iterator();
		while (itr.hasNext()) {
			PropertySource<?> source = itr.next();
			sources.add(source);
		}
		return sources;
	}

	public static List<Properties> getPropertiesList(List<PropertySource<?>> sources) {
		List<Properties> list = new ArrayList<Properties>();
		// Extract property values from the sources and place them in a Properties object
		for (PropertySource<?> source : sources) {
			logger.info("Adding [{}]", source.getName());
			if (source instanceof EnumerablePropertySource) {
				EnumerablePropertySource<?> eps = (EnumerablePropertySource<?>) source;
				Properties sourceProperties = getProperties(eps);
				list.add(sourceProperties);
			} else {
				logger.info("Unable to obtain properties from property source [{}] -> [{}]", source.getName(), source.getClass().getName());
			}
		}
		return list;
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
