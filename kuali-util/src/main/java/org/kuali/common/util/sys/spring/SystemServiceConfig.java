package org.kuali.common.util.sys.spring;

import org.kuali.common.util.sys.DefaultSystemService;
import org.kuali.common.util.sys.SystemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemServiceConfig {

	@Bean
	public SystemService systemService() {
		return new DefaultSystemService();
	}

}
