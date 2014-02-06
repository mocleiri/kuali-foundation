package org.kuali.common.util.wait.spring;

import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WaitServiceConfig {

	@Bean
	public WaitService waitService() {
		return new DefaultWaitService();
	}
}
