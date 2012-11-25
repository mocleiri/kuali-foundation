package org.kuali.common.util.property.processor;

import java.util.Properties;

public class AddSystemPropertiesModifier implements PropertyProcessor {

	@Override
	public void process(Properties properties) {
		Properties system = System.getProperties();
		properties.putAll(system);
	}

}
