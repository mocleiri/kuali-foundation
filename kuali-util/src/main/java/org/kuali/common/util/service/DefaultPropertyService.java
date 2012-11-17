package org.kuali.common.util.service;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ResourceUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.PropertyContext;
import org.kuali.common.util.property.PropertyLoadContext;
import org.kuali.common.util.property.PropertyStoreContext;
import org.kuali.common.util.property.PropertyStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultPropertyService implements PropertyService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyService.class);

	protected Properties getFormattedProperties(Properties properties, PropertyStyle style) {
		switch (style) {
		case NORMAL:
			return properties;
		case ENVIRONMENT_VARIABLE:
			return PropertyUtils.getPropertiesAsEnvironmentVariables(properties);
		default:
			throw new IllegalArgumentException(style + " is unknown");
		}
	}

	protected Properties getResolvedProperties(Properties props, PropertyPlaceholderHelper helper) {
		Properties global = PropertyUtils.getGlobalProperties(props);
		List<String> keys = PropertyUtils.getSortedKeys(props);
		Properties newProps = new Properties();
		for (String key : keys) {
			String originalValue = props.getProperty(key);
			String resolvedValue = helper.replacePlaceholders(originalValue, global);
			if (!resolvedValue.equals(originalValue)) {
				logger.debug("Resolved property '" + key + "' [{}] -> [{}]", Str.flatten(originalValue), Str.flatten(resolvedValue));
			}
			newProps.setProperty(key, resolvedValue);
		}
		return newProps;
	}

	@Override
	public void store(PropertyStoreContext context, Properties properties) {
		Properties finalProperties = getProperties(context, properties);
		PropertyUtils.store(finalProperties, context.getFile(), context.getEncoding(), context.getComment());
	}

	@Override
	public Properties load(PropertyLoadContext context) {
		Properties props = new Properties();
		for (String location : context.getLocations()) {
			Properties global = PropertyUtils.getGlobalProperties(props);
			String resolvedLocation = context.getHelper().replacePlaceholders(location, global);
			if (!location.equals(resolvedLocation)) {
				logger.info("Resolved location [{}] -> [{}]", location, resolvedLocation);
			}
			boolean missing = !ResourceUtils.exists(resolvedLocation);
			if (missing && context.isIgnoreMissingLocations()) {
				logger.info("Skipping non-existent location - [{}]", resolvedLocation);
				continue;
			} else {
				props.putAll(PropertyUtils.load(resolvedLocation, context.getEncoding()));
			}
		}
		return props;
	}

	public Properties getProperties(PropertyLoadContext context) {
		// Load properties in from the specified locations
		Properties props = load(context);
		// Process the properties according to the options provided in the context
		return getProperties(context, props);
	}

	public Properties getProperties(PropertyContext context, Properties props) {

		// Add in environment variables?
		if (context.isIncludeEnvironmentVariables()) {
			props.putAll(PropertyUtils.getEnvAsProperties());
		}

		// Add in system properties?
		if (context.isIncludeSystemProperties()) {
			props.putAll(System.getProperties());
		}

		// Resolve placeholders?
		if (context.isResolvePlaceholders()) {
			props = getResolvedProperties(props, context.getHelper());
		}

		// Trim out unwanted properties
		PropertyUtils.trim(props, context.getIncludes(), context.getExcludes());

		// Add a prefix if asked to do so
		Properties prefixed = PropertyUtils.getPrefixedProperties(props, context.getPrefix());

		// Format the property keys according to the style they've asked for and return
		return getFormattedProperties(prefixed, context.getStyle());
	}

}
