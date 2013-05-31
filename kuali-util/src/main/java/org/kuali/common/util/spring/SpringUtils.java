/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.SpringExecutable;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

public class SpringUtils {

	private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

	private static final String GLOBAL_SPRING_PROPERTY_SOURCE_NAME = "springPropertySource";

	public static SpringContext getSpringContext(List<Class<?>> annotatedClasses, ProjectContext project, List<ProjectContext> others) {
		// This PropertySource object is backed by a set of properties that has been
		// 1 - fully resolved
		// 2 - contains all properties needed by Spring
		// 3 - contains system/environment properties where system/env properties override loaded properties
		PropertySource<?> source = getGlobalPropertySource(project, others);

		// Setup a property source context such that our single property source is the only one registered with Spring
		// This will make it so our PropertySource is the ONLY thing used to resolve placeholders
		PropertySourceContext psc = new PropertySourceContext(source, true);

		// Setup a Spring context
		SpringContext context = new SpringContext();

		// Supply Spring with our PropertySource
		context.setPropertySourceContext(psc);

		// Supply Spring with java classes containing annotated config
		context.setAnnotatedClasses(annotatedClasses);

		// Return a Spring context configured with a single property source
		return context;
	}

	public static SpringContext getSpringContext(Class<?> annotatedClass, ProjectContext project, List<ProjectContext> others) {
		return getSpringContext(CollectionUtils.asList(annotatedClass), project, others);
	}

	/**
	 * <code>project</code> needs to be a top level project eg rice-sampleapp, olefs-webapp. <code>others</code> is projects for submodules organized into a list where the last one
	 * in wins.
	 */
	public static PropertySource<?> getGlobalPropertySource(ProjectContext project, ProjectContext other) {
		return getGlobalPropertySource(project, Arrays.asList(other));
	}

	/**
	 * <code>project</code> needs to be a top level project eg rice-sampleapp, olefs-webapp. <code>others</code> is projects for submodules organized into a list where the last one
	 * in wins.
	 */
	public static PropertySource<?> getGlobalPropertySource(ProjectContext project, List<ProjectContext> others) {
		return getGlobalPropertySource(project, others, null);
	}

	/**
	 * <code>project</code> needs to be a top level project eg rice-sampleapp, olefs-webapp. <code>others</code> is projects for submodules organized into a list where the last one
	 * in wins.
	 */
	public static PropertySource<?> getGlobalPropertySource(ProjectContext project, List<ProjectContext> others, Properties properties) {

		ProjectProperties projectProperties = ProjectUtils.loadProjectProperties(project);
		projectProperties.getPropertiesContext().setProperties(properties);

		List<ProjectProperties> otherProjectProperties = new ArrayList<ProjectProperties>();
		for (ProjectContext other : CollectionUtils.toEmptyList(others)) {
			ProjectProperties opp = ProjectUtils.loadProjectProperties(other);
			otherProjectProperties.add(opp);
		}

		// Get a PropertySource object backed by the properties loaded from the list as well as system/environment properties
		return getGlobalPropertySource(projectProperties, otherProjectProperties);
	}

	/**
	 * <code>project</code> needs to be a top level project eg rice-sampleapp, olefs-webapp. <code>others</code> is projects for submodules organized into a list where the last one
	 * in wins.
	 */
	public static PropertySource<?> getGlobalPropertySource(ProjectProperties project, List<ProjectProperties> others) {
		// Property loading uses a "last one in wins" strategy
		List<ProjectProperties> list = new ArrayList<ProjectProperties>();

		// Add project properties first so they can be used to resolve locations
		list.add(project);

		// Load in project properties
		list.addAll(others);

		// Add project properties last so they override loaded properties
		list.add(project);

		// Get a PropertySource object backed by the properties loaded from the list as well as system/environment properties
		return getGlobalPropertySource(GLOBAL_SPRING_PROPERTY_SOURCE_NAME, list);
	}

	public static List<String> getIncludes(Environment env, String key, String defaultValue) {
		String includes = SpringUtils.getProperty(env, key, defaultValue);
		if (NullUtils.isNull(includes) || StringUtils.equals(includes, Constants.WILDCARD)) {
			return new ArrayList<String>();
		} else {
			return CollectionUtils.getTrimmedListFromCSV(includes);
		}
	}

	public static List<String> getIncludes(Environment env, String key) {
		return getIncludes(env, key, null);
	}

	public static List<String> getExcludes(Environment env, String key, String defaultValue) {
		String excludes = SpringUtils.getProperty(env, key, defaultValue);
		if (NullUtils.isNullOrNone(excludes)) {
			return new ArrayList<String>();
		} else {
			return CollectionUtils.getTrimmedListFromCSV(excludes);
		}
	}

	public static List<String> getExcludes(Environment env, String key) {
		return getExcludes(env, key, null);
	}

