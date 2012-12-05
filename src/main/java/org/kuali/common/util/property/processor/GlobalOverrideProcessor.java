package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;

public class GlobalOverrideProcessor implements PropertyProcessor {

	GlobalPropertiesMode globalPropertiesMode;
	Mode propertyOverwriteMode;

	public GlobalOverrideProcessor() {
		this(GlobalPropertiesMode.BOTH, Constants.DEFAULT_PROPERTY_OVERWRITE_MODE);
	}

	public GlobalOverrideProcessor(GlobalPropertiesMode globalPropertiesMode) {
		this(globalPropertiesMode, Constants.DEFAULT_PROPERTY_OVERWRITE_MODE);
	}

	public GlobalOverrideProcessor(GlobalPropertiesMode globalPropertiesMode, Mode propertyOverwriteMode) {
		super();
		this.globalPropertiesMode = globalPropertiesMode;
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

	@Override
	public void process(Properties properties) {
		Properties global = PropertyUtils.getProperties(properties, globalPropertiesMode);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String newValue = global.getProperty(key);
			PropertyUtils.addOrOverrideProperty(properties, key, newValue, propertyOverwriteMode);
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
