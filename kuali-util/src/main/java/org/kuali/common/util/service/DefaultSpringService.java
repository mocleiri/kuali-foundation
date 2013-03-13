/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

public class DefaultSpringService implements SpringService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringService.class);

	@Override
	public List<PropertySource<?>> getPropertySources(String location) {
		// Load the indicated locations
		GenericXmlApplicationContext context = new GenericXmlApplicationContext(location);

		// Extract all beans that implement the PropertySource interface
		@SuppressWarnings("rawtypes")
		Map<String, PropertySource> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, PropertySource.class);

		// Close the context
		closeQuietly(context);

		// Convert the Map to a List
		List<PropertySource<?>> list = new ArrayList<PropertySource<?>>();
		for (PropertySource<?> source : map.values()) {
			list.add(source);
		}

		// Return the list
		return list;
	}

	@Override
	public void load(String location) {
		load(location, null, null);
	}

	@Override
	public void load(String location, String beanName, Object bean) {
		Assert.hasText(location);
		SpringContext context = new SpringContext();
		context.setLocations(Arrays.asList(location));
		context.setBeanNames(CollectionUtils.toEmptyList(beanName));
		context.setBeans(CollectionUtils.toEmptyList(bean));
		load(context);
	}

	@Override
	public void load(SpringContext context) {
		// Make sure we have at least one location to load
		Assert.isTrue(context.getLocations().size() > 0);

		// Null-safe handling for non-required parameters
		context.setBeanNames(CollectionUtils.toEmptyList(context.getBeanNames()));
		context.setBeans(CollectionUtils.toEmptyList(context.getBeans()));

		// Make sure we have a name for every bean
		Assert.isTrue(context.getBeanNames().size() == context.getBeans().size());

		// Make sure all of the locations exist
		validate(context.getLocations());

		// Convert any file names to fully qualified file system URL's
		List<String> convertedLocations = getConvertedLocations(context.getLocations());

		// The Spring classes prefer array's
		String[] locationsArray = CollectionUtils.toStringArray(convertedLocations);

		AbstractApplicationContext parent = null;
		AbstractApplicationContext child = null;
		try {
			if (isParentContextRequired(context)) {
				// Construct a parent context if necessary
				parent = getContextWithPreRegisteredBeans(context);
			}

			// Load the locations they provided us
			// Optionally wrapped in a parent context containing the pre-registered beans (if any)
			child = new ClassPathXmlApplicationContext(locationsArray, false, parent);

			// Add custom property sources (if any)
			if (!CollectionUtils.isEmpty(context.getPropertySources())) {
				addPropertySources(context, child);
			}

			// Invoke refresh to load the context
			child.refresh();
		} finally {
			// cleanup
			closeQuietly(child);
			closeQuietly(parent);
		}
	}

	/**
	 * Null safe close for a context
	 */
	protected void closeQuietly(AbstractApplicationContext context) {
		if (context != null) {
			context.close();
		}
	}

	/**
	 * Return an <code>AbstractApplicationContext</code> with <code>beans</code> and <code>PropertySource's</code> registered as dictated by the <code>SpringContext</code>
	 */
	protected AbstractApplicationContext getContextWithPreRegisteredBeans(SpringContext context) {
		List<String> beanNames = context.getBeanNames();
		List<Object> beans = context.getBeans();
		Assert.isTrue(beanNames.size() == beans.size());
		GenericXmlApplicationContext appContext = new GenericXmlApplicationContext();
		appContext.refresh();
		ConfigurableListableBeanFactory factory = appContext.getBeanFactory();
		for (int i = 0; i < beanNames.size(); i++) {
			String beanName = beanNames.get(i);
			Object bean = beans.get(i);
			logger.info("Registering [{} - {}]", beanName, bean.getClass().getName());
			factory.registerSingleton(beanName, bean);
		}
		return appContext;
	}

	protected void addPropertySources(SpringContext context, AbstractApplicationContext applicationContext) {
		List<PropertySource<?>> propertySources = context.getPropertySources();
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		MutablePropertySources sources = environment.getPropertySources();
		if (context.isLastOneInWins()) {
			Collections.reverse(propertySources);
		}
		for (PropertySource<?> propertySource : propertySources) {
			sources.addLast(propertySource);
		}
	}

	/**
	 * Return true if the context contains any beans or property sources, false otherwise.
	 */
	protected boolean isParentContextRequired(SpringContext context) {
		if (!CollectionUtils.isEmpty(context.getBeanNames())) {
			return true;
		} else if (!CollectionUtils.isEmpty(context.getBeans())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Make sure all of the locations actually exist
	 */
	protected void validate(List<String> locations) {
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

	/**
	 * Format file names into fully qualified file system URL's
	 */
	protected List<String> getConvertedLocations(List<String> locations) {
		List<String> converted = new ArrayList<String>();
		for (String location : locations) {
			if (LocationUtils.isExistingFile(location)) {
				File file = new File(location);
				// ClassPathXmlApplicationContext needs a fully qualified URL, not a filename
				String url = LocationUtils.getCanonicalURLString(file);
				converted.add(url);
			} else {
				converted.add(location);
			}
		}
		return converted;
	}

}
