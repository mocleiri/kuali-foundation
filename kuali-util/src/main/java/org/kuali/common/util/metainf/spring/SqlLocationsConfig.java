package org.kuali.common.util.metainf.spring;

import java.util.Collections;
import java.util.List;

import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.PropertiesLocationService;
import org.kuali.common.util.properties.spring.PropertiesLocationServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PropertiesLocationServiceConfig.class })
public class SqlLocationsConfig {

	@Autowired
	PropertiesLocationService service;

	@Bean
	public List<Location> metaInfSqlLocations() {
		Location location = service.getLocation(MetaInfUtils.FEATURE_ID, "sql.properties");
		return Collections.singletonList(location);
	}
}
