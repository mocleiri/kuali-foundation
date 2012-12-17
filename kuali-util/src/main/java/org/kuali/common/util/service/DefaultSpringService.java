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
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class DefaultSpringService implements SpringService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringService.class);

	@Override
	public void load(String location) {
		load(location, Collections.<String> emptyList(), Collections.<Object> emptyList());
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
		load(locations, Collections.<String> emptyList(), Collections.<Object> emptyList());
	}

	@Override
	public void load(List<String> locations, String beanName, Object bean) {
		load(locations, CollectionUtils.toEmptyList(beanName), CollectionUtils.toEmptyList(bean));
	}

	@Override
	public void load(List<String> locations, List<String> beanNames, List<Object> beans) {
		Assert.isTrue(locations.size() > 0);
		beanNames = CollectionUtils.toEmpty(beanNames);
		beans = CollectionUtils.toEmpty(beans);
		Assert.isTrue(beanNames.size() == beans.size());

		// Make sure all of the locations exist
		validate(locations);

		// Convert any file names to fully qualified file system URL's
		List<String> convertedLocations = getConvertedLocations(locations);

		// May need to pre-register some beans
		if (beanNames.size() > 0) {
			logger.debug("Registering {} beans", beanNames.size());
			// Get a parent context with the bean's they've provided us pre-registered in the context
			ApplicationContext parent = getApplicationContext(beanNames, beans);
			// Load the locations they provided us, wrapped in a parent context containing the pre-registered beans
			new ClassPathXmlApplicationContext(CollectionUtils.toStringArray(convertedLocations), parent);
		} else {
			// Load the locations they provided us
			new ClassPathXmlApplicationContext(CollectionUtils.toStringArray(convertedLocations));
		}
	}

	/**
	 * Return an <code>ApplicationContext</code> with <code>beans</code> registered in the context under <code>beanNames</code>
	 */
	protected ApplicationContext getApplicationContext(List<String> beanNames, List<Object> beans) {
		Assert.isTrue(beanNames.size() == beans.size());
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.refresh();
		ConfigurableListableBeanFactory factory = applicationContext.getBeanFactory();
		for (int i = 0; i < beanNames.size(); i++) {
			String beanName = beanNames.get(i);
			Object bean = beans.get(i);
			logger.info("Registering [{}]", beanName);
			factory.registerSingleton(beanName, bean);
		}
		return applicationContext;
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
