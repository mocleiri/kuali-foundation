package org.kuali.common.impex.util.spring;

import org.kuali.common.impex.cli.project.KualiImpexCLIProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.project.spring.ProjectIdentifierConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KualiImpexCLIProjectIdentifierConfig implements ProjectIdentifierConfig {

	@Override
	public ProjectIdentifier projectIdentifier() {
		return KualiImpexCLIProjectConstants.PROJECT_ID;
	}

}
