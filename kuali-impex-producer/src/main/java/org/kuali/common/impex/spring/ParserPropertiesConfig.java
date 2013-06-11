package org.kuali.common.impex.spring;

import java.util.Arrays;

import org.kuali.common.util.Project;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.spring.ConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ParserProjectConfig.class)
public class ParserPropertiesConfig {

	@Autowired
	ParserProjectConfig projectConfig;

	@Bean
	public ProjectProperties parserProjectProperties() {
		String batch = "classpath:org/kuali/common/impex/batch.properties";
		Project project = projectConfig.generatorProject();
		return ConfigUtils.getProjectProperties(project, Arrays.asList(batch));
	}

}
