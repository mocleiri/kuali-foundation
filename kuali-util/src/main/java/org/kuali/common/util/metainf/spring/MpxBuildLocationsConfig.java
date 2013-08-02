package org.kuali.common.util.metainf.spring;

import java.util.List;

import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.spring.PropertyLocationsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MpxLocationsConfig.class })
public class MpxBuildLocationsConfig implements PropertyLocationsConfig {

	@Autowired
	MpxLocationsConfig mpxLocationsConfig;

	@Override
	@Bean
	public List<Location> propertyLocations() {
		return mpxLocationsConfig.metaInfMpxBuildLocations();
	}

}
