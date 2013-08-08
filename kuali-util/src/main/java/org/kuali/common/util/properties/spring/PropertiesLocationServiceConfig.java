package org.kuali.common.util.properties.spring;

import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.spring.ProjectServiceConfig;
import org.kuali.common.util.properties.DefaultPropertiesLocationService;
import org.kuali.common.util.properties.PropertiesLocationService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ ProjectServiceConfig.class })
public class PropertiesLocationServiceConfig {

	private static final String CACHE_KEY = "properties.cache";
	private static final boolean DEFAULT_CACHE_PROPERTIES_VALUE = true;

	@Autowired
	Environment env;

	@Autowired
	ProjectService projectService;

	@Bean
	public PropertiesLocationService propertiesLocationService() {
		boolean cache = SpringUtils.getBoolean(env, CACHE_KEY, DEFAULT_CACHE_PROPERTIES_VALUE);
		return new DefaultPropertiesLocationService(projectService, cache);
	}

}
