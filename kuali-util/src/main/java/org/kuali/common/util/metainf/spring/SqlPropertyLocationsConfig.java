package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class SqlPropertyLocationsConfig {

	@Autowired
	Project project;

	@Bean
	public Location metaInfSqlLocation() {
		return MetaInfCommonConfig.getLocation(MetaInfCommonConfig.FEATURE_ID, project, "sql.properties");
	}
}
