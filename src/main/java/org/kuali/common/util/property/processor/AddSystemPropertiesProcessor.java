package org.kuali.common.util.property.processor;

import java.util.Properties;

public class AddSystemPropertiesProcessor implements PropertyProcessor {

	@Override
	public void process(Properties properties) {
		Properties system = System.getProperties();
		properties.putAll(system);
	}

}
