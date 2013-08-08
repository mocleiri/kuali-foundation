package org.kuali.common.impex.spring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @deprecated
 */
@Deprecated
@Configuration
@Import(ParserProjectConfig.class)
public class ParserPropertiesConfig {

	@Autowired
	ParserProjectConfig projectConfig;

	@Bean
	public org.kuali.common.util.property.ProjectProperties parserProjectProperties() {
		String batch = "classpath:org/kuali/common/impex/batch.properties";
		org.kuali.common.util.Project project = projectConfig.generatorProject();
		return org.kuali.common.util.spring.ConfigUtils.getProjectProperties(project, Arrays.asList(batch));
	}

}
