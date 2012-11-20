package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.GlobalPropertiesMode;

public class OverrideModifier implements PropertyModifier {

	GlobalPropertiesMode globalPropertiesMode;
	Mode propertyOverwriteMode;

	@Override
	public void modify(Properties properties) {
		Properties global = PropertyUtils.getProperties(globalPropertiesMode);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String globalValue = global.getProperty(key);
			if (!StringUtils.isBlank(globalValue)) {
				PropertyUtils.setProperty(properties, key, globalValue, propertyOverwriteMode);
			}
		}
	}

	public GlobalPropertiesMode getGlobalPropertiesMode() {
		return globalPropertiesMode;
	}

	public void setGlobalPropertiesMode(GlobalPropertiesMode globalPropertiesMode) {
		this.globalPropertiesMode = globalPropertiesMode;
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
