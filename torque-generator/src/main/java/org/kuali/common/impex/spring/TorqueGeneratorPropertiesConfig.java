package org.kuali.common.impex.spring;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.spring.ConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TorqueGeneratorProjectConfig.class)
public class TorqueGeneratorPropertiesConfig {

	@Autowired
	TorqueGeneratorProjectConfig projectConfig;

	@Bean
	public ProjectProperties jdbcProjectProperties() {
		Project project = projectConfig.torqueGeneratorProject();

		List<String> locations = Arrays.asList("classpath:org/kuali/common/impex/batch.properties");

		return ConfigUtils.getProjectProperties(project, locations);
	}

}