	/**
	 * Given a property holding the name of a class, return an instance of that class
	 */
	public static <T> T getInstance(Environment env, String key, Class<T> defaultValue) {
		String className = getProperty(env, key, defaultValue.getCanonicalName());
		return ReflectionUtils.newInstance(className);
	}

	/**
	 * Given a property holding the name of a class, return an instance of that class
	 */
	public static <T> T getInstance(Environment env, String key) {
		String className = getProperty(env, key, null);
		return ReflectionUtils.newInstance(className);
	}

	public static List<String> getListFromCSV(Environment env, String key, String defaultValue) {
		String csv = SpringUtils.getProperty(env, key, defaultValue);
		return CollectionUtils.getTrimmedListFromCSV(csv);
	}

	@Deprecated
	public static List<PropertySource<?>> getPropertySources(SpringService service, Class<?> annotatedClass, String mavenPropertiesBeanName, Properties mavenProperties) {
		return getPropertySources(annotatedClass, mavenPropertiesBeanName, mavenProperties);
	}

	public static List<PropertySource<?>> getPropertySources(Class<?> annotatedClass, String mavenPropertiesBeanName, Properties mavenProperties) {
		ConfigurableApplicationContext parent = null;
		if (mavenProperties == null) {
			parent = getConfigurableApplicationContext();
		} else {
			parent = getContextWithPreRegisteredBean(mavenPropertiesBeanName, mavenProperties);
		}
		AnnotationConfigApplicationContext child = new AnnotationConfigApplicationContext();
		child.setParent(parent);
		child.register(annotatedClass);
		child.refresh();
		return SpringUtils.getPropertySources(child);
	}

	@Deprecated
	public static List<PropertySource<?>> getPropertySources(SpringService service, String location, String mavenPropertiesBeanName, Properties mavenProperties) {
		return getPropertySources(location, mavenPropertiesBeanName, mavenProperties);
	}

	public static List<PropertySource<?>> getPropertySources(String location, String mavenPropertiesBeanName, Properties mavenProperties) {
		String[] locationsArray = { location };
		ConfigurableApplicationContext parent = getContextWithPreRegisteredBean(mavenPropertiesBeanName, mavenProperties);
		ConfigurableApplicationContext child = new ClassPathXmlApplicationContext(locationsArray, parent);
		return SpringUtils.getPropertySources(child);
	}

	public static Executable getSpringExecutable(Environment env, boolean skip, PropertySource<?> ps, List<Class<?>> annotatedClasses) {
		/**
		 * This line creates a property source containing 100% of the properties needed by Spring to resolve any/all placeholders. It will be the only property source available to
		 * Spring so it needs to include system properties and environment variables
		 */
		PropertySourceContext psc = new PropertySourceContext(ps, true);

		// Setup the Spring context
		SpringContext context = new SpringContext();
		context.setAnnotatedClasses(annotatedClasses);
		context.setPropertySourceContext(psc);

		// Load the context
		SpringExecutable se = new SpringExecutable();
		se.setService(new DefaultSpringService());
		se.setContext(context);
		se.setSkip(skip);
		return se;
	}

	public static int getInteger(Environment env, String key) {
		String value = getProperty(env, key);
		return Integer.parseInt(value);
	}

	public static int getInteger(Environment env, String key, int defaultValue) {
		String value = getProperty(env, key, Integer.toString(defaultValue));
		return Integer.parseInt(value);
	}

	public static long getLong(Environment env, String key) {
		String value = getProperty(env, key);
		return Long.parseLong(value);
	}

	public static long getLong(Environment env, String key, long defaultValue) {
		String value = getProperty(env, key, Long.toString(defaultValue));
		return Long.parseLong(value);
	}

	public static double getDouble(Environment env, String key) {
		String value = getProperty(env, key);
		return Double.parseDouble(value);
	}

	public static double getDouble(Environment env, String key, double defaultValue) {
		String value = getProperty(env, key, Double.toString(defaultValue));
		return Double.parseDouble(value);
	}

	/**
	 * Parse milliseconds from a time string that ends with a unit of measure. If no unit of measure is provided, milliseconds is assumed. Unit of measure is case insensitive.
	 * 
	 * @see FormatUtils.getMillis(String time)
	 */
	public static long getMillis(Environment env, String key, String defaultValue) {
		String value = getProperty(env, key, defaultValue);
		return FormatUtils.getMillis(value);
	}

	/**
	 * Parse bytes from a size string that ends with a unit of measure. If no unit of measure is provided, bytes is assumed. Unit of measure is case insensitive.
	 * 
	 * @see FormatUtils.getBytes(String size)
	 */
	public static long getBytes(Environment env, String key, String defaultValue) {
		String value = getProperty(env, key, defaultValue);
		return FormatUtils.getBytes(value);
	}

