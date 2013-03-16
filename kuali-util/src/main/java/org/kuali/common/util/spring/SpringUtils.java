package org.kuali.common.util.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

public class SpringUtils {

	private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

	/**
	 * Get a fully resolved property value from the environment. If the property is not found or contains unresolvable placeholders an exception is thrown.
	 */
	public static String getProperty(Environment env, String key) {
		String value = env.getRequiredProperty(key);
		return env.resolveRequiredPlaceholders(value);
	}

	/**
	 * Examine <code>ConfigurableEnvironment</code> for <code>PropertySource</code>'s that extend <code>EnumerablePropertySource</code> and aggregate them into a single
	 * <code>Properties</code> object
	 */
	public static Properties getAllEnumerableProperties(ConfigurableEnvironment env) {

		// Extract the list of PropertySources from the environment
		List<PropertySource<?>> sources = getPropertySources(env);

		// Convert the list of PropertySource's to a list of Properties objects
		List<Properties> propertiesList = getPropertiesList(sources);

		// Spring provides PropertySource objects ordered from highest priority to lowest priority
		// We reverse the order here so we can iterate though the list of Properties objects using
		// properties.putAll() as a simple "last one in wins" strategy to make sure the highest priority
		// property value always wins
		Collections.reverse(propertiesList);

		// Combine them into a single Properties object
		return PropertyUtils.combine(propertiesList);
	}

	/**
	 * Remove any existing property sources and add one property source backed by the properties passed in
	 */
	public static void reconfigurePropertySources(ConfigurableEnvironment env, String name, Properties properties) {
		// Remove all existing property sources
		removeAllPropertySources(env);
		
		// MutablePropertySources allow us to manipulate the list of property sources
		MutablePropertySources mps = env.getPropertySources();

		// Make sure there are no existing property sources
		Assert.isTrue(mps.size() == 0);
		
		// Create a property source backed by the properties object passed in
		PropertiesPropertySource pps = new PropertiesPropertySource(name, properties);
		
		// Add it to the environment
		mps.addFirst(pps);
	}

	/**
	 * Remove any existing property sources
	 */
	public static void removeAllPropertySources(ConfigurableEnvironment env) {
		MutablePropertySources mps = env.getPropertySources();
		List<PropertySource<?>> sources = getPropertySources(env);
		for (PropertySource<?> source : sources) {
			String name = source.getName();
			mps.remove(name);
		}
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
			logger.debug("Adding [{}]", source.getName());
			if (source instanceof EnumerablePropertySource) {
				EnumerablePropertySource<?> eps = (EnumerablePropertySource<?>) source;
				Properties sourceProperties = getProperties(eps);
				list.add(sourceProperties);
			} else {
				logger.warn("Unable to obtain properties from property source [{}] -> [{}]", source.getName(), source.getClass().getName());
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
			} else {
				logger.warn("Property [{}] is null", name);
			}
		}
		return properties;
	}

}
