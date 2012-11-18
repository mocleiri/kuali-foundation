package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.PropertyOverwriteMode;

public class PathModifier implements PropertyModifier {

	public static final String DEFAULT_PATH_SUFFIX = "path";

	String suffix = DEFAULT_PATH_SUFFIX;
	List<String> includes;
	List<String> excludes;
	PropertyOverwriteMode propertyOverwriteMode = PropertyOverwriteMode.INFORM;

	@Override
	public void modify(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		for (String key : keys) {
			String oldValue = properties.getProperty(key);
			String newValue = Str.getPath(oldValue);
			String newKey = key + "." + suffix;
			PropertyUtils.checkExistingProperty(properties, newKey, propertyOverwriteMode);
			properties.setProperty(newKey, newValue);
		}
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
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

	public PropertyOverwriteMode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(PropertyOverwriteMode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}
}
