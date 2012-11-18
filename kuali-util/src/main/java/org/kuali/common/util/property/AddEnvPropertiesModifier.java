package org.kuali.common.util.property;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;

public class AddEnvPropertiesModifier implements PropertyModifier {

	@Override
	public void modify(Properties properties) {
		Properties env = PropertyUtils.getEnvAsProperties();
		properties.putAll(env);
	}

}
