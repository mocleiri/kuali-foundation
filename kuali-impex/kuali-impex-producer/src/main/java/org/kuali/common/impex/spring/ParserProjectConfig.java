package org.kuali.common.impex.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @deprecated
 */
@Deprecated
@Configuration
public class ParserProjectConfig {

	public static final String GROUP_ID = org.kuali.common.util.MavenConstants.KUALI_COMMON_GROUP_ID;
	public static final String ARTIFACT_ID = "torque-parser";

	@Bean
	public org.kuali.common.util.Project generatorProject() {
		return org.kuali.common.util.ProjectUtils.loadProject(GROUP_ID + ":" + ARTIFACT_ID);
	}

}
