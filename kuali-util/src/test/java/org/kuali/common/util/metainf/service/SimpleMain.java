package org.kuali.common.util.metainf.service;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.HelloWorldExecutable;
import org.kuali.common.util.spring.main.MainConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleMain implements MainConfig {

	@Override
	@Bean
	public Executable mainExecutable() {
		return new HelloWorldExecutable();
	}
}
