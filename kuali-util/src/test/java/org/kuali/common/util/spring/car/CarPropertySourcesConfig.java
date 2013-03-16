package org.kuali.common.util.spring.car;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.util.PropertyPlaceholderHelper;

@Configuration
public class CarPropertySourcesConfig {
	
	@Autowired
	Environment env;
	
	@Bean()
	public PropertiesPropertySource pps() {
		String encoding = SpringUtils.getProperty(env, "project.encoding");
		PropertyPlaceholderHelper pph = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		Properties global = PropertyUtils.getGlobalProperties();
		Properties source = new Properties();
		source.putAll(global);
		List<String> locations = Arrays.asList("classpath:car.properties", "classpath:${car.make}.properties");
		for (String location : locations) {
			String resolvedLocation = pph.replacePlaceholders(location, source);
			boolean exists = LocationUtils.exists(resolvedLocation);
			if (exists) {
				source.putAll(PropertyUtils.load(resolvedLocation,encoding));
				source.putAll(global);
			}
		}
		String name = "springProperties";
		return new PropertiesPropertySource(name, source);
	}

}
