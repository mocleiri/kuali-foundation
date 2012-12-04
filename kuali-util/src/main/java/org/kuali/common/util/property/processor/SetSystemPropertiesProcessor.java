package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetSystemPropertiesProcessor implements PropertyProcessor {
	private static final Logger logger = LoggerFactory.getLogger(SetSystemPropertiesProcessor.class);

	Mode propertyOverwriteMode;
	List<String> includes;
	List<String> excludes;

	public SetSystemPropertiesProcessor() {
		this(null);
	}

	public SetSystemPropertiesProcessor(List<String> includes) {
		super();
		this.includes = includes;
		this.propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;
	}

	@Override
	public void process(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		Properties systemProperties = System.getProperties();
		for (String key : keys) {
			String value = properties.getProperty(key);
			logger.info("Setting system property [{}]", key);
			PropertyUtils.addOrOverrideProperty(systemProperties, key, value, propertyOverwriteMode);
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
