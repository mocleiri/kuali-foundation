package org.kuali.common.util.project;

import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ProjectServiceConfig {

	private static final String SERVICE_KEY = "project.service";
	private static final String SERVICE_BEAN = UtilConstants.PROJECT_ID + ":projectServiceConfig:projectService";

	@Autowired
	Environment env;

	@Bean(name = SERVICE_BEAN)
	public ProjectService projectService() {
		return SpringUtils.getInstance(env, SERVICE_KEY, DefaultProjectService.class);
	}

}
