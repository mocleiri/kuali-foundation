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

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.PropertyLoadContext;
import org.kuali.common.util.property.PropertyStoreContext;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultPropertyService implements PropertyService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyService.class);

	@Override
	public Properties load(PropertyLoadContext context) {
		Properties properties = loadProperties(context);
		context.initialize(properties);
		process(properties, context.getProcessors());
		return properties;
	}

	@Override
	public void store(PropertyStoreContext context, Properties properties) {
		Properties duplicate = PropertyUtils.duplicate(properties);
		context.initialize(duplicate);
		process(duplicate, context.getProcessors());
		PropertyUtils.store(duplicate, context.getFile(), context.getEncoding(), context.getComment());
	}

	protected void process(Properties properties, List<PropertyProcessor> processors) {
		for (PropertyProcessor processor : CollectionUtils.toEmpty(processors)) {
			processor.process(properties);
		}
	}

	protected Properties loadProperties(PropertyLoadContext context) {
		List<String> locations = CollectionUtils.toEmpty(context.getLocations());
		logger.info("Examining " + locations.size() + " locations to load properties from");
		Properties properties = new Properties();
		int loadCount = 0;
		for (String location : locations) {
			String resolvedAndValidatedLocation = context.getLocation(location, properties);
			if (resolvedAndValidatedLocation != null) {
				Properties newProperties = PropertyUtils.load(resolvedAndValidatedLocation, context.getEncoding());
				properties.putAll(newProperties);
				loadCount++;
			}
		}
		int skipped = locations.size() - loadCount;
		logger.info("Loaded " + properties.size() + " properties from " + loadCount + " locations.  Skipped " + skipped + " locations");
		return properties;
	}
}
