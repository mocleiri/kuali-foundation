package org.kuali.common.util.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

public class SpringUtils {

	private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

	/**
	 * Converts a GAV into Spring's classpath style notation for the default project properties context.
	 * 
	 * <pre>
	 *  org.kuali.common:kuali-jdbc -> classpath:org/kuali/common/kuali-jdbc-properties-context.xml
	 * </pre>
	 */
	public static String getDefaultPropertyContextLocation(String gav) {
		Assert.hasText(gav, "gav has no text");
		Project p = ProjectUtils.getProject(gav);
		return "classpath:" + Str.getPath(p.getGroupId()) + "/" + p.getArtifactId() + "-properties-context.xml";
	}

	/**
	 * Make sure all of the locations actually exist
	 */
	public static void validateExists(List<String> locations) {
		StringBuilder sb = new StringBuilder();
		for (String location : locations) {
			if (!LocationUtils.exists(location)) {
				sb.append("Location [" + location + "] does not exist\n");
			}
		}
		if (sb.length() > 0) {
			throw new IllegalArgumentException(sb.toString());
		}
	}

	public static ConfigurableApplicationContext getContextWithPreRegisteredBeans(List<String> beanNames, List<Object> beans) {
		Assert.isTrue(beanNames.size() == beans.size());
		GenericXmlApplicationContext appContext = new GenericXmlApplicationContext();
		appContext.refresh();
		ConfigurableListableBeanFactory factory = appContext.getBeanFactory();
		for (int i = 0; i < beanNames.size(); i++) {
			String beanName = beanNames.get(i);
			Object bean = beans.get(i);
			logger.debug("Registering bean - [{}] -> [{}]", beanName, bean.getClass().getName());
			factory.registerSingleton(beanName, bean);
		}
		return appContext;
	}

	/**
	 * Null safe refresh for a context
	 */
	public static void refreshQuietly(ConfigurableApplicationContext context) {
		if (context != null) {
			context.refresh();
		}
	}

	/**
	 * Null safe close for a context
	 */
	public static void closeQuietly(ConfigurableApplicationContext context) {
		if (context != null) {
			context.close();
		}
	}

	public static ConfigurableApplicationContext getContextWithPreRegisteredBean(String beanName, Object bean) {
		return getContextWithPreRegisteredBeans(Arrays.asList(beanName), Arrays.asList(bean));
	}

