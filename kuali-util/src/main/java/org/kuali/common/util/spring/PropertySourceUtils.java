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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.spring.service.PropertySourceContext;
import org.kuali.common.util.spring.service.SpringContext;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

public class PropertySourceUtils {

	// private static final Logger logger = LoggerFactory.getLogger(PropertySourceUtils.class);

	/**
	 * Convert sources into an EnumerablePropertySource list
	 * 
	 * @throws <code>IllegalStateException</code> if any source inside sources is not enumerable
	 */
	public static List<EnumerablePropertySource<?>> asEnumerableList(List<PropertySource<?>> sources) {
		List<EnumerablePropertySource<?>> list = new ArrayList<EnumerablePropertySource<?>>();
		for (PropertySource<?> source : sources) {
			Assert.isTrue(source instanceof EnumerablePropertySource<?>, "[" + source + "] is not enumerable");
			EnumerablePropertySource<?> element = (EnumerablePropertySource<?>) source;
			list.add(element);
		}
		return list;
	}

	public static List<PropertySource<?>> getPropertySources(Class<?> annotatedClass) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(annotatedClass);
		return extractPropertySourcesAndClose(context);
	}

	public static List<PropertySource<?>> extractPropertySourcesAndClose(ConfigurableApplicationContext context) {
		// Extract PropertySources (if any)
		List<PropertySource<?>> sources = getPropertySources(context);

		// Close the context
		SpringUtils.closeQuietly(context);

		// Return the list
		return sources;
	}

	/**
	 * Copy the key/value pairs from <code>source</code> into a <code>java.util.Properties</code> object.
	 * 
	 * @throws <code>IllegalArgumentException</code> if any value is <code>null</code> or not a string.
	 */
	public static Properties convert(EnumerablePropertySource<?> source) {
		Assert.notNull(source, "source is null");
		Properties properties = new Properties();
		String[] names = source.getPropertyNames();
		for (String name : names) {
			Object object = source.getProperty(name);
			Assert.notNull(object, "[" + name + "] is null");
			Assert.isTrue(object instanceof String, "[" + name + "] is not a string");
			properties.setProperty(name, (String) object);
		}
		return properties;
	}

	/**
	 * Copy the key/value pairs from <code>sources</code> into a <code>java.util.Properties</code> object.
	 * 
	 * @throws <code>IllegalArgumentException</code> if any value is <code>null</code> or not a string.
	 */
	public static Properties convert(List<EnumerablePropertySource<?>> sources) {
		Properties converted = new Properties();
		for (EnumerablePropertySource<?> source : sources) {
			Properties properties = convert(source);
			converted.putAll(properties);
		}
		return converted;
	}

	/**
	 * Aggregate all <code>PropertySource<?><code> objects from the environment into a <code>List</code>
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
	 * Remove all property sources from <code>env</code> and replace them with a single <code>PropertiesPropertySource</code> backed by <code>properties</code>
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
	 * Remove all property sources from <code>env</code>.
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
	 * Null safe conversion of <code>PropertySource<?>[]</code> into <code>List&lt;PropertySource&lt;?>></code>
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

	/**
	 * Return all <code>PropertySource</code> beans registered in the context, sorted use <code>comparator</code>
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
	 * Return all <code>PropertySource</code> beans registered in the context, sorted by name.
	 */
	public static List<PropertySource<?>> getPropertySources(ConfigurableApplicationContext context) {
		// Sort them by name
		return getPropertySources(context, new PropertySourceNameComparator());
	}

	/**
	 * Return a <code>SpringContext</code> such that <code>source</code> is the only thing Spring uses to resolve placeholders
	 */
	public static SpringContext getSinglePropertySourceContext(PropertySource<?> source) {
		// Setup a property source context such that this property source is the only one registered with Spring
		// This PropertySource will be the ONLY thing used to resolve placeholders
		PropertySourceContext psc = new PropertySourceContext(source, true);

		// Setup a Spring context
		SpringContext context = new SpringContext();

		// Supply Spring with our PropertySource
		context.setPropertySourceContext(psc);

		// Return a Spring context configured with a single property source
		return context;
	}

}
