package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class LoadProcessor implements PropertyProcessor {
	private static final Logger logger = LoggerFactory.getLogger(LoadProcessor.class);

	List<String> locations;
	GlobalPropertiesMode globalPropertiesOverrideMode = GlobalPropertiesMode.BOTH;
	String encoding;
	Mode missingLocationsMode = Mode.INFORM;

	public LoadProcessor() {
		this(null, GlobalPropertiesMode.BOTH, null, Mode.INFORM);
	}

	public LoadProcessor(List<String> locations, GlobalPropertiesMode globalPropertiesOverrideMode, String encoding, Mode missingLocationsMode) {
		super();
		this.locations = locations;
		this.globalPropertiesOverrideMode = globalPropertiesOverrideMode;
		this.encoding = encoding;
		this.missingLocationsMode = missingLocationsMode;
	}

	@Override
	public void process(Properties properties) {
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(Constants.DEFAULT_PLACEHOLDER_PREFIX, Constants.DEFAULT_PLACEHOLDER_SUFFIX);
		for (String location : locations) {
			Properties duplicate = PropertyUtils.getProperties(properties, globalPropertiesOverrideMode);
			String resolvedLocation = helper.replacePlaceholders(location, duplicate);
			if (!location.equals(resolvedLocation)) {
				logger.debug("Resolved location [{}] -> [{}]", location, resolvedLocation);
			}
			if (LocationUtils.exists(resolvedLocation)) {
				Properties newProperties = PropertyUtils.load(resolvedLocation, encoding);
				properties.putAll(newProperties);
			} else {
				ModeUtils.validate(missingLocationsMode, "Skipping non-existent location - [{}]", resolvedLocation, "Could not locate [" + resolvedLocation + "]");
			}
		}
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public GlobalPropertiesMode getGlobalPropertiesOverrideMode() {
		return globalPropertiesOverrideMode;
	}

	public void setGlobalPropertiesOverrideMode(GlobalPropertiesMode globalPropertiesOverrideMode) {
		this.globalPropertiesOverrideMode = globalPropertiesOverrideMode;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Mode getMissingLocationsMode() {
		return missingLocationsMode;
	}

	public void setMissingLocationsMode(Mode missingLocationsMode) {
		this.missingLocationsMode = missingLocationsMode;
	}

}
