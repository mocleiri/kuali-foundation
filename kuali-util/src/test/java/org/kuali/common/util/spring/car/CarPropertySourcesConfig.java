package org.kuali.common.util.spring.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.util.PropertyPlaceholderHelper;

@Configuration
public class CarPropertySourcesConfig {

	PropertyPlaceholderHelper pph = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;

	@Autowired
	Environment env;

	@Bean
	public String projectPropertiesLocation() {
		String groupId = "org.kuali.common";
		String artifactId = "kuali-util";

		Properties properties = new Properties();
		properties.setProperty("project.groupId.path", Str.getPath(groupId));
		properties.setProperty("project.artifactId", artifactId);

		return pph.replacePlaceholders(Constants.PROJECT_PROPERTIES_LOCATION, properties);
	}

	@Bean()
	public PropertiesPropertySource pps() {
		// Default to UTF-8 for project properties unless they've specified something else
		String ppenc = env.getProperty("project.properties.encoding", "UTF-8");
		String pploc = projectPropertiesLocation();
		Properties projectProperties = PropertyUtils.load(pploc, ppenc);
		String encoding = projectProperties.getProperty("project.encoding");
		Properties global = PropertyUtils.getGlobalProperties();
		Properties source = new Properties();
		source.putAll(global);
		List<String> locations = new ArrayList<String>();
		locations.add("classpath:car.properties");
		locations.add("classpath:${car.make}.properties");

		for (String location : locations) {
			String resolvedLocation = pph.replacePlaceholders(location, source);
			boolean exists = LocationUtils.exists(resolvedLocation);
			if (exists) {
				source.putAll(PropertyUtils.load(resolvedLocation, encoding));
				source.putAll(global);
			}
		}
		String name = "springProperties";
		return new PropertiesPropertySource(name, source);
	}

}
