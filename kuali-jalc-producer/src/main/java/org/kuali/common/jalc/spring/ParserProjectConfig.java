package org.kuali.common.jalc.spring;

import org.kuali.common.util.MavenConstants;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserProjectConfig {

	public static final String GROUP_ID = MavenConstants.KUALI_COMMON_GROUP_ID;
	public static final String ARTIFACT_ID = "torque-parser";

	@Bean
	public Project generatorProject() {
		return ProjectUtils.loadProject(GROUP_ID + ":" + ARTIFACT_ID);
	}

}
