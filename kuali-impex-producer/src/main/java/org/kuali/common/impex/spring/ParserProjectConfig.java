package org.kuali.common.impex.spring;

import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Deprecated
public class ParserProjectConfig {

	public static final String GROUP_ID = org.kuali.common.util.MavenConstants.KUALI_COMMON_GROUP_ID;
	public static final String ARTIFACT_ID = "torque-parser";

	@Bean
	public Project generatorProject() {
		return ProjectUtils.loadProject(GROUP_ID + ":" + ARTIFACT_ID);
	}

}
