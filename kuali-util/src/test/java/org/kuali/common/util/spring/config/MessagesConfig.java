package org.kuali.common.util.spring.config;

import org.kuali.common.util.spring.beans.GoodbyeMessage;
import org.kuali.common.util.spring.beans.HelloWorldMessage;
import org.kuali.common.util.spring.beans.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagesConfig {

	@Bean
	public String helloWorldString() {
		return "Hello World";
	}

	@Bean
	public Message helloWorldMessage() {
		HelloWorldMessage hwm = new HelloWorldMessage();
		hwm.setMessage(helloWorldString());
		return hwm;
	}

	@Bean
	public Message goodbyeMessage() {
		return new GoodbyeMessage();
	}
}
