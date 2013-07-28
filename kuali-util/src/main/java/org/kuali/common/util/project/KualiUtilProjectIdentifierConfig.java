package org.kuali.common.util.project;

import org.kuali.common.util.project.spring.config.ProjectIdentifierConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KualiUtilProjectIdentifierConfig implements ProjectIdentifierConfig {

	ProjectIdentifier identifier = KualiUtilProjectConstants.PROJECT_IDENTIFIER;

	@Override
	public String projectGroupId() {
		return identifier.getGroupId();
	}

	@Override
	public String projectArtifactId() {
		return identifier.getArtifactId();
	}

}
