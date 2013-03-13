package org.kuali.common.util.spring.config;

import org.kuali.common.util.spring.beans.HelloWorldMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {

	@Bean
	public HelloWorldMessage helloWorldMessage() {
		return new HelloWorldMessage();
	}
}
