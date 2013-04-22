package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.property.ProjectProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MavenResetConfig extends AbstractMavenResetConfig {

	@Override
	public List<ProjectProperties> getProjectPropertiesList() {
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		pps.add(jdbcProperties.jdbcProjectProperties());
		pps.add(mavenProjectProperties());
		return pps;
	}

	@Override
	public List<Class<?>> getAnnotatedClasses() {
		List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
		annotatedClasses.add(ResetConfig.class);
		annotatedClasses.add(SqlControllerConfig.class);
		return annotatedClasses;
	}
}
