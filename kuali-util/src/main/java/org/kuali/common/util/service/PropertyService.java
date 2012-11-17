package org.kuali.common.util.service;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ResourceUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.DefaultPropertyLoadingContext;
import org.kuali.common.util.property.DefaultPropertyStorageContext;
import org.kuali.common.util.property.PropertyContext;
import org.kuali.common.util.property.PropertyLoadContext;
import org.kuali.common.util.property.PropertyStoreContext;
import org.kuali.common.util.property.PropertyStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertyService {

	private static final Logger logger = LoggerFactory.getLogger(PropertyService.class);

	public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
	public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

	public Properties getResolvedProperties(Properties props) {
		return getResolvedProperties(props, DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX);
	}

	public Properties getFormattedProperties(Properties properties, PropertyStyle style) {
		switch (style) {
		case NORMAL:
			return properties;
		case ENVIRONMENT_VARIABLE:
			return PropertyUtils.getPropertiesAsEnvironmentVariables(properties);
		default:
			throw new IllegalArgumentException(style + " is unknown");
		}
	}

	public Properties getResolvedProperties(Properties props, String placeHolderPrefix, String placeHolderSuffix) {
		Properties global = PropertyUtils.getGlobalProperties(props);
		PropertyPlaceholderHelper pph = new PropertyPlaceholderHelper(placeHolderPrefix, placeHolderSuffix);
		List<String> keys = PropertyUtils.getSortedKeys(props);
		Properties newProps = new Properties();
		for (String key : keys) {
			String originalValue = props.getProperty(key);
			String resolvedValue = pph.replacePlaceholders(originalValue, global);
			if (!resolvedValue.equals(originalValue)) {
				logger.debug("Resolved property '" + key + "' [{}] -> [{}]", Str.flatten(originalValue), Str.flatten(resolvedValue));
			}
			newProps.setProperty(key, resolvedValue);
		}
		return newProps;
	}

	public String getResolvedValue(String value, Properties properties) {
		return getResolvedValue(value, properties, DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX);
	}

	public String getResolvedValue(String value, Properties properties, String placeHolderPrefix, String placeHolderSuffix) {
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(placeHolderPrefix, placeHolderSuffix);
		return helper.replacePlaceholders(value, properties);
	}

	public Properties getProperties(List<String> locations) {
		return getProperties(locations, null);
	}

	public Properties getProperties(List<String> locations, String encoding) {
		DefaultPropertyLoadingContext context = new DefaultPropertyLoadingContext();
		context.setLocations(locations);
		context.setEncoding(encoding);
		return getProperties(context);
	}

	public void store(Properties properties, File file, String encoding, PropertyStyle style) {
		store(properties, file, encoding, null, style, null);
	}

	public void store(Properties properties, File file, String encoding, String prefix, PropertyStyle style, String comment) {
		DefaultPropertyStorageContext context = new DefaultPropertyStorageContext();
		context.setFile(file);
		context.setEncoding(encoding);
		context.setPrefix(prefix);
		context.setStyle(style);
		context.setComment(comment);
		store(context, properties);
	}

	public void store(PropertyStoreContext context, Properties properties) {
		Properties finalProperties = getProperties(context, properties);
		PropertyUtils.store(finalProperties, context.getFile(), context.getEncoding(), context.getComment());
	}

	public Properties load(PropertyLoadContext context) {
		String prefix = context.getPlaceHolderPrefix();
		String suffix = context.getPlaceHolderSuffix();
		Properties props = new Properties();
		for (String location : context.getLocations()) {
			Properties global = PropertyUtils.getGlobalProperties(props);
			String resolvedLocation = getResolvedValue(location, global, prefix, suffix);
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
			props = getResolvedProperties(props, context.getPlaceHolderPrefix(), context.getPlaceHolderSuffix());
		}

		// Trim out unwanted properties
		PropertyUtils.trim(props, context.getIncludes(), context.getExcludes());

		// Add in a prefix if asked to do so
		Properties prefixed = PropertyUtils.getPrefixedProperties(props, context.getPrefix());

		// Format the property keys according to the style they've asked for and return
		return getFormattedProperties(prefixed, context.getStyle());
	}

	public Properties getProperties(String location) {
		return getProperties(location, null);
	}

	public Properties getProperties(String location, String encoding) {
		return getProperties(Collections.singletonList(location), encoding);
	}

}
