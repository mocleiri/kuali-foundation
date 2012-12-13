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
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.processor.GlobalOverrideProcessor;
import org.kuali.common.util.spring.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.util.Assert;

public class DefaultSpringService implements SpringService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringService.class);

	@Override
	public void load(SpringContext context) {
		Assert.notNull(context, "context is null");
		Assert.notNull(context.getContextLocation(), "context location is null");
		logger.info("Context Location - {}", context.getContextLocation());
		logger.info("Filter Context - {}", context.isFilterContext());
		logger.info("Export Properties - {}", context.isExportProperties());
		if (context.isFilterContext()) {
			logger.info("Working Dir - {}", LocationUtils.getCanonicalPath(context.getWorkingDir()));
		}
		try {
			Properties properties = getProperties(context);
			doExport(context, properties);
			doLoad(context, properties);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected error loading context", e);
		}
	}

	protected void doExport(SpringContext context, Properties properties) {
		if (!context.isExportProperties()) {
			return;
		}
		Properties duplicate = PropertyUtils.duplicate(properties);
		PropertyUtils.trim(duplicate, context.getExportIncludes(), context.getExportExcludes());
		PropertyUtils.store(duplicate, context.getExportPropertiesFile());
	}

	protected Properties getProperties(SpringContext context) {
		Properties properties = PropertyUtils.combine(CollectionUtils.toEmpty(context.getPropertySources()));
		if (context.isExportProperties()) {
			Assert.notNull(context.getExportPropertiesFile(), "export properties file is null");
			String value = LocationUtils.getURLString(context.getExportPropertiesFile());
			String name = context.getExportPropertiesFileProperty();
			properties.setProperty(name, value);
		}
		PropertyUtils.process(properties, new GlobalOverrideProcessor(context.getGlobalPropertiesMode()));
		return properties;
	}

	protected ApplicationContext doLoad(SpringContext context, Properties properties) throws IOException {
		boolean exists = LocationUtils.exists(context.getContextLocation());
		if (!exists) {
			throw new IllegalArgumentException(context.getContextLocation() + " does not exist");
		}

		boolean filter = context.isFilterContext();
		boolean isFile = LocationUtils.isExistingFile(context.getContextLocation());
		boolean loadFromClasspath = !filter && !isFile;
		if (loadFromClasspath) {
			return new ClassPathXmlApplicationContext(context.getContextLocation());
		} else {
			File file = getFile(context, properties);
			return getApplicationContext(file);
		}
	}

	protected File getNewFile(SpringContext context) {
		String filename = LocationUtils.getFilename(context.getContextLocation());
		File file = new File(context.getWorkingDir(), filename);
		return file;
	}

	protected File createFilteredContextFile(SpringContext context, Properties properties) throws IOException {
		String content = getFilteredContent(context, properties);
		File file = getNewFile(context);
		logger.info("Creating [" + file.getCanonicalPath() + "]");
		FileUtils.write(file, content);
		return file;
	}

	protected File getFile(SpringContext context, Properties properties) throws IOException {
		if (context.isFilterContext()) {
			return createFilteredContextFile(context, properties);
		} else {
			return new File(context.getContextLocation());
		}
	}

	protected ApplicationContext getApplicationContext(File file) {
		String url = LocationUtils.getURLString(file);
		return new FileSystemXmlApplicationContext(url);
	}

	protected String getFilteredContent(SpringContext context, Properties properties) {
		Properties duplicate = PropertyUtils.duplicate(properties);
		PropertyUtils.trim(duplicate, context.getFilterIncludes(), context.getFilterExcludes());
		String content = LocationUtils.toString(context.getContextLocation(), context.getEncoding());
		logger.info("Filtering [" + context.getContextLocation() + "] using " + duplicate.size() + " properties");
		return context.getHelper().replacePlaceholders(content, duplicate);
	}
}
