package org.kuali.common.util.spring.config;

import org.kuali.common.util.spring.beans.GoodbyeMessage;
import org.kuali.common.util.spring.beans.HelloWorldMessage;
import org.kuali.common.util.spring.beans.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloAndGoodbyeConfig {

	@Bean
	public Message helloWorldMessage() {
		return new HelloWorldMessage();
	}

	@Bean
	public Message goodByeMessage() {
		return new GoodbyeMessage();
	}
}
