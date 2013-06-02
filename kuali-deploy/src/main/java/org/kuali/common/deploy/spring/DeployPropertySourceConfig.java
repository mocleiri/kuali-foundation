package org.kuali.common.deploy.spring;

import org.kuali.common.deploy.DeployProjectContext;
import org.kuali.common.jdbc.JdbcProjectContext;
import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.PropertySource;

public class DeployPropertySourceConfig {

	@Bean
	public PropertySource<?> springPropertySource() {
		ProjectContext project = new DeployProjectContext();
		ProjectContext other = new JdbcProjectContext();
		return SpringUtils.getGlobalPropertySource(project, other);
	}

}
