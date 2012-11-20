package org.kuali.common.util.property.modifier;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;

public class ReformatKeysAsEnvVarsModifier implements PropertyModifier {

	@Override
	public void modify(Properties properties) {
		Properties newProperties = PropertyUtils.getPropertiesAsEnvVars(properties);
		properties.clear();
		properties.putAll(newProperties);
	}
}
