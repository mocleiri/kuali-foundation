package org.kuali.common.util.property.processor;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;

public class AddEnvPropertiesProcessor implements PropertyProcessor {

	@Override
	public void process(Properties properties) {
		Properties env = PropertyUtils.getEnvAsProperties();
		properties.putAll(env);
	}

}
