package org.kuali.common.util.service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.spring.LoadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DefaultSpringService implements SpringService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringService.class);

	@Override
	public void load(LoadContext context) {
		logger.info("Context Location - " + context.getContextLocation());
		logger.info("Filter Context - " + context.isFilterContext());
		logger.info("Working Dir - " + LocationUtils.getCanonicalPath(context.getWorkingDir()));
		try {
			loadApplicationContext(context);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected error loading context", e);
		}
	}

	protected ApplicationContext loadApplicationContext(LoadContext context) throws IOException {
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
			File file = getFile(context);
			return getApplicationContext(file);
		}
	}

	protected File createFilteredContextFile(LoadContext context) throws IOException {
		String content = getFilteredContent(context);
		String filename = LocationUtils.getFilename(context.getContextLocation());
		File file = new File(context.getWorkingDir(), filename);
		logger.info("Creating [" + file.getCanonicalPath() + "]");
		FileUtils.write(file, content);
		return file;
	}

	protected File getFile(LoadContext context) throws IOException {
		if (context.isFilterContext()) {
			return createFilteredContextFile(context);
		} else {
			return new File(context.getContextLocation());
		}
	}

	protected ApplicationContext getApplicationContext(File file) {
		String url = LocationUtils.getURLString(file);
		return new FileSystemXmlApplicationContext(url);
	}

	protected String getFilteredContent(LoadContext context) {
		Properties duplicate = PropertyUtils.getProperties(context.getProperties(), context.getGlobalPropertiesMode());
		PropertyUtils.trim(duplicate, context.getFilterIncludes(), context.getFilterExcludes());
		String content = LocationUtils.toString(context.getContextLocation(), context.getEncoding());
		logger.info("Filtering [" + context.getContextLocation() + "] using " + duplicate.size() + " properties");
		return context.getHelper().replacePlaceholders(content, duplicate);
	}
}
