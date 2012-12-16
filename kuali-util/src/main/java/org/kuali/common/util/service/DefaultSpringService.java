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
import java.util.List;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.spring.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class DefaultSpringService implements SpringService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringService.class);

	@Override
	public void load(SpringContext context) {
		Assert.notNull(context);
		Assert.notNull(context.getLocations());

		ApplicationContext parent = null;
		if (context.isInjectProperties()) {
			parent = loadParent(context);
		}
		String[] locations = getLocations(context.getLocations());
		logLocations(locations);
		new ClassPathXmlApplicationContext(locations, parent);
	}

	protected ApplicationContext loadParent(SpringContext context) {
		String propertiesBeanName = context.getPropertiesBeanName();
		ClassPathXmlApplicationContext parent = new ClassPathXmlApplicationContext();
		parent.refresh();
		logger.info("Registering a properties object containing {} properties under the bean name [{}]", context.getProperties().size(), propertiesBeanName);
		parent.getBeanFactory().registerSingleton(propertiesBeanName, context.getProperties());
		return parent;
	}

	protected void logLocations(String[] locations) {
		if (locations.length == 1) {
			logger.info("Loading [{}]", locations[0]);
		} else {
			logger.info("Loading {} context locations", locations.length);
		}
	}

	protected String[] getLocations(List<String> locations) {
		for (int i = 0; i < locations.size(); i++) {
			String location = locations.get(i);
			if (!LocationUtils.exists(location)) {
				throw new IllegalArgumentException("Location [" + location + "] does not exist");
			}
			if (LocationUtils.isExistingFile(location)) {
				File file = new File(location);
				// Convert the raw filename to a fully qualified file system url
				locations.set(i, LocationUtils.getURLString(file));
			}
		}
		return locations.toArray(new String[locations.size()]);
	}

}
