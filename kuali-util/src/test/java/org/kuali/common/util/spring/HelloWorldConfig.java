package org.kuali.common.util.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {

	@Bean
	public HelloMessage helloWorldMessage() {
		return new HelloMessage();
	}
}
