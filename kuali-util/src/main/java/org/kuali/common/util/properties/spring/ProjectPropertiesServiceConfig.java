package org.kuali.common.util.properties.spring;

import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.OverridePropertiesService;
import org.kuali.common.util.properties.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class ProjectPropertiesServiceConfig {

	@Autowired
	Project project;

	@Bean
	public PropertiesService projectPropertiesService() {
		return new OverridePropertiesService(project.getProperties());
	}

}
