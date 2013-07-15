package org.kuali.common.util.config.spring;

import org.kuali.common.util.config.DefaultProjectConfigService;
import org.kuali.common.util.config.ProjectConfigService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ProjectConfigSpringConfig {

	private static final String SERVICE_KEY = "project.config.service";

	@Autowired
	Environment env;

	@Bean
	public ProjectConfigService utilProjectConfigService() {
		return SpringUtils.getInstance(env, SERVICE_KEY, DefaultProjectConfigService.class);
	}

}
