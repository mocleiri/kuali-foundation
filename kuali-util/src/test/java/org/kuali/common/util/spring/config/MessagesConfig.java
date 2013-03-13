package org.kuali.common.util.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.beans.DefaultMessageImpl;
import org.kuali.common.util.spring.beans.Message;
import org.kuali.common.util.spring.beans.PrintMessagesExecutable;
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
		DefaultMessageImpl message = new DefaultMessageImpl();
		message.setMessage(helloWorldString());
		return message;
	}

	@Bean
	public Message goodbyeMessage() {
		DefaultMessageImpl message = new DefaultMessageImpl();
		message.setMessage(goodbyeString());
		return message;
	}

	// @Bean(initMethod = "execute")
	@Bean
	public Executable printMessagesExecutable() {
		return new PrintMessagesExecutable();
	}
}
