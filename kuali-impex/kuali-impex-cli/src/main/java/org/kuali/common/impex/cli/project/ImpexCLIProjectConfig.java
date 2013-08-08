package org.kuali.common.impex.cli.project;

import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.project.spring.ProjectIdentifierConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class ImpexCLIProjectConfig implements ProjectIdentifierConfig {

	@Override
	@Bean
	public ProjectIdentifier projectIdentifier() {
		return ImpexCLIProjectConstants.PROJECT_ID;
	}

}
