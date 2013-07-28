package org.kuali.common.util.project;

import org.kuali.common.util.project.spring.config.AutomaticProjectConfig;
import org.kuali.common.util.project.spring.config.ProjectConfig;
import org.kuali.common.util.project.spring.config.ProjectIdentifierConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutomaticProjectConfig.class })
public class KualiUtilProjectConfig implements ProjectIdentifierConfig, ProjectConfig {

	protected static final ProjectIdentifier IDENTIFIER = KualiUtilProjectConstants.PROJECT_IDENTIFIER;

	@Autowired
	Project project;

	@Override
	@Bean
	public String projectGroupId() {
		return IDENTIFIER.getGroupId();
	}

	@Override
	@Bean
	public String projectArtifactId() {
		return IDENTIFIER.getArtifactId();
	}

	@Override
	@Bean
	public Project project() {
		return project;
	}

}