	public static List<PropertySource<?>> getPropertySources(Class<?> annotatedClass) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(annotatedClass);
		return extractPropertySourcesAndClose(context);
	}

	public static List<PropertySource<?>> extractPropertySourcesAndClose(ConfigurableApplicationContext context) {
		// Extract PropertySources (if any)
		List<PropertySource<?>> sources = getPropertySources(context);

		// Close the context
		closeQuietly(context);

		// Return the list
		return sources;
	}

	/**
	 * Scan the XML Spring context for any beans that implement <code>PropertySource</code>
	 */
	public static List<PropertySource<?>> getPropertySources(String location) {
		ConfigurableApplicationContext context = new GenericXmlApplicationContext(location);
		return extractPropertySourcesAndClose(context);
	}

	/**
	 * This method returns a list of any PropertySource objects registered in the indicated context. They are sorted by property source name.
	 */
	public static List<PropertySource<?>> getPropertySources(ConfigurableApplicationContext context) {
		// Sort them by name
		return getPropertySources(context, new PropertySourceNameComparator());
	}

	public static <T> Map<String, T> getAllBeans(List<String> locations, Class<T> type) {
		String[] locationsArray = locations.toArray(new String[locations.size()]);
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext(locationsArray);
		Map<String, T> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(ctx, type);
		ctx.close();
		return map;
	}

	public static <T> Map<String, T> getAllBeans(String location, Class<T> type) {
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext(location);
		Map<String, T> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(ctx, type);
		ctx.close();
		return map;
	}

	public static <T> Map<String, T> getAllBeans(ConfigurableApplicationContext ctx, Class<T> type) {
		return BeanFactoryUtils.beansOfTypeIncludingAncestors(ctx, type);
	}

	/**
	 * This method returns a list of any PropertySource objects registered in the indicated context. The comparator is responsible for putting them in correct order.
	 */
	public static List<PropertySource<?>> getPropertySources(ConfigurableApplicationContext context, Comparator<PropertySource<?>> comparator) {
		// Extract all beans that implement the PropertySource interface
		@SuppressWarnings("rawtypes")
		Map<String, PropertySource> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, PropertySource.class);

		// Extract the PropertySource beans into a list
		List<PropertySource<?>> list = new ArrayList<PropertySource<?>>();
		for (PropertySource<?> source : map.values()) {
			list.add(source);
		}

		// Sort them using the provided comparator
		Collections.sort(list, comparator);

		// Return the list
		return list;
	}

	/**
	 * Null safe method for converting an untyped array of property sources into a list. Never returns null.
	 */
	public static List<PropertySource<?>> asList(PropertySource<?>... sources) {
		List<PropertySource<?>> list = new ArrayList<PropertySource<?>>();
		if (sources == null) {
			return list;
		}
		for (PropertySource<?> element : sources) {
			if (element != null) {
				list.add(element);
			}
		}
		return list;
	}

	public static void showPropertySources(List<PropertySource<?>> propertySources) {
		List<String> columns = Arrays.asList("Name", "Impl", "Source");
		List<Object[]> rows = new ArrayList<Object[]>();
		for (PropertySource<?> propertySource : propertySources) {
			String name = propertySource.getName();
			String impl = propertySource.getClass().getName();
			String source = propertySource.getSource().getClass().getName();
			Object[] row = { name, impl, source };
			rows.add(row);
		}
		LoggerUtils.logTable(columns, rows, LoggerLevel.INFO, logger, true);
	}

	public static void showPropertySources(ConfigurableEnvironment env) {
		showPropertySources(getPropertySources(env));
	}

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

		// Spring provides PropertySource objects ordered from highest priority to lowest priority
		// We reverse the order here so things follow the typical "last one in wins" strategy
		Collections.reverse(sources);

		// Convert the list of PropertySource's to a list of Properties objects
		PropertySourceConversionResult result = convertEnumerablePropertySources(sources);

		// Combine them into a single Properties object
		return PropertyUtils.combine(result.getPropertiesList());
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

	/**
	 * Get all PropertySource objects from the environment as a List.
	 */
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

	/**
	 * Convert any PropertySources that extend EnumerablePropertySource into Properties object's
	 */
	public static PropertySourceConversionResult convertEnumerablePropertySources(List<PropertySource<?>> sources) {
		PropertySourceConversionResult result = new PropertySourceConversionResult();
		List<Properties> list = new ArrayList<Properties>();
		List<PropertySource<?>> converted = new ArrayList<PropertySource<?>>();
		List<PropertySource<?>> skipped = new ArrayList<PropertySource<?>>();
		// Extract property values from the sources and place them in a Properties object
		for (PropertySource<?> source : sources) {
			logger.debug("Adding [{}]", source.getName());
			if (source instanceof EnumerablePropertySource) {
				EnumerablePropertySource<?> eps = (EnumerablePropertySource<?>) source;
				Properties sourceProperties = convert(eps);
				list.add(sourceProperties);
				converted.add(source);
			} else {
				logger.debug("Unable to obtain properties from property source [{}] -> [{}]", source.getName(), source.getClass().getName());
				skipped.add(source);
			}
		}
		result.setConverted(converted);
		result.setSkipped(skipped);
		result.setPropertiesList(list);
		return result;
	}

	/**
	 * Convert an EnumerablePropertySource into a Properties object.
	 */
	public static Properties convert(EnumerablePropertySource<?> source) {
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
