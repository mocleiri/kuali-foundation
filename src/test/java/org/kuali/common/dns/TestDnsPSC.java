package org.kuali.common.dns;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.dns.project.DnsProjectConfig;
import org.kuali.common.dns.project.DnsProjectConstants;
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
@Import({ DefaultPropertiesServiceConfig.class, DnsProjectConfig.class })
public class TestDnsPSC implements PropertySourceConfig {

	@Autowired
	Project project;

	@Autowired
	PropertiesService service;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		String encoding = ProjectUtils.getEncoding(project);
		String prefix = ProjectUtils.getClasspathPrefix(DnsProjectConstants.PROJECT_ID);
		List<Location> locations = new ArrayList<Location>();
		locations.add(new Location(prefix + "/dnsme.properties", encoding));
		return PropertySourceUtils.getPropertySource(service, locations);
	}
}
