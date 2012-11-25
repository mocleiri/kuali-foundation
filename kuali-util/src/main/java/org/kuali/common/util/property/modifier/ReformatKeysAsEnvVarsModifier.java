package org.kuali.common.util.property.modifier;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;

public class ReformatKeysAsEnvVarsModifier implements PropertyProcessor {

	@Override
	public void process(Properties properties) {
		Properties newProperties = PropertyUtils.reformatKeysAsEnvVars(properties);
		properties.clear();
		properties.putAll(newProperties);
	}
}
