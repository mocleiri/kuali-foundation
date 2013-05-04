package org.kuali.common.impex.spring;

import java.util.ArrayList;

import org.kuali.common.util.Project;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.spring.ConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GeneratorProjectConfig.class)
public class GeneratorPropertiesConfig {

	@Autowired
	GeneratorProjectConfig projectConfig;

	@Bean
	public ProjectProperties generatorProjectProperties() {
		Project project = projectConfig.generatorProject();
		return ConfigUtils.getProjectProperties(project, new ArrayList<String>());
	}

}
