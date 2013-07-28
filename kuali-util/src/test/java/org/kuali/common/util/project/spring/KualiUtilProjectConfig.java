package org.kuali.common.util.project.spring;

import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.ProjectIdentifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutomaticProjectConfig.class })
public class KualiUtilProjectConfig implements ProjectIdentifierConfig {

	protected static final ProjectIdentifier IDENTIFIER = KualiUtilProjectConstants.PROJECT_IDENTIFIER;

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
}
