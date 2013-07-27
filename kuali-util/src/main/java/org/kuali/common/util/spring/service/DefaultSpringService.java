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
package org.kuali.common.util.spring.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * This service must be stateless
 */
public class DefaultSpringService implements SpringService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringService.class);

	@Override
	public void load(Class<?> annotatedClass, Map<String, Object> contextBeans) {
		load(annotatedClass, contextBeans, null);
	}

	@Override
	public void load(Class<?> annotatedClass, Map<String, Object> contextBeans, PropertySource<?> propertySource) {
		// Make sure the annotatedClass isn't null
		Assert.notNull(annotatedClass, "annotatedClass is null");

		// Setup a SpringContext
		SpringContext context = new SpringContext();
		context.setAnnotatedClasses(CollectionUtils.asList(annotatedClass));
		context.setPropertySourceContext(new PropertySourceContext(PropertySourceUtils.asList(propertySource)));

		// Null safe handling for non-required parameters
		context.setContextBeans(CollectionUtils.toEmptyMap(contextBeans));

		// Load the context
		load(context);
	}

	@Override
	public void load(String location, Map<String, Object> contextBeans) {
		load(location, contextBeans, null);
	}

	@SuppressWarnings("deprecation")
	protected Map<String, Object> getContextBeans(SpringContext context) {
		// Null-safe handling for parameters
		Map<String, Object> contextBeans = CollectionUtils.toModifiableEmptyMap(context.getContextBeans());
		CollectionUtils.combine(contextBeans, context.getBeanNames(), context.getBeans());
		return contextBeans;
	}

	protected AbstractApplicationContext getXmlChild(String[] locationsArray, AbstractApplicationContext parent, SpringContext context) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(locationsArray, false, parent);
		if (parent == null) {
			addMetaInfo(ctx, context);
		}
		return ctx;
	}

	@Override
	public ConfigurableApplicationContext getApplicationContext(SpringContext context) {
		// Null-safe handling for optional parameters
		context.setContextBeans(getContextBeans(context));
		context.setAnnotatedClasses(CollectionUtils.toEmptyList(context.getAnnotatedClasses()));
		context.setLocations(CollectionUtils.toEmptyList(context.getLocations()));
		context.setActiveProfiles(CollectionUtils.toEmptyList(context.getActiveProfiles()));
		context.setDefaultProfiles(CollectionUtils.toEmptyList(context.getDefaultProfiles()));

		// Make sure we have at least one location or annotated class
		boolean empty = CollectionUtils.isEmpty(context.getLocations()) && CollectionUtils.isEmpty(context.getAnnotatedClasses());
		Assert.isFalse(empty, "Both locations and annotatedClasses are empty");

		// Make sure all of the locations exist
		LocationUtils.validateExists(context.getLocations());

		// Convert any file names to fully qualified file system URL's
		List<String> convertedLocations = getConvertedLocations(context.getLocations());

		// The Spring classes prefer array's
		String[] locationsArray = CollectionUtils.toStringArray(convertedLocations);

		AbstractApplicationContext parent = null;

		// Construct a parent context if necessary
		if (!CollectionUtils.isEmpty(context.getContextBeans())) {
			parent = SpringUtils.getContextWithPreRegisteredBeans(context.getId(), context.getDisplayName(), context.getContextBeans());
		}

		AbstractApplicationContext child = null;
		if (!CollectionUtils.isEmpty(context.getAnnotatedClasses())) {
			child = getAnnotationContext(context, parent);
		} else if (!CollectionUtils.isEmpty(context.getLocations())) {
			child = getXmlChild(locationsArray, parent, context);
		} else {
			throw new IllegalStateException("Must provide either annotated classes or locations");
		}

		// Add custom property sources (if any)
		addPropertySources(context, child);

		// Set default profiles (if any)
		setDefaultProfiles(child, context.getDefaultProfiles());

		// Set active profiles (if any)
		setActiveProfiles(child, context.getActiveProfiles());

		// Return the fully configured context
		return child;
	}

	@Override
	public void load(SpringContext context) {
		ConfigurableApplicationContext ctx = getApplicationContext(context);
		try {
			ctx.refresh();
			SpringUtils.debugQuietly(ctx);
		} finally {
			SpringUtils.closeQuietly(ctx);
		}
	}

	@Override
	public void load(String location, Map<String, Object> contextBeans, PropertySource<?> propertySource) {
		// Make sure the location isn't empty
		Assert.hasText(location, "location is null");

		// Setup a SpringContext
		SpringContext context = new SpringContext();
		context.setLocations(Arrays.asList(location));
		context.setPropertySourceContext(new PropertySourceContext(PropertySourceUtils.asList(propertySource)));

		// Null safe handling for non-required parameters
		context.setContextBeans(CollectionUtils.toEmptyMap(contextBeans));

		// Load the location using a SpringContext
		load(context);
	}

	@Override
	public void load(Class<?> annotatedClass) {
		load(annotatedClass, (String) null, (Object) null);
	}

	@Override
	public void load(String location) {
		load(location, (String) null, (Object) null);
	}

	/**
	 * Add id and display name to the ApplicationContext if they are not blank
	 */
	protected void addMetaInfo(AnnotationConfigApplicationContext ctx, SpringContext sc) {
		if (!StringUtils.isBlank(sc.getId())) {
			ctx.setId(sc.getId());
		}
		if (!StringUtils.isBlank(sc.getDisplayName())) {
			ctx.setDisplayName(sc.getDisplayName());
		}
	}

	/**
	 * Add id and display name to the ApplicationContext if they are not blank
	 */
	protected void addMetaInfo(ClassPathXmlApplicationContext ctx, SpringContext sc) {
		if (!StringUtils.isBlank(sc.getId())) {
			ctx.setId(sc.getId());
		}
		if (!StringUtils.isBlank(sc.getDisplayName())) {
			ctx.setDisplayName(sc.getDisplayName());
		}
	}

	protected AbstractApplicationContext getAnnotationContext(SpringContext context, ConfigurableApplicationContext parent) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		if (parent != null) {
			ctx.setParent(parent);
		} else {
			addMetaInfo(ctx, context);
		}
		for (Class<?> annotatedClass : context.getAnnotatedClasses()) {
			ctx.register(annotatedClass);
		}
		return ctx;
	}

	protected void setActiveProfiles(ConfigurableApplicationContext applicationContext, List<String> activeProfiles) {
		if (!CollectionUtils.isEmpty(activeProfiles)) {
			ConfigurableEnvironment env = applicationContext.getEnvironment();
			env.setActiveProfiles(CollectionUtils.toStringArray(activeProfiles));
		}
	}

	protected void setDefaultProfiles(ConfigurableApplicationContext applicationContext, List<String> defaultProfiles) {
		if (!CollectionUtils.isEmpty(defaultProfiles)) {
			ConfigurableEnvironment env = applicationContext.getEnvironment();
			env.setDefaultProfiles(CollectionUtils.toStringArray(defaultProfiles));
		}
	}

	protected void addPropertySources(SpringContext context, ConfigurableApplicationContext applicationContext) {
		PropertySourceContext psc = context.getPropertySourceContext();
		if (psc == null) {
			return;
		}
		ConfigurableEnvironment env = applicationContext.getEnvironment();
		if (psc.isRemoveExistingSources()) {
			logger.debug("Removing all existing property sources");
			PropertySourceUtils.removeAllPropertySources(env);
		}

		if (CollectionUtils.isEmpty(psc.getSources())) {
			return;
		}
		List<PropertySource<?>> propertySources = psc.getSources();
		MutablePropertySources sources = env.getPropertySources();
		if (psc.isLastOneInWins()) {
			Collections.reverse(propertySources);
		}
		PropertySourceAddPriority priority = psc.getPriority();
		for (PropertySource<?> propertySource : propertySources) {
			Object[] args = { propertySource.getName(), propertySource.getClass().getName(), priority };
			logger.debug("Adding property source - [{}] -> [{}] Priority=[{}]", args);
			switch (priority) {
			case FIRST:
				sources.addFirst(propertySource);
				break;
			case LAST:
				sources.addLast(propertySource);
				break;
			default:
				throw new IllegalStateException(priority + " is an unknown priority");
			}
		}
	}

	/**
	 * Convert any locations representing an existing file into a fully qualified file system url. Leave any locations that do not resolve to an existing file alone.
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

	@Deprecated
	@Override
	public void load(Class<?> annotatedClass, String beanName, Object bean, PropertySource<?> propertySource) {
		load(annotatedClass, CollectionUtils.toEmptyMap(beanName, bean), propertySource);
	}

	@Deprecated
	@Override
	public void load(Class<?> annotatedClass, String beanName, Object bean) {
		load(annotatedClass, beanName, bean, null);
	}

	@Deprecated
	@Override
	public void load(String location, String beanName, Object bean, PropertySource<?> propertySource) {
		load(location, CollectionUtils.toEmptyMap(beanName, bean), propertySource);
	}

	@Deprecated
	@Override
	public void load(String location, String beanName, Object bean) {
		load(location, beanName, bean, null);
	}

}
