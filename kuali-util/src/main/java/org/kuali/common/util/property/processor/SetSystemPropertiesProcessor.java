package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;

public class SetSystemPropertiesProcessor implements PropertyProcessor {

	Mode propertyOverwriteMode;
	List<String> includes;
	List<String> excludes;

	public SetSystemPropertiesProcessor() {
		this(null);
	}

	public SetSystemPropertiesProcessor(Mode propertyOverwriteMode) {
		this(null, Constants.DEFAULT_PROPERTY_OVERWRITE_MODE);
	}

	public SetSystemPropertiesProcessor(List<String> includes, Mode propertyOverwriteMode) {
		super();
		this.includes = includes;
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

	@Override
	public void process(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		for (String key : keys) {
			String newValue = properties.getProperty(key);
			PropertyUtils.addOrOverrideProperty(properties, key, newValue, propertyOverwriteMode);
		}
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

}
