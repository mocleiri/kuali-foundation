package org.kuali.common.aws.ec2;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.properties.spring.DefaultPropertiesServiceConfig;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ DefaultPropertiesServiceConfig.class })
public class LaunchInstancePSC implements PropertySourceConfig {

	@Autowired
	Project project;

	@Autowired
	PropertiesService service;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		List<Location> locations = new ArrayList<Location>();
		locations.add(new Location("classpath:org/kuali/common/kuali-aws/ci.properties", ProjectUtils.getEncoding(project)));
		locations.add(new Location("classpath:org/kuali/common/kuali-aws/foundation.properties", ProjectUtils.getEncoding(project)));
		return PropertySourceUtils.getPropertySource(service, locations);
	}
}
