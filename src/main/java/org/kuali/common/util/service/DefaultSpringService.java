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
package org.kuali.common.util.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * @deprecated
 */
@Deprecated
public class DefaultSpringService implements SpringService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringService.class);

	@Override
	public void load(Class<?> annotatedClass) {
		load(annotatedClass, null, null);
	}

	@Override
	public void load(Class<?> annotatedClass, String beanName, Object bean, PropertySource<?> propertySource) {
		// Make sure the annotatedClass isn't null
		Assert.notNull(annotatedClass, "annotatedClass is null");

		// Setup a SpringContext
		SpringContext context = new SpringContext();
		context.setAnnotatedClasses(CollectionUtils.asList(annotatedClass));
		context.setPropertySourceContext(new PropertySourceContext(SpringUtils.asList(propertySource)));

		// Null safe handling for non-required parameters
		context.setBeanNames(CollectionUtils.toEmptyList(beanName));
		context.setBeans(CollectionUtils.toEmptyList(bean));

		// Load the configuration from the annotated class
		load(context);
	}

	@Override
	public void load(Class<?> annotatedClass, String beanName, Object bean) {
		load(annotatedClass, beanName, bean, null);
	}

	@Override
	public void load(String location) {
		load(location, null, null);
	}

	@Override
	public void load(String location, String beanName, Object bean, PropertySource<?> propertySource) {
		// Make sure the location isn't empty
		Assert.hasText(location, "location is null");

		// Setup a SpringContext
		SpringContext context = new SpringContext();
		context.setLocations(Arrays.asList(location));
		context.setPropertySourceContext(new PropertySourceContext(SpringUtils.asList(propertySource)));

		// Null safe handling for non-required parameters
		context.setBeanNames(CollectionUtils.toEmptyList(beanName));
		context.setBeans(CollectionUtils.toEmptyList(bean));

		// Load the location using a SpringContext
		load(context);
	}

	@Override
	public void load(String location, String beanName, Object bean) {
		load(location, beanName, bean, null);
	}

	@Override
	public void load(SpringContext context) {

		// Null-safe handling for parameters
		context.setBeanNames(CollectionUtils.toEmptyList(context.getBeanNames()));
		context.setBeans(CollectionUtils.toEmptyList(context.getBeans()));
		context.setAnnotatedClasses(CollectionUtils.toEmptyList(context.getAnnotatedClasses()));
		context.setLocations(CollectionUtils.toEmptyList(context.getLocations()));

		// Make sure we have at least one location or annotated class
		boolean empty = CollectionUtils.isEmpty(context.getLocations()) && CollectionUtils.isEmpty(context.getAnnotatedClasses());
		Assert.isFalse(empty, "Both locations and annotatedClasses are empty");

		// Make sure we have a name for every bean
		Assert.isTrue(context.getBeanNames().size() == context.getBeans().size());

		// Make sure all of the locations exist
		SpringUtils.validateExists(context.getLocations());

		// Convert any file names to fully qualified file system URL's
		List<String> convertedLocations = getConvertedLocations(context.getLocations());

		// The Spring classes prefer array's
		String[] locationsArray = CollectionUtils.toStringArray(convertedLocations);

		ConfigurableApplicationContext parent = null;
		ClassPathXmlApplicationContext xmlChild = null;
		AnnotationConfigApplicationContext annotationChild = null;
		try {
			if (isParentContextRequired(context)) {
				// Construct a parent context if necessary
				parent = SpringUtils.getContextWithPreRegisteredBeans(context.getId(), context.getDisplayName(), context.getBeanNames(), context.getBeans());
			}

			if (!CollectionUtils.isEmpty(context.getAnnotatedClasses())) {
				// Create an annotation based application context wrapped in a parent context
				annotationChild = getAnnotationContext(context, parent);
				// Add custom property sources (if any)
				addPropertySources(context, annotationChild);

			}

			if (!CollectionUtils.isEmpty(context.getLocations())) {
				// Create an XML application context wrapped in a parent context
				xmlChild = new ClassPathXmlApplicationContext(locationsArray, false, parent);
				if (parent == null) {
					addMetaInfo(xmlChild, context);
				}
				// Add custom property sources (if any)
				addPropertySources(context, xmlChild);
			}

			// Invoke refresh to load the context
			SpringUtils.refreshQuietly(annotationChild);
			SpringUtils.refreshQuietly(xmlChild);
			debugQuietly(parent, annotationChild, xmlChild);
		} finally {
			// cleanup
			// closeQuietly(annotationChild);
			// closeQuietly(xmlChild);
			// closeQuietly(parent);
		}
	}

	protected void debugQuietly(ApplicationContext parent, ApplicationContext child1, ApplicationContext child2) {
		if (!logger.isDebugEnabled()) {
			return;
		}
		if (parent != null) {
			SpringUtils.debug(parent);
		} else {
			if (child1 != null) {
				SpringUtils.debug(child1);
			}
			if (child2 != null) {
				SpringUtils.debug(child2);
			}
		}
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

	protected AnnotationConfigApplicationContext getAnnotationContext(SpringContext context, ConfigurableApplicationContext parent) {
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

	protected void addPropertySources(SpringContext context, ConfigurableApplicationContext applicationContext) {
		PropertySourceContext psc = context.getPropertySourceContext();
		ConfigurableEnvironment env = applicationContext.getEnvironment();
		if (psc.isRemoveExistingSources()) {
			logger.debug("Removing all existing property sources");
			SpringUtils.removeAllPropertySources(env);
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
	 * Return true if the context contains any beans or beanNames, false otherwise.
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

}
