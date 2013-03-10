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
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class DefaultSpringService implements SpringService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringService.class);

	@Override
	public void load(String location) {
		load(location, (String) null, (Object) null);
	}

	@Override
	public void load(String location, String beanName, Object bean) {
		load(location, CollectionUtils.toEmptyList(beanName), CollectionUtils.toEmptyList(bean));
	}

	@Override
	public void load(String location, List<String> beanNames, List<Object> beans) {
		load(CollectionUtils.toEmptyList(location), beanNames, beans);
	}

	@Override
	public void load(List<String> locations) {
		load(locations, (String) null, (Object) null);
	}

	@Override
	public void load(List<String> locations, String beanName, Object bean) {
		load(locations, CollectionUtils.toEmptyList(beanName), CollectionUtils.toEmptyList(bean));
	}

	@Override
	public void load(List<String> locations, List<String> beanNames, List<Object> beans) {
		// Make sure we have at least one location to load
		Assert.isTrue(locations.size() > 0);

		// Null-safe handling for non-required parameters
		beanNames = CollectionUtils.toEmptyList(beanNames);
		beans = CollectionUtils.toEmptyList(beans);

		// Make sure we have a name for every bean
		Assert.isTrue(beanNames.size() == beans.size());

		// Make sure all of the locations exist
		validate(locations);

		// Convert any file names to fully qualified file system URL's
		List<String> convertedLocations = getConvertedLocations(locations);

		// The Spring classes prefer array's
		String[] locationsArray = CollectionUtils.toStringArray(convertedLocations);

		AbstractApplicationContext parent = null;
		AbstractApplicationContext context = null;
		try {
			// Get a parent context with any bean's they've provided us pre-registered in the context
			// Parent is null if there are no beans to register
			parent = getApplicationContext(beanNames, beans);
			// Load the locations they provided us, optionally wrapped in a parent context containing pre-registered beans
			context = new ClassPathXmlApplicationContext(locationsArray, parent);
		} finally {
			// cleanup
			closeQuietly(context);
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
	 * Return an <code>AbstractApplicationContext</code> with <code>beans</code> registered in the context under <code>beanNames</code>
	 */
	protected AbstractApplicationContext getApplicationContext(List<String> beanNames, List<Object> beans) {
		if (CollectionUtils.isEmpty(beanNames) && CollectionUtils.isEmpty(beans)) {
			return null;
		}
		Assert.isTrue(beanNames.size() == beans.size());
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		context.refresh();
		ConfigurableListableBeanFactory factory = context.getBeanFactory();
		for (int i = 0; i < beanNames.size(); i++) {
			String beanName = beanNames.get(i);
			Object bean = beans.get(i);
			logger.info("Registering [{}]", beanName);
			factory.registerSingleton(beanName, bean);
		}
		return context;
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
