package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class ResolvePlaceholdersModifier implements PropertyModifier {

	private static final Logger logger = LoggerFactory.getLogger(ResolvePlaceholdersModifier.class);

	PropertyPlaceholderHelper helper;

	public ResolvePlaceholdersModifier() {
		this(null);
	}

	public ResolvePlaceholdersModifier(PropertyPlaceholderHelper helper) {
		super();
		this.helper = helper;
	}

	@Override
	public void modify(Properties properties) {
		Properties resolvedProperties = getResolvedProperties(properties, helper);
		logger.info("Resolved {} property values", resolvedProperties.size());
		properties.putAll(resolvedProperties);
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

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}
}
