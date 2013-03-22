package org.kuali.common.jdbc.spring;

import org.kuali.common.KualiJdbcGAV;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcProjectConfig {

	@Bean
	public Project jdbcProject() {
		return ProjectUtils.loadProject(KualiJdbcGAV.GROUP_ID + ":" + KualiJdbcGAV.ARTIFACT_ID);
	}

}
