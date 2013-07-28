package org.kuali.common.util.project.spring.config;

import org.kuali.common.util.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MavenProjectConfig.class, RuntimeProjectConfig.class })
public class DefaultProjectConfig implements ProjectConfig {

	@Autowired
	Project project;

	@Override
	public Project project() {
		return project;
	}

}
