package org.kuali.common.util.service;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ResourceUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.PropertyLoadContext;
import org.kuali.common.util.property.PropertyStoreContext;
import org.kuali.common.util.property.modifier.PropertyModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultPropertyService implements PropertyService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyService.class);

	@Override
	public Properties load(PropertyLoadContext context) {
		Properties properties = loadProperties(context);
		context.beforeModify(properties);
		modify(properties, context.getModifiers());
		return properties;
	}

	@Override
	public void store(PropertyStoreContext context, Properties properties) {
		Properties duplicate = PropertyUtils.duplicate(properties);
		context.beforeModify(duplicate);
		modify(duplicate, context.getModifiers());
		PropertyUtils.store(duplicate, context.getFile(), context.getEncoding(), context.getComment());
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
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(Constants.DEFAULT_PLACEHOLDER_PREFIX, Constants.DEFAULT_PLACEHOLDER_SUFFIX);
		context.beforeLoad();
		Properties properties = new Properties();
		for (String location : context.getLocations()) {
			Properties duplicate = PropertyUtils.duplicate(properties);
			modify(duplicate, context.getLoadModifiers());
			String resolvedLocation = helper.replacePlaceholders(location, duplicate);
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
