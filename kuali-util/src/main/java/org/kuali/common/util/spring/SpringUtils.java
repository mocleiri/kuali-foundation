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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.LoggerLevel;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.PropertiesContext;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class SpringUtils {

	private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

	private static final String GLOBAL_SPRING_PROPERTY_SOURCE_NAME = "springPropertySource";

	public static <T> T getProperty(Optional<EnvironmentService> env, List<String> keys, Class<T> type, T provided) {
		return getProperty(env, keys, type, Optional.fromNullable(provided)).orNull();
	}

	public static <T> Optional<T> getProperty(Optional<EnvironmentService> env, String key, Class<T> type, Optional<T> provided) {
		return getProperty(env, ImmutableList.of(key), type, provided);
	}

	public static <T> Optional<T> getProperty(Optional<EnvironmentService> env, List<String> keys, Class<T> type, Optional<T> provided) {
		if (!env.isPresent()) {
			return provided;
		}
		for (String key : keys) {
			Optional<T> value = getOptionalProperty(env.get(), key, type);
			if (value.isPresent()) {
				return value;
			}
		}
		return provided;
	}

	public static Optional<String> getString(Optional<EnvironmentService> env, List<String> keys, Optional<String> provided) {
		if (!env.isPresent()) {
			return provided;
		}
		for (String key : keys) {
			Optional<String> value = getOptionalString(env.get(), key);
			if (value.isPresent()) {
				return value;
			}
		}
		return provided;
	}

	public static String getString(EnvironmentService env, List<String> keys) {
		return getString(Optional.of(env), keys, Optional.<String> absent()).get();
	}

	public static Optional<String> getString(EnvironmentService env, List<String> keys, Optional<String> provided) {
		return getString(Optional.of(env), keys, provided);
	}

	public static Optional<String> getString(EnvironmentService env, String key, Optional<String> provided) {
		return getString(env, ImmutableList.of(key), provided);
	}

	public static Optional<Boolean> getBoolean(EnvironmentService env, String key, Optional<Boolean> provided) {
		Optional<Boolean> value = getOptionalBoolean(env, key);
		if (value.isPresent()) {
			return value;
		} else {
			return provided;
		}
	}

	public static Optional<Integer> getInteger(EnvironmentService env, String key, Optional<Integer> provided) {
		Optional<Integer> value = getOptionalInteger(env, key);
		if (value.isPresent()) {
			return value;
		} else {
			return provided;
		}
	}

	public static Optional<Integer> getOptionalInteger(EnvironmentService env, String key) {
		if (!env.containsProperty(key)) {
			return Optional.absent();
		} else {
			return Optional.of(env.getInteger(key));
		}
	}

	/**
	 * If there is no value for <code>key</code> return Optional.absent(), otherwise return Optional.of(value)
	 */
	public static <T> Optional<T> getOptionalProperty(EnvironmentService env, String key, Class<T> type) {
		if (!env.containsProperty(key)) {
			return Optional.absent();
		} else {
			T value = env.getProperty(key, type);
			return Optional.of(value);
		}
	}

	/**
	 * If there is no value for <code>key</code> or the value is NULL or NONE, return Optional.absent(), otherwise return Optional.of(value)
	 */
	public static Optional<String> getOptionalString(EnvironmentService env, String key) {
		if (!env.containsProperty(key)) {
			return Optional.absent();
		} else {
			return NullUtils.toAbsent(env.getString(key));
		}
	}

	public static Optional<Boolean> getOptionalBoolean(EnvironmentService env, String key) {
		if (!env.containsProperty(key)) {
			return Optional.absent();
		} else {
			return Optional.of(env.getBoolean(key));
		}
	}

	@Deprecated
	public static org.kuali.common.util.service.SpringContext getSpringContext(List<Class<?>> annotatedClasses, org.kuali.common.util.ProjectContext project,
			List<org.kuali.common.util.ProjectContext> others) {
		// This PropertySource object is backed by a set of properties that has been
		// 1 - fully resolved
		// 2 - contains all properties needed by Spring
		// 3 - contains system/environment properties where system/env properties override loaded properties
		PropertySource<?> source = getGlobalPropertySource(project, others);

		// Setup a property source context such that our single property source is the only one registered with Spring
		// This will make it so our PropertySource is the ONLY thing used to resolve placeholders
		org.kuali.common.util.service.PropertySourceContext psc = new org.kuali.common.util.service.PropertySourceContext(source, true);

		// Setup a Spring context
		org.kuali.common.util.service.SpringContext context = new org.kuali.common.util.service.SpringContext();

		// Supply Spring with our PropertySource
		context.setPropertySourceContext(psc);

		// Supply Spring with java classes containing annotated config
		context.setAnnotatedClasses(annotatedClasses);

		// Return a Spring context configured with a single property source
		return context;
	}

	@Deprecated
	public static org.kuali.common.util.service.SpringContext getSpringContext(Class<?> annotatedClass, org.kuali.common.util.ProjectContext project,
			List<org.kuali.common.util.ProjectContext> others) {
		return getSpringContext(CollectionUtils.asList(annotatedClass), project, others);
	}

	/**
	 * 
	 */
	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(org.kuali.common.util.ProjectContext project, org.kuali.common.util.ProjectContext other) {
		return getGlobalPropertySource(project, Arrays.asList(other));
	}

	/**
	 * 
	 */
	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(org.kuali.common.util.ProjectContext project, List<org.kuali.common.util.ProjectContext> others) {
		return getGlobalPropertySource(project, others, null);
	}

	/**
	 * 
	 */
	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(org.kuali.common.util.ProjectContext project, Mode missingLocationsMode) {
		return getGlobalPropertySource(project, missingLocationsMode, Collections.<org.kuali.common.util.ProjectContext> emptyList());
	}

	/**
	 * 
	 */
	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(org.kuali.common.util.ProjectContext project, Mode missingLocationsMode, org.kuali.common.util.ProjectContext... others) {
		return getGlobalPropertySource(project, missingLocationsMode, Arrays.asList(others));
	}

	/**
	 * 
	 */
	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(org.kuali.common.util.ProjectContext project, Mode missingLocationsMode,
			List<org.kuali.common.util.ProjectContext> others) {
		org.kuali.common.util.property.ProjectProperties pp = ConfigUtils.getProjectProperties(project);
		pp.getPropertiesContext().setMissingLocationsMode(missingLocationsMode);
		return getGlobalPropertySource(pp, others, null);
	}

	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(org.kuali.common.util.property.ProjectProperties projectProperties, List<org.kuali.common.util.ProjectContext> others,
			Properties properties) {
		ConfigUtils.combine(projectProperties, properties);
		List<org.kuali.common.util.property.ProjectProperties> otherProjectProperties = ConfigUtils.getProjectProperties(others);
		// Get a PropertySource object backed by the properties loaded from the list as well as system/environment properties
		return getGlobalPropertySource(projectProperties, otherProjectProperties);
	}

	/**
	 * <code>project</code> needs to be a top level project eg rice-sampleapp, olefs-webapp. <code>others</code> is projects for submodules organized into a list where the last one
	 * in wins.
	 */
	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(org.kuali.common.util.ProjectContext project, List<org.kuali.common.util.ProjectContext> others, Properties properties) {

		org.kuali.common.util.property.ProjectProperties projectProperties = ConfigUtils.getProjectProperties(project, properties);

		List<org.kuali.common.util.property.ProjectProperties> otherProjectProperties = ConfigUtils.getProjectProperties(others);

		// Get a PropertySource object backed by the properties loaded from the list as well as system/environment properties
		return getGlobalPropertySource(projectProperties, otherProjectProperties);
	}

	/**
	 * <code>project</code> needs to be a top level project eg rice-sampleapp, olefs-webapp. <code>others</code> is projects for submodules organized into a list where the last one
	 * in wins.
	 */
	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(org.kuali.common.util.property.ProjectProperties project) {
		return getGlobalPropertySource(project, null);
	}

	/**
	 * <code>project</code> needs to be a top level project eg rice-sampleapp, olefs-webapp. <code>others</code> is projects for submodules organized into a list where the last one
	 * in wins.
	 */
	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(org.kuali.common.util.property.ProjectProperties project, List<org.kuali.common.util.property.ProjectProperties> others) {
		// Property loading uses a "last one in wins" strategy
		List<org.kuali.common.util.property.ProjectProperties> list = new ArrayList<org.kuali.common.util.property.ProjectProperties>();

		// Add project properties first so they can be used to resolve locations
		list.add(project);

		if (!CollectionUtils.isEmpty(others)) {
			// Load in other project properties
			list.addAll(others);

			// Add project properties last so they override loaded properties
			list.add(project);
		}

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

	public static List<String> getListFromCSV(Environment env, String key) {
		String csv = SpringUtils.getProperty(env, key);
		return CollectionUtils.getTrimmedListFromCSV(csv);
	}

	/**
	 * If the CSV value evaluates to <code>null</code>, <code>"null"</code>, <code>"none"</code> or the empty string, return an empty list.
	 */
	public static List<String> getNoneSensitiveListFromCSV(Environment env, String key) {
		String csv = SpringUtils.getProperty(env, key);
		return CollectionUtils.getNoneSensitiveListFromCSV(csv);
	}

	/**
	 * If the CSV value evaluates to <code>null</code>, <code>"null"</code>, <code>"none"</code> or the empty string, return an empty list.
	 */
	public static List<String> getNoneSensitiveListFromCSV(EnvironmentService env, String key, String defaultValue) {
		String csv = env.getString(key, defaultValue);
		return CollectionUtils.getNoneSensitiveListFromCSV(csv);
	}

	/**
	 * If the CSV value evaluates to <code>null</code>, <code>"null"</code>, <code>"none"</code> or the empty string, return an empty list.
	 */
	public static List<String> getNoneSensitiveListFromCSV(EnvironmentService env, String key) {
		return getNoneSensitiveListFromCSV(env, key, null);
	}

	/**
	 * If the environment contains a value for <code>key</code> (NONE sensitive) use the environment value, otherwise use the defaults.
	 */
	public static List<String> getStrings(EnvironmentService env, String key, List<String> defaults) {
		if (env.containsProperty(key)) {
			return getNoneSensitiveListFromCSV(env, key);
		} else {
			return defaults;
		}
	}

	/**
	 * If the CSV value evaluates to <code>null</code>, <code>"null"</code>, <code>"none"</code> or the empty string, return an empty list.
	 */
	public static List<String> getNoneSensitiveListFromCSV(Environment env, String key, String defaultValue) {
		String csv = SpringUtils.getProperty(env, key, defaultValue);
		return CollectionUtils.getNoneSensitiveListFromCSV(csv);
	}

	@Deprecated
	public static List<PropertySource<?>> getPropertySources(org.kuali.common.util.service.SpringService service, Class<?> annotatedClass, String propertiesBeanName,
			Properties properties) {
		return getPropertySources(annotatedClass, propertiesBeanName, properties);
	}

	/**
	 * Scan the annotated class to find the single bean registered in the context that implements <code>PropertySource</code>. If more than one bean is located, throw
	 * <code>IllegalStateException</code>.
	 */
	@Deprecated
	public static PropertySource<?> getSinglePropertySource(Class<?> annotatedClass) {
		return getSinglePropertySource(annotatedClass, null, null);
	}

	/**
	 * Scan the annotated class to find the single bean registered in the context that implements <code>PropertySource</code>. If more than one bean is located, throw
	 * <code>IllegalStateException</code>.
	 */
	@Deprecated
	public static PropertySource<?> getSinglePropertySource(Class<?> annotatedClass, String propertiesBeanName, Properties properties) {
		List<PropertySource<?>> sources = getPropertySources(annotatedClass, propertiesBeanName, properties);
		Assert.isTrue(sources.size() == 1, "Must be exactly one PropertySource registered in the context");
		return sources.get(0);
	}

	@Deprecated
	public static List<PropertySource<?>> getPropertySources(Class<?> annotatedClass, String propertiesBeanName, Properties properties) {
		return getPropertySources(annotatedClass, propertiesBeanName, properties, null, null);
	}

	public static void setupProfiles(ConfigurableApplicationContext ctx, List<String> activeProfiles, List<String> defaultProfiles) {
		if (!CollectionUtils.isEmpty(activeProfiles)) {
			ConfigurableEnvironment env = ctx.getEnvironment();
			env.setActiveProfiles(CollectionUtils.toStringArray(activeProfiles));
		}
		if (!CollectionUtils.isEmpty(defaultProfiles)) {
			ConfigurableEnvironment env = ctx.getEnvironment();
			env.setDefaultProfiles(CollectionUtils.toStringArray(defaultProfiles));
		}
	}

	@Deprecated
	public static List<PropertySource<?>> getPropertySources(Class<?> annotatedClass, String propertiesBeanName, Properties properties, List<String> activeProfiles,
			List<String> defaultProfiles) {
		ConfigurableApplicationContext parent = null;
		if (properties == null) {
			parent = getConfigurableApplicationContext();
		} else {
			parent = getContextWithPreRegisteredBean(propertiesBeanName, properties);
		}
		AnnotationConfigApplicationContext child = new AnnotationConfigApplicationContext();
		child.setParent(parent);
		setupProfiles(child, activeProfiles, defaultProfiles);
		child.register(annotatedClass);
		child.refresh();
		return getPropertySources(child);
	}

	@Deprecated
	public static List<PropertySource<?>> getPropertySources(org.kuali.common.util.service.SpringService service, String location, String mavenPropertiesBeanName,
			Properties mavenProperties) {
		return getPropertySources(location, mavenPropertiesBeanName, mavenProperties);
	}

	@Deprecated
	public static List<PropertySource<?>> getPropertySources(String location, String propertiesBeanName, Properties properties) {
		return getPropertySources(location, propertiesBeanName, properties, null, null);
	}

	@Deprecated
	public static List<PropertySource<?>> getPropertySources(String location, String propertiesBeanName, Properties properties, List<String> activeProfiles,
			List<String> defaultProfiles) {
		String[] locationsArray = { location };
		ConfigurableApplicationContext parent = getContextWithPreRegisteredBean(propertiesBeanName, properties);
		ConfigurableApplicationContext child = new ClassPathXmlApplicationContext(locationsArray, false, parent);
		setupProfiles(child, activeProfiles, defaultProfiles);
		child.refresh();
		return getPropertySources(child);
	}

	@Deprecated
	public static Executable getSpringExecutable(Environment env, boolean skip, PropertySource<?> ps, List<Class<?>> annotatedClasses) {
		/**
		 * This line creates a property source containing 100% of the properties needed by Spring to resolve any/all placeholders. It will be the only property source available to
		 * Spring so it needs to include system properties and environment variables
		 */
		org.kuali.common.util.service.PropertySourceContext psc = new org.kuali.common.util.service.PropertySourceContext(ps, true);

		// Setup the Spring context
		org.kuali.common.util.service.SpringContext context = new org.kuali.common.util.service.SpringContext();
		context.setAnnotatedClasses(annotatedClasses);
		context.setPropertySourceContext(psc);

		// Load the context
		org.kuali.common.util.execute.SpringExecutable se = new org.kuali.common.util.execute.SpringExecutable();
		se.setService(new org.kuali.common.util.service.DefaultSpringService());
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
	public static long getMillis(EnvironmentService env, String key, String defaultValue) {
		String value = env.getString(key, defaultValue);
		return FormatUtils.getMillis(value);
	}

	/**
	 * Parse milliseconds from a time string that ends with a unit of measure. If no unit of measure is provided, milliseconds is assumed. Unit of measure is case insensitive.
	 * 
	 * @see FormatUtils.getMillis(String time)
	 */
	public static Optional<Integer> getMillisAsInt(EnvironmentService env, String key, Optional<Integer> provided) {
		if (env.containsProperty(key)) {
			String defaultValue = FormatUtils.getTime(provided.get());
			Long millis = getMillis(env, key, defaultValue);
			return Optional.of(millis.intValue());
		} else {
			return provided;
		}
	}

	/**
	 * Parse milliseconds from a time string that ends with a unit of measure. If no unit of measure is provided, milliseconds is assumed. Unit of measure is case insensitive.
	 * 
	 * @see FormatUtils.getMillis(String time)
	 */
	public static int getMillisAsInt(EnvironmentService env, String key, String defaultValue) {
		return new Long(getMillis(env, key, defaultValue)).intValue();
	}

	/**
	 * Parse milliseconds from a time string that ends with a unit of measure. If no unit of measure is provided, milliseconds is assumed. Unit of measure is case insensitive.
	 * 
	 * @see FormatUtils.getMillis(String time)
	 */
	public static int getMillisAsInt(EnvironmentService env, String key, int defaultValue) {
		return getMillisAsInt(env, key, FormatUtils.getTime(defaultValue));
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
	public static int getBytesInteger(Environment env, String key, String defaultValue) {
		Long value = getBytes(env, key, defaultValue);
		return getValidatedIntValue(value);
	}

	/**
	 * Parse bytes from a size string that ends with a unit of measure. If no unit of measure is provided, bytes is assumed. Unit of measure is case insensitive.
	 * 
	 * @see FormatUtils.getBytes(String size)
	 */
	public static int getBytesInteger(Environment env, String key) {
		Long value = getBytes(env, key);
		return getValidatedIntValue(value);
	}

	/**
	 * Throw IllegalArgumentException if value is outside the range of an Integer, otherwise return the Integer value.
	 */
	public static int getValidatedIntValue(Long value) {
		if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
			throw new IllegalArgumentException(value + " is outside the range of an integer");
		} else {
			return value.intValue();
		}
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

	public static List<File> getFilesFromCSV(Environment env, String key, String defaultValue) {
		List<String> strings = getNoneSensitiveListFromCSV(env, key, defaultValue);
		List<File> files = new ArrayList<File>();
		for (String string : strings) {
			File file = new File(string);
			files.add(file);
		}
		return files;
	}

	public static List<File> getFilesFromCSV(Environment env, String key) {
		return getFilesFromCSV(env, key, null);
	}

	public static File getFile(Environment env, String key, File defaultValue) {
		String value = getProperty(env, key, LocationUtils.getCanonicalPath(defaultValue));
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

	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(String name, List<org.kuali.common.util.property.ProjectProperties> pps) {
		// Load them from disk
		Properties source = PropertyUtils.load(pps);

		// Add in system/environment properties
		Properties globalSource = PropertyUtils.getGlobalProperties(source);

		logger.debug("Before prepareContextProperties()");
		PropertyUtils.debug(globalSource);

		// Prepare them so they are ready for use
		PropertyUtils.prepareContextProperties(globalSource);

		logger.debug("After prepareContextProperties()");
		PropertyUtils.debug(globalSource);

		// Return a PropertySource backed by the properties
		return new PropertiesPropertySource(name, globalSource);
	}

	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(Properties properties) {
		return new PropertiesPropertySource(GLOBAL_SPRING_PROPERTY_SOURCE_NAME, properties);
	}

	/**
	 * Return a SpringContext that resolves all placeholders from the PropertySource passed in
	 */
	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(List<String> locations, String encoding) {
		Properties loaded = PropertyUtils.load(locations, encoding);
		Properties global = PropertyUtils.getGlobalProperties(loaded);
		PropertyUtils.prepareContextProperties(global);
		return getGlobalPropertySource(global);
	}

	/**
	 * Return a SpringContext that resolves all placeholders from the list of property locations passed in + System/Environment properties
	 */
	@Deprecated
	public static org.kuali.common.util.service.SpringContext getSinglePropertySourceContext(org.kuali.common.util.ProjectContext context, String location) {
		PropertySource<?> source = getGlobalPropertySource(context, location);
		return getSinglePropertySourceContext(source);
	}

	/**
	 * Return a SpringExecutable for the project, properties location, and config passed in.
	 */
	@Deprecated
	public static org.kuali.common.util.execute.SpringExecutable getSpringExecutable(org.kuali.common.util.ProjectContext project, String location, List<Class<?>> annotatedClasses) {
		org.kuali.common.util.service.SpringContext context = getSinglePropertySourceContext(project, location);
		context.setAnnotatedClasses(annotatedClasses);

		org.kuali.common.util.execute.SpringExecutable executable = new org.kuali.common.util.execute.SpringExecutable();
		executable.setContext(context);
		return executable;
	}

	/**
	 * Return a SpringExecutable for the org.kuali.common.util.config.supplier.PropertiesSupplier and annotatedClass passed in
	 */
	@Deprecated
	public static org.kuali.common.util.execute.SpringExecutable getSpringExecutable(org.kuali.common.util.config.supplier.PropertiesSupplier supplier, Class<?> annotatedClass) {
		return getSpringExecutable(supplier, CollectionUtils.asList(annotatedClass));
	}

	/**
	 * Return a SpringExecutable for the org.kuali.common.util.config.supplier.PropertiesSupplier and annotatedClasses passed in
	 */
	@Deprecated
	public static org.kuali.common.util.execute.SpringExecutable getSpringExecutable(org.kuali.common.util.config.supplier.PropertiesSupplier supplier,
			List<Class<?>> annotatedClasses) {
		Properties properties = supplier.getProperties();
		PropertySource<?> source = getGlobalPropertySource(properties);
		org.kuali.common.util.service.SpringContext context = getSinglePropertySourceContext(source);
		context.setAnnotatedClasses(annotatedClasses);
		return new org.kuali.common.util.execute.SpringExecutable(context);
	}

	/**
	 * Return a SpringExecutable for the PropertySource and annotatedClass passed in
	 */
	@Deprecated
	public static org.kuali.common.util.execute.SpringExecutable getSpringExecutable(PropertySource<?> source, Class<?> annotatedClass) {
		org.kuali.common.util.service.SpringContext context = getSinglePropertySourceContext(source);
		context.setAnnotatedClasses(CollectionUtils.asList(annotatedClass));
		return new org.kuali.common.util.execute.SpringExecutable(context);
	}

	/**
	 * Return a SpringExecutable for the project, properties location, and config passed in.
	 */
	@Deprecated
	public static org.kuali.common.util.execute.SpringExecutable getSpringExecutable(org.kuali.common.util.ProjectContext project, String location, Class<?> annotatedClass) {
		return getSpringExecutable(project, location, CollectionUtils.asList(annotatedClass));
	}

	/**
	 * Return a SpringExecutable for the project, properties location, and config passed in.
	 */
	@Deprecated
	public static org.kuali.common.util.execute.SpringExecutable getSpringExecutable(Class<?> annotatedClass, String location, org.kuali.common.util.ProjectContext... projects) {
		List<org.kuali.common.util.property.ProjectProperties> list = ConfigUtils.getProjectProperties(projects);
		org.kuali.common.util.property.ProjectProperties last = list.get(list.size() - 1);
		PropertiesContext pc = last.getPropertiesContext();
		if (!StringUtils.isBlank(location)) {
			List<String> locations = new ArrayList<String>(CollectionUtils.toEmptyList(pc.getLocations()));
			locations.add(location);
			pc.setLocations(locations);
		}
		PropertySource<?> source = getGlobalPropertySource(GLOBAL_SPRING_PROPERTY_SOURCE_NAME, list);
		org.kuali.common.util.service.SpringContext context = getSinglePropertySourceContext(source);
		context.setAnnotatedClasses(CollectionUtils.asList(annotatedClass));
		return new org.kuali.common.util.execute.SpringExecutable(context);
	}

	/**
	 * Return a SpringContext that resolves all placeholders from the list of property locations passed in + System/Environment properties
	 */
	@Deprecated
	public static org.kuali.common.util.service.SpringContext getSinglePropertySourceContext(List<String> locations, String encoding) {
		PropertySource<?> source = getGlobalPropertySource(locations, encoding);
		return getSinglePropertySourceContext(source);
	}

	/**
	 * Return a SpringContext that resolves all placeholders from the PropertySource passed in
	 */
	@Deprecated
	public static org.kuali.common.util.service.SpringContext getSinglePropertySourceContext(PropertySource<?> source) {
		// Setup a property source context such that our single property source is the only one registered with Spring
		// This will make it so our PropertySource is the ONLY thing used to resolve placeholders
		org.kuali.common.util.service.PropertySourceContext psc = new org.kuali.common.util.service.PropertySourceContext(source, true);

		// Setup a Spring context
		org.kuali.common.util.service.SpringContext context = new org.kuali.common.util.service.SpringContext();

		// Supply Spring with our PropertySource
		context.setPropertySourceContext(psc);

		// Return a Spring context configured with a single property source
		return context;
	}

	@Deprecated
	public static PropertySource<?> getGlobalPropertySource(org.kuali.common.util.ProjectContext context, String... locations) {
		org.kuali.common.util.property.ProjectProperties pp = org.kuali.common.util.ProjectUtils.getProjectProperties(context);
		PropertiesContext pc = pp.getPropertiesContext();
		List<String> existingLocations = CollectionUtils.toEmptyList(pc.getLocations());
		if (locations != null) {
			for (String location : locations) {
				existingLocations.add(location);
			}
		}
		pc.setLocations(existingLocations);
		return getGlobalPropertySource(pp);
	}

	@Deprecated
	public static PropertySource<?> getPropertySource(String name, List<org.kuali.common.util.property.ProjectProperties> pps) {
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
	 *  org.kuali.common:kuali-jdbc -> classpath:org/kuali/common/kuali-jdbc/properties-context.xml
	 * </pre>
	 */
	@Deprecated
	public static String getDefaultPropertyContextLocation(String gav) {
		Assert.hasText(gav, "gav has no text");
		org.kuali.common.util.Project p = org.kuali.common.util.ProjectUtils.getProject(gav);
		return org.kuali.common.util.ProjectUtils.getClassPathPrefix(p) + "/properties-context.xml";
	}

	/**
	 * Make sure all of the locations actually exist.<br>
	 */
	@Deprecated
	public static void validateExists(List<String> locations) {
		LocationUtils.validateExists(locations);
	}

	public static AbstractApplicationContext getContextWithPreRegisteredBeans(String id, String displayName, Map<String, ?> beans) {
		GenericXmlApplicationContext appContext = new GenericXmlApplicationContext();
		if (!StringUtils.isBlank(id)) {
			appContext.setId(id);
		}
		if (!StringUtils.isBlank(displayName)) {
			appContext.setDisplayName(displayName);
		}
		appContext.refresh();
		ConfigurableListableBeanFactory factory = appContext.getBeanFactory();
		for (String beanName : beans.keySet()) {
			Object bean = beans.get(beanName);
			logger.debug("Registering bean - [{}] -> [{}]", beanName, bean.getClass().getName());
			factory.registerSingleton(beanName, bean);
		}
		return appContext;
	}

	@Deprecated
	public static ConfigurableApplicationContext getContextWithPreRegisteredBeans(String id, String displayName, List<String> beanNames, List<Object> beans) {
		Map<String, Object> contextBeans = new HashMap<String, Object>();
		CollectionUtils.combine(contextBeans, beanNames, beans);
		return getContextWithPreRegisteredBeans(id, displayName, contextBeans);
	}

	@Deprecated
	public static ConfigurableApplicationContext getContextWithPreRegisteredBeans(List<String> beanNames, List<Object> beans) {
		return getContextWithPreRegisteredBeans(null, null, beanNames, beans);
	}

	@Deprecated
	public static ConfigurableApplicationContext getConfigurableApplicationContext() {
		return new GenericXmlApplicationContext();
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

	@Deprecated
	public static ConfigurableApplicationContext getContextWithPreRegisteredBean(String beanName, Object bean) {
		return getContextWithPreRegisteredBeans(Arrays.asList(beanName), Arrays.asList(bean));
	}

	@Deprecated
	public static List<PropertySource<?>> getPropertySourcesFromAnnotatedClass(String annotatedClassName) {
		Class<?> annotatedClass = ReflectionUtils.getClass(annotatedClassName);
		return getPropertySources(annotatedClass);
	}

	@Deprecated
	public static List<PropertySource<?>> getPropertySources(Class<?> annotatedClass) {
		return PropertySourceUtils.getPropertySources(annotatedClass);
	}

	@Deprecated
	public static List<PropertySource<?>> extractPropertySourcesAndClose(ConfigurableApplicationContext context) {
		return PropertySourceUtils.extractPropertySourcesAndClose(context);
	}

	/**
	 * Scan the XML Spring context for any beans that implement <code>PropertySource</code>
	 */
	@Deprecated
	public static List<PropertySource<?>> getPropertySources(String location) {
		ConfigurableApplicationContext context = new GenericXmlApplicationContext(location);
		return extractPropertySourcesAndClose(context);
	}

	/**
	 * This method returns a list of any PropertySource objects registered in the indicated context. They are sorted by property source name.
	 */
	@Deprecated
	public static List<PropertySource<?>> getPropertySources(ConfigurableApplicationContext context) {
		return PropertySourceUtils.getPropertySources(context);
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
	@Deprecated
	public static List<PropertySource<?>> getPropertySources(ConfigurableApplicationContext context, Comparator<PropertySource<?>> comparator) {
		return PropertySourceUtils.getPropertySources(context, comparator);
	}

	/**
	 * Null safe method for converting an untyped array of property sources into a list. Never returns null.
	 */
	@Deprecated
	public static List<PropertySource<?>> asList(PropertySource<?>... sources) {
		return PropertySourceUtils.asList(sources);
	}

	public static void debugQuietly(ApplicationContext ctx) {
		if (!logger.isDebugEnabled() || ctx == null) {
			return;
		}
		if (ctx.getParent() != null) {
			debug(ctx.getParent());
		}
		debug(ctx);
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
			String instance = (bean == null) ? NullUtils.NULL : bean.getClass().getSimpleName();
			String hashcode = (bean == null) ? NullUtils.NULL : Integer.toHexString(bean.hashCode());
			Object[] row = { name, instance, hashcode };
			rows.add(row);
		}
		LoggerUtils.logTable(columns, rows, LoggerLevel.DEBUG, logger, true);
		logger.debug("----------------------------------------------------------------------");
	}

	@Deprecated
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

	@Deprecated
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
	@Deprecated
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
	@Deprecated
	public static void reconfigurePropertySources(ConfigurableEnvironment env, String name, Properties properties) {
		PropertySourceUtils.reconfigurePropertySources(env, name, properties);
	}

	/**
	 * Remove any existing property sources
	 */
	@Deprecated
	public static void removeAllPropertySources(ConfigurableEnvironment env) {
		PropertySourceUtils.removeAllPropertySources(env);
	}

	/**
	 * Get all PropertySource objects from the environment as a List.
	 */
	@Deprecated
	public static List<PropertySource<?>> getPropertySources(ConfigurableEnvironment env) {
		return PropertySourceUtils.getPropertySources(env);
	}

	/**
	 * Convert any PropertySources that extend EnumerablePropertySource into Properties object's
	 */
	@Deprecated
	public static PropertySourceConversionResult convertEnumerablePropertySources(List<PropertySource<?>> sources) {
		PropertySourceConversionResult result = new PropertySourceConversionResult();
		List<Properties> list = new ArrayList<Properties>();
		List<PropertySource<?>> converted = new ArrayList<PropertySource<?>>();
		List<PropertySource<?>> skipped = new ArrayList<PropertySource<?>>();
		// Extract property values from the sources and place them in a Properties object
		for (PropertySource<?> source : sources) {
			Assert.isTrue(source instanceof EnumerablePropertySource, "[" + source + "] is not enumerable");
			EnumerablePropertySource<?> eps = (EnumerablePropertySource<?>) source;
			Properties sourceProperties = convert(eps);
			list.add(sourceProperties);
			converted.add(source);
		}
		result.setConverted(converted);
		result.setSkipped(skipped);
		result.setPropertiesList(list);
		return result;
	}

	/**
	 * Convert an EnumerablePropertySource into a Properties object.
	 */
	@Deprecated
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
