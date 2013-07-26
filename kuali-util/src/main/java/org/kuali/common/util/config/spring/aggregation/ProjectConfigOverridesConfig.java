package org.kuali.common.util.config.spring.aggregation;

import java.util.Properties;

import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.spring.config.MavenProjectConfig;
import org.kuali.common.util.project.spring.config.ProjectConfig;
import org.kuali.common.util.project.spring.config.RuntimeProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MavenProjectConfig.class, RuntimeProjectConfig.class })
public class ProjectConfigOverridesConfig implements ConfigOverridesConfig {

	@Autowired
	ProjectConfig projectConfig;

	@Override
	public Properties configOverrideProperties() {
		Project project = projectConfig.project();
		Properties overrides = project.getProperties();
		return overrides;
	}

}
