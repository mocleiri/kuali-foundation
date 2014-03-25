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
		logger.info("Working with " + properties.size() + " properties total.");
		context.initialize(properties);
		List<PropertyProcessor> processors = CollectionUtils.toEmptyList(context.getProcessors());
		logger.info("Processing " + properties.size() + " properties using " + processors.size() + " processors.");
		PropertyUtils.process(properties, context.getProcessors());
		logger.info("Returning " + properties.size() + " properties.");
		if (logger.isDebugEnabled()) {
			logger.debug(PropertyUtils.toString(properties));
		}
		return properties;
	}

	@Override
	public void store(PropertyStoreContext context, Properties properties) {
		Properties duplicate = PropertyUtils.duplicate(properties);
		context.initialize(duplicate);
		PropertyUtils.process(duplicate, context.getProcessors());
		PropertyUtils.store(duplicate, context.getFile(), context.getEncoding(), context.getComment());
	}

	protected Properties loadProperties(PropertyLoadContext context) {
		Properties properties = context.init();
		int initialSize = properties.size();
		List<String> locations = CollectionUtils.toEmptyList(context.getLocations());
		logger.info("Examining " + locations.size() + " property locations.");
		int count = 0;
		for (String location : locations) {
			String resolvedAndValidatedLocation = context.getLocation(location, properties);
			if (resolvedAndValidatedLocation != null) {
				Properties newProperties = PropertyUtils.load(resolvedAndValidatedLocation, context.getEncoding());
				properties.putAll(newProperties);
				count++;
			}
		}
		int newSize = properties.size();
		int skipped = locations.size() - count;
		logger.info("Loaded " + (newSize - initialSize) + " properties from " + count + " locations.  Skipped " + skipped + " locations.");
		return properties;
	}
}
