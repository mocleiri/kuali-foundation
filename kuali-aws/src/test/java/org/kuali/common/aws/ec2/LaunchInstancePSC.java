package org.kuali.common.aws.ec2;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.aws.project.spring.AwsProjectConfig;
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
@Import({ AwsProjectConfig.class, DefaultPropertiesServiceConfig.class })
public class LaunchInstancePSC implements PropertySourceConfig {

	@Autowired
	Project project;

	@Autowired
	PropertiesService service;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		String encoding = ProjectUtils.getEncoding(project);
		List<Location> locations = new ArrayList<Location>();
		locations.add(new Location("classpath:org/kuali/common/kuali-aws/foundation.properties", encoding));
		locations.add(new Location("classpath:org/kuali/common/kuali-aws/ci.properties", encoding));
		return PropertySourceUtils.getPropertySource(service, locations);
	}
}
