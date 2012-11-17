package org.kuali.common.util.service;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ResourceUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.PropertyContext;
import org.kuali.common.util.property.PropertyEncMode;
import org.kuali.common.util.property.PropertyEncryptor;
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
		Properties properties = new Properties();
		for (String location : context.getLocations()) {
			Properties global = PropertyUtils.getGlobalProperties(properties);
			String resolvedLocation = context.getHelper().replacePlaceholders(location, global);
			if (!location.equals(resolvedLocation)) {
				logger.info("Resolved location [{}] -> [{}]", location, resolvedLocation);
			}
			boolean missing = !ResourceUtils.exists(resolvedLocation);
			if (missing && context.isIgnoreMissingLocations()) {
				logger.info("Skipping non-existent location - [{}]", resolvedLocation);
				continue;
			}
			properties.putAll(PropertyUtils.load(resolvedLocation, context.getEncoding()));
		}
		return getProperties(context, properties);
	}

	protected Properties getProperties(PropertyContext context, Properties props) {

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

		// Encrypt / decrypt as necessary
		handleEncryption(context, props);

		// Trim out unwanted properties
		PropertyUtils.trim(props, context.getIncludes(), context.getExcludes());

		// Add a prefix if asked to do so
		Properties prefixed = PropertyUtils.getPrefixedProperties(props, context.getPrefix());

		// Format the property keys according to the style they've asked for and return
		return getFormattedProperties(prefixed, context.getStyle());
	}

	protected String getResolvedPassword(PropertyContext context, Properties props) {
		Properties globalProperties = PropertyUtils.getGlobalProperties(props);
		String originalPassword = context.getEncryptionPassword();
		String resolvedPassword = context.getHelper().replacePlaceholders(originalPassword, globalProperties);
		if (!resolvedPassword.equals(originalPassword)) {
			logger.info("Resolved encryption password");
		}
		return resolvedPassword;
	}

	protected boolean isEncryptOrDecrypt(PropertyEncMode mode) {
		switch (mode) {
		case NONE:
			return false;
		case DECRYPT:
		case ENCRYPT:
			return true;
		default:
			throw new IllegalArgumentException(mode + " is unknown");
		}
	}

	protected void handleEncryption(PropertyContext context, Properties props) {
		if (!isEncryptOrDecrypt(context.getEncryptionMode())) {
			return;
		}

		PropertyEncryptor encryptor = context.getEncryptor();
		String password = getResolvedPassword(context, props);
		// Give the encryptor a chance to initialize itself
		encryptor.initialize(context.getEncryptionStrength(), password);

		switch (context.getEncryptionMode()) {
		case DECRYPT:
			encryptor.decrypt(props);
			return;
		case ENCRYPT:
			encryptor.encrypt(props);
			return;
		default:
			throw new IllegalStateException(context.getEncryptionMode() + " must be either " + PropertyEncMode.DECRYPT + " or " + PropertyEncMode.ENCRYPT);
		}
	}
}
