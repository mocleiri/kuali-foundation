package org.kuali.common.util.metainf.spring;

import java.util.Collections;
import java.util.List;

import org.kuali.common.util.properties.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MetaInfCommonConfig.class })
public class SqlLocationsConfig {

	@Autowired
	MetaInfCommonConfig metaInfCommonConfig;

	@Bean
	public List<Location> metaInfSqlLocations() {
		Location location = metaInfCommonConfig.getLocation(MetaInfCommonConfig.FEATURE_ID, "sql.properties");
		return Collections.singletonList(location);
	}
}
