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
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.spring.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class DefaultSpringService implements SpringService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringService.class);

	@Override
	public void load(SpringContext context) {
		Assert.notNull(context);
		Assert.notNull(context.getLocations());
		Assert.notNull(context.getProperties());
		Assert.notNull(context.getPropertiesBeanName());

		Properties duplicate = PropertyUtils.duplicate(context.getProperties());
		PropertyUtils.trim(duplicate, context.getIncludes(), context.getExcludes());
		String propertiesBeanName = context.getPropertiesBeanName();

		logger.info("Registring {} properties under the bean name [{}]", duplicate.size(), propertiesBeanName);

		ClassPathXmlApplicationContext parent = new ClassPathXmlApplicationContext();
		parent.refresh();
		parent.getBeanFactory().registerSingleton(propertiesBeanName, duplicate);

		String[] locations = getLocations(context.getLocations());

		if (locations.length == 1) {
			logger.info("Loading [{}]", locations[0]);
		} else {
			logger.info("Loading {} context locations", locations.length);
		}
		new ClassPathXmlApplicationContext(locations, parent);
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
