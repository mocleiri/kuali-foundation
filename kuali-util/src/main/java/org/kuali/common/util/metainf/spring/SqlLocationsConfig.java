package org.kuali.common.util.metainf.spring;

import java.util.Collections;
import java.util.List;

import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class SqlLocationsConfig {

	@Autowired
	Project project;

	@Bean
	public List<Location> metaInfSqlLocations() {
		Location location = MetaInfCommonConfig.getLocation(MetaInfCommonConfig.FEATURE_ID, project, "sql.properties");
		return Collections.singletonList(location);
	}
}
