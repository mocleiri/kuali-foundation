package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class ResolvePlaceholdersProcessor implements PropertyProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ResolvePlaceholdersProcessor.class);

	public ResolvePlaceholdersProcessor() {
		this(new PropertyPlaceholderHelper(Constants.DEFAULT_PLACEHOLDER_PREFIX, Constants.DEFAULT_PLACEHOLDER_SUFFIX));
	}

	public ResolvePlaceholdersProcessor(PropertyPlaceholderHelper helper) {
		this(helper, GlobalPropertiesMode.BOTH);
	}

	public ResolvePlaceholdersProcessor(PropertyPlaceholderHelper helper, GlobalPropertiesMode globalPropertiesMode) {
		super();
		this.helper = helper;
		this.globalPropertiesMode = globalPropertiesMode;
	}

	PropertyPlaceholderHelper helper;
	GlobalPropertiesMode globalPropertiesMode = GlobalPropertiesMode.BOTH;

	@Override
	public void process(Properties properties) {
		Properties resolvedProperties = getResolvedProperties(properties, helper);
		logger.info("Resolved {} property values", resolvedProperties.size());
		properties.putAll(resolvedProperties);
	}

	protected Properties getResolvedProperties(Properties props, PropertyPlaceholderHelper helper) {
		Properties global = PropertyUtils.getProperties(props, globalPropertiesMode);
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

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	public GlobalPropertiesMode getGlobalPropertiesMode() {
		return globalPropertiesMode;
	}

	public void setGlobalPropertiesMode(GlobalPropertiesMode globalPropertiesMode) {
		this.globalPropertiesMode = globalPropertiesMode;
	}
}
