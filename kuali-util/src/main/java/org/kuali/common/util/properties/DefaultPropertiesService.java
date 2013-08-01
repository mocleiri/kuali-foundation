package org.kuali.common.util.properties;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
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
			PropertiesLoader loader = new LocationLoader(location);
			Properties loaded = loader.load();
			new OverrideProcessor(Mode.INFORM, loaded, 2).process(properties);
		}
		return properties;
	}

}
