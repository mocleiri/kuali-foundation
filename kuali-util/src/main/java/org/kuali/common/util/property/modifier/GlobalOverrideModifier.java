package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;

public class GlobalOverrideModifier implements PropertyModifier {

	GlobalPropertiesMode globalPropertiesMode = GlobalPropertiesMode.BOTH;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	@Override
	public void modify(Properties properties) {
		Properties global = PropertyUtils.getProperties(properties, globalPropertiesMode);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String newValue = global.getProperty(key);
			PropertyUtils.setProperty(properties, key, newValue, propertyOverwriteMode);
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
