package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathModifier implements PropertyModifier {

	private static final Logger logger = LoggerFactory.getLogger(PathModifier.class);

	String suffix = Constants.DEFAULT_PATH_SUFFIX;
	List<String> includes;
	List<String> excludes;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	public PathModifier() {
		this(null);
	}

	public PathModifier(List<String> includes) {
		super();
		this.includes = includes;
	}

	@Override
	public void modify(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		for (String key : keys) {
			String oldValue = properties.getProperty(key);
			String newValue = Str.getPath(oldValue);
			String newKey = key + "." + suffix;
			logger.info("Setting {}={}", newKey, newValue);
			PropertyUtils.setProperty(properties, newKey, newValue, propertyOverwriteMode);
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

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
