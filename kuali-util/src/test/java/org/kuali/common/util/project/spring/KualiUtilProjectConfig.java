package org.kuali.common.util.project.spring;

import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.ProjectIdentifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class KualiUtilProjectConfig implements ProjectIdentifierConfig {

	@Override
	@Bean
	public ProjectIdentifier projectIdentifier() {
		return KualiUtilProjectConstants.PROJECT_IDENTIFIER;
	}
}
