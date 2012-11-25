package org.kuali.common.util.property.processor;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;

public class ReformatKeysAsEnvVarsProcessor implements PropertyProcessor {

	@Override
	public void process(Properties properties) {
		Properties newProperties = PropertyUtils.reformatKeysAsEnvVars(properties);
		properties.clear();
		properties.putAll(newProperties);
	}
}
