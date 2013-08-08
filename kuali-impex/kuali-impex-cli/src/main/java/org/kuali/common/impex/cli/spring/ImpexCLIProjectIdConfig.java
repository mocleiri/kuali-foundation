package org.kuali.common.impex.cli.spring;

import org.kuali.common.impex.cli.project.ImpexCLIProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.project.spring.ProjectIdentifierConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImpexCLIProjectIdConfig implements ProjectIdentifierConfig {

	@Override
	public ProjectIdentifier projectIdentifier() {
		return ImpexCLIProjectConstants.PROJECT_ID;
	}

}
