package org.kuali.common.util.properties.spring;

import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class ProjectPropertySourceConfig implements PropertySourceConfig {

	@Autowired
	Project project;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		return PropertySourceUtils.getPropertySource(project.getProperties());
	}

}