	/**
	 * Parse bytes from a size string that ends with a unit of measure. If no unit of measure is provided, bytes is assumed. Unit of measure is case insensitive.
	 * 
	 * @see FormatUtils.getBytes(String size)
	 */
	public static long getBytes(Environment env, String key) {
		String value = getProperty(env, key);
		return FormatUtils.getBytes(value);
	}

	public static File getFile(Environment env, String key) {
		String value = getProperty(env, key);
		return new File(value);
	}

	public static boolean getBoolean(Environment env, String key, boolean defaultValue) {
		String value = getProperty(env, key, Boolean.toString(defaultValue));
		return Boolean.parseBoolean(value);
	}

	public static boolean getBoolean(Environment env, String key) {
		String value = getProperty(env, key);
		return Boolean.parseBoolean(value);
	}

	public static PropertySource<?> getGlobalPropertySource(String name, List<ProjectProperties> pps) {
		// Load them from disk
		Properties source = PropertyUtils.load(pps);

		// Add in system/environment properties
		Properties globalSource = PropertyUtils.getGlobalProperties(source);

		// Prepare them so they are ready for use
		PropertyUtils.prepareContextProperties(globalSource);

		// Return a PropertySource backed by the properties
		return new PropertiesPropertySource(name, globalSource);
	}

	public static PropertySource<?> getPropertySource(String name, List<ProjectProperties> pps) {
		// Load them from disk
		Properties source = PropertyUtils.load(pps);

		// Prepare them so they are ready for use
		PropertyUtils.prepareContextProperties(source);

		// Return a PropertySource backed by the properties
		return new PropertiesPropertySource(name, source);
	}

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

	public static ConfigurableApplicationContext getContextWithPreRegisteredBeans(String id, String displayName, List<String> beanNames, List<Object> beans) {
		Assert.isTrue(beanNames.size() == beans.size());
		GenericXmlApplicationContext appContext = new GenericXmlApplicationContext();
		if (!StringUtils.isBlank(id)) {
			appContext.setId(id);
		}
		if (!StringUtils.isBlank(displayName)) {
			appContext.setDisplayName(displayName);
		}
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

	public static ConfigurableApplicationContext getConfigurableApplicationContext() {
		return new GenericXmlApplicationContext();
	}

	public static ConfigurableApplicationContext getContextWithPreRegisteredBeans(List<String> beanNames, List<Object> beans) {
		return getContextWithPreRegisteredBeans(null, null, beanNames, beans);
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

	public static List<PropertySource<?>> getPropertySourcesFromAnnotatedClass(String annotatedClassName) {
		Class<?> annotatedClass = ReflectionUtils.getClass(annotatedClassName);
		return getPropertySources(annotatedClass);
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

	public static void debug(ApplicationContext ctx) {
		logger.debug("------------------------ Spring Context ------------------------------");
		logger.debug("Id: [{}]", ctx.getId());
		logger.debug("Display Name: [{}]", ctx.getDisplayName());
		logger.debug("Application Name: [{}]", ctx.getApplicationName());
		logger.debug("----------------------------------------------------------------------");
		List<String> names = Arrays.asList(BeanFactoryUtils.beanNamesIncludingAncestors(ctx));
		List<String> columns = Arrays.asList("Name", "Type", "Hashcode");
		List<Object[]> rows = new ArrayList<Object[]>();
		Collections.sort(names);
		for (String name : names) {
			Object bean = ctx.getBean(name);
			String instance = (bean == null) ? Constants.NULL : bean.getClass().getSimpleName();
			String hashcode = (bean == null) ? Constants.NULL : Integer.toHexString(bean.hashCode());
			Object[] row = { name, instance, hashcode };
			rows.add(row);
		}
		LoggerUtils.logTable(columns, rows, LoggerLevel.DEBUG, logger, true);
		logger.debug("----------------------------------------------------------------------");
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
	 * Return true if the environment value for key is not null.
	 */
	public static boolean exists(Environment env, String key) {
		return env.getProperty(key) != null;
	}

	/**
	 * Always return a fully resolved value. Use <code>defaultValue</code> if a value cannot be located in the environment. Throw an exception if the return value contains
	 * unresolvable placeholders.
	 */
	public static String getProperty(Environment env, String key, String defaultValue) {
		if (defaultValue == null) {
			// No default value supplied, we must be able to locate this property in the environment
			return getProperty(env, key);
		} else {
			// Look up a value from the environment
			String value = env.getProperty(key);
			if (value == null) {
				// Resolve the default value against the environment
				return env.resolveRequiredPlaceholders(defaultValue);
			} else {
				// Resolve the located value against the environment
				return env.resolveRequiredPlaceholders(value);
			}
		}
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

	/**
	 * Return true if, and only if, <code>property</code> is set in the environment and evaluates to true.
	 */
	public static boolean isTrue(Environment env, String property) {
		String value = env.getProperty(property);
		if (StringUtils.isBlank(value)) {
			return false;
		} else {
			return new Boolean(value);
		}
	}

}
