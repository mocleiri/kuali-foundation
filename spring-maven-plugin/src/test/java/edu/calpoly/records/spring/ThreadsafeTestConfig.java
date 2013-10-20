package edu.calpoly.records.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class ThreadsafeTestConfig {

	@Autowired
	EnvironmentService env;

	@Bean
	public Executable executablesExecutable() {
		System.out.println("hello world");
		return null;
	}
}
