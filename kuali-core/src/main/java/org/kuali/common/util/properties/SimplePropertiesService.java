package org.kuali.common.util.properties;

import java.util.List;
import java.util.Properties;

public class SimplePropertiesService implements PropertiesService {

	@Override
	public Properties getProperties(List<Location> locations) {
		Properties properties = new Properties();
		for (Location location : locations) {
			Properties loaded = new LocationLoader(location).load();
			properties.putAll(loaded);
		}
		return properties;
	}

}
