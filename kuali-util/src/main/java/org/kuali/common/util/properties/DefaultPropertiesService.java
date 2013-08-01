package org.kuali.common.util.properties;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.processor.OverrideProcessor;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultPropertiesService implements PropertiesService {

	final PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", false);

	@Override
	public Properties getProperties(List<Location> locations) {
		// Allocate some storage
		Properties properties = new Properties();
		// Cycle through our list of locations
		for (Location location : locations) {
			// If the location exists, load it
			if (LocationUtils.exists(location.getValue())) {
				Properties loaded = PropertyUtils.load(location.getValue(), location.getEncoding());
				new OverrideProcessor(Mode.INFORM, loaded, 2).process(properties);
			} else {
				// Take appropriate action for missing locations (ignore, inform, warn, or error out)
				ModeUtils.validate(location.getMissingMode(), "Non-existent location [" + location.getValue() + "]");
			}
		}
		return properties;
	}

}
