package org.kuali.common.util.service;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ResourceUtils;
import org.kuali.common.util.property.PropertyLoadContext;
import org.kuali.common.util.property.PropertyModifier;
import org.kuali.common.util.property.PropertyStoreContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultPropertyService implements PropertyService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyService.class);

	@Override
	public Properties load(PropertyLoadContext context) {
		Properties properties = loadProperties(context);
		context.initialize(properties);
		modify(properties, context.getModifiers());
		return properties;
	}

	@Override
	public void store(PropertyStoreContext context, Properties properties) {
		modify(properties, context.getModifiers());
		PropertyUtils.store(properties, context.getFile(), context.getEncoding(), context.getComment());
	}

	protected void modify(Properties properties, List<PropertyModifier> modifiers) {
		if (modifiers == null) {
			return;
		}
		for (PropertyModifier modifier : modifiers) {
			modifier.modify(properties);
		}
	}

	protected Properties loadProperties(PropertyLoadContext context) {
		Properties properties = new Properties();
		for (String location : context.getLocations()) {
			Properties global = PropertyUtils.getGlobalProperties(properties);
			String resolvedLocation = context.getHelper().replacePlaceholders(location, global);
			if (!location.equals(resolvedLocation)) {
				logger.info("Resolved location [{}] -> [{}]", location, resolvedLocation);
			}
			if (ResourceUtils.exists(resolvedLocation)) {
				Properties newProperties = PropertyUtils.load(resolvedLocation, context.getEncoding());
				properties.putAll(newProperties);
			} else {
				handleMissing(context.isIgnoreMissingLocations(), resolvedLocation);
			}
		}
		return properties;
	}

	protected void handleMissing(boolean ignoreMissing, String location) {
		if (ignoreMissing) {
			logger.info("Ignoring non-existent location - [{}]", location);
		} else {
			throw new IllegalArgumentException("Could not locate [" + location + "]");
		}
	}
}
