package org.kuali.common.util.main.spring;

import org.kuali.common.util.main.DefaultMainService;
import org.kuali.common.util.main.MainService;
import org.kuali.common.util.spring.service.PropertySourceService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class MainServiceConfig {

	@Autowired
	PropertySourceService service;

	@Bean
	public MainService mainService() {
		return new DefaultMainService(service);
	}
}
