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
import org.kuali.common.util.spring.InjectionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class DefaultSpringService implements SpringService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringService.class);

	@Override
	public void load(InjectionContext context) {
		Assert.notNull(context);
		Assert.notNull(context.getLocations());

		// Make sure all of the locations exist
		validate(context.getLocations());

		// Convert file names to URL's
		List<String> locations = getConvertedLocations(context.getLocations());

		if (context.isInjectBeans()) {
			ApplicationContext parent = getApplicationContext(context, context.getBeanNames(), context.getBeans());
			logLocations(locations);
			new ClassPathXmlApplicationContext(CollectionUtils.toStringArray(locations), parent);
		} else {
			logLocations(locations);
			new ClassPathXmlApplicationContext(CollectionUtils.toStringArray(locations));
		}
	}

	/**
	 * Return an <code>ApplicationContext</code> with <code>beans</code> registered in the context under <code>beanNames</code>
	 */
	protected ApplicationContext getApplicationContext(InjectionContext context, List<String> beanNames, List<Object> beans) {
		Assert.isTrue(beanNames.size() == beans.size());
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.refresh();
		ConfigurableListableBeanFactory factory = applicationContext.getBeanFactory();
		for (int i = 0; i < beanNames.size(); i++) {
			String beanName = beanNames.get(i);
			Object bean = beans.get(i);
			factory.registerSingleton(beanName, bean);
		}
		return applicationContext;
	}

	protected ApplicationContext getApplicationContext(InjectionContext context, String beanName, Object bean) {
		return getApplicationContext(context, Collections.singletonList(beanName), Collections.singletonList(bean));
	}

	protected void logLocations(List<String> locations) {
		if (locations.size() == 1) {
			logger.info("Loading [{}]", locations.get(0));
		} else {
			logger.info("Loading {} context locations", locations.size());
		}
	}

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
