package org.kuali.common.util.property;

import java.util.Properties;

public class AddSystemPropertiesModifier implements PropertyModifier {

	@Override
	public void modify(Properties properties) {
		Properties system = System.getProperties();
		properties.putAll(system);
	}

}
