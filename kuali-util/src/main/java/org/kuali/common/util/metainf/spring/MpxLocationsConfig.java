package org.kuali.common.util.metainf.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.properties.Location;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MpxLocationsConfig extends MetaInfLocationsConfig {

	@Bean
	public List<Location> mpxLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(getMetaInfCommon());
		locations.add(getMpx());
		return Collections.unmodifiableList(locations);
	}

	@Bean
	public List<Location> mpxBuildLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(getMetaInfCommon());
		locations.add(getMetaInfCommonBuild());
		locations.add(getMpx());
		locations.add(getLocation("/build/mpx.properties"));
		return Collections.unmodifiableList(locations);
	}

	protected Location getMpx() {
		return getLocation("/mpx.properties");
	}

}
