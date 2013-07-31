package org.kuali.common.util.metainf.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.properties.Location;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlLocationsConfig extends MetaInfLocationsConfig {

	@Bean
	public List<Location> metaInfSqlLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(getMetaInfCommon());
		locations.add(getSql());
		return Collections.unmodifiableList(locations);
	}

	@Bean
	public List<Location> metaInfSqlBuildLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(getMetaInfCommon());
		locations.add(getMetaInfCommonBuild());
		locations.add(getSql());
		locations.add(getLocation("/build/sql.properties"));
		return Collections.unmodifiableList(locations);
	}

	protected Location getSql() {
		return getLocation("/sql.properties");
	}

}
