package org.kuali.common.util.metainf.spring;

import java.util.Collections;
import java.util.List;

import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.ProjectServiceConfig;
import org.kuali.common.util.properties.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ProjectServiceConfig.class })
public class SqlLocationsConfig {

	@Autowired
	ProjectService projectService;

	@Bean
	public List<Location> metaInfSqlLocations() {
		Project project = projectService.getProject(KualiUtilProjectConstants.PROJECT_ID);
		Location location = MetaInfCommonConfig.getLocation(MetaInfCommonConfig.FEATURE_ID, project, "sql.properties");
		return Collections.singletonList(location);
	}
}
