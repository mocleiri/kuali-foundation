package org.kuali.common.util.service;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.PropertyLoadContext;
import org.kuali.common.util.property.PropertyStoreContext;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

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
		if (processors == null) {
			return;
		}
		for (PropertyProcessor processor : processors) {
			processor.process(properties);
		}
	}

	protected Properties loadProperties(PropertyLoadContext context) {
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(Constants.DEFAULT_PLACEHOLDER_PREFIX, Constants.DEFAULT_PLACEHOLDER_SUFFIX);
		context.initializeLoadProcessors();
		Properties properties = new Properties();
		for (String location : context.getLocations()) {
			Properties duplicate = PropertyUtils.duplicate(properties);
			process(duplicate, context.getLoadProcessors());
			String resolvedLocation = helper.replacePlaceholders(location, duplicate);
			if (!location.equals(resolvedLocation)) {
				logger.debug("Resolved location [{}] -> [{}]", location, resolvedLocation);
			}
			if (LocationUtils.exists(resolvedLocation)) {
				Properties newProperties = PropertyUtils.load(resolvedLocation, context.getEncoding());
				properties.putAll(newProperties);
			} else {
				ModeUtils.validate(context.getMissingLocationsMode(), "Skipping non-existent location - [{}]", resolvedLocation, "Could not locate [" + resolvedLocation + "]");
			}
		}
		return properties;
	}
}
