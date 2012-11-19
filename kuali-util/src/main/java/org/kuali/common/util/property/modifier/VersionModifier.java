package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.PropertyOverwriteMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VersionModifier implements PropertyModifier {

	private static final Logger logger = LoggerFactory.getLogger(VersionModifier.class);

	public VersionModifier() {
		this(null);
	}

	public VersionModifier(List<String> includes) {
		super();
		this.includes = includes;
	}

	String majorSuffix = Constants.DEFAULT_MAJOR_VERSION_SUFFIX;
	String minorSuffix = Constants.DEFAULT_MINOR_VERSION_SUFFIX;
	String incrementalSuffix = Constants.DEFAULT_INCREMENTAL_VERSION_SUFFIX;
	String qualifierSuffix = Constants.DEFAULT_QUALIFIER_VERSION_SUFFIX;
	String trimmedSuffix = Constants.DEFAULT_TRIMMED_VERSION_SUFFIX;

	List<String> includes;
	List<String> excludes;
	PropertyOverwriteMode propertyOverwriteMode = PropertyOverwriteMode.INFORM;

	@Override
	public void modify(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		for (String key : keys) {
			String oldValue = properties.getProperty(key);
			String newValue = Str.getPath(oldValue);
			String newKey = null;// key + "." + suffix;
			logger.debug("Setting {}={}", newKey, newValue);
			PropertyUtils.setProperty(properties, newKey, newValue, propertyOverwriteMode);
		}
	}

}
