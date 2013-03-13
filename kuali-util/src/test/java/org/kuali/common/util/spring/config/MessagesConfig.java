package org.kuali.common.util.spring.config;

import org.kuali.common.util.spring.beans.DefaultMessage;
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
	public String goodbyeString() {
		return "Good bye";
	}

	@Bean
	public Message helloWorldMessage() {
		DefaultMessage message = new DefaultMessage();
		message.setMessage(helloWorldString());
		return message;
	}

	@Bean
	public Message goodbyeMessage() {
		DefaultMessage message = new DefaultMessage();
		message.setMessage(goodbyeString());
		return message;
	}
}
