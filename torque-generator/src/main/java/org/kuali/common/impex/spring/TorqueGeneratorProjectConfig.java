package org.kuali.common.impex.spring;

import org.kuali.common.TorqueGeneratorGAV;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TorqueGeneratorProjectConfig {

	@Bean
	public Project torqueGeneratorProject() {
		return ProjectUtils.loadProject(TorqueGeneratorGAV.GROUP_ID + ":" + TorqueGeneratorGAV.ARTIFACT_ID);
	}

}
