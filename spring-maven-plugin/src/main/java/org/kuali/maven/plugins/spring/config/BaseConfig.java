package org.kuali.maven.plugins.spring.config;

import org.kuali.maven.plugins.spring.SpringMojoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BaseConfig.class)
public class BaseConfig {

	@Bean
	public SpringMojoService service() {
		return new SpringMojoService();
	}

}
