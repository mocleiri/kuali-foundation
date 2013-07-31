package org.kuali.common.util.metainf.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.properties.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MetaInfLocationsConfig.class })
public class SqlLocationsConfig {

	@Autowired
	MetaInfLocationsConfig metaInfLocationConfig;

	@Bean
	public List<Location> mpxLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(metaInfLocationConfig.getMetaInfCommon());
		locations.add(getMpx());
		return Collections.unmodifiableList(locations);
	}

	@Bean
	public List<Location> metaInfMpxBuildLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(metaInfLocationConfig.getMetaInfCommon());
		locations.add(metaInfLocationConfig.getMetaInfCommonBuild());
		locations.add(getMpx());
		locations.add(metaInfLocationConfig.getLocation("/build/sql.properties"));
		return Collections.unmodifiableList(locations);
	}

	protected Location getMpx() {
		return metaInfLocationConfig.getLocation("/sql.properties");
	}

}
