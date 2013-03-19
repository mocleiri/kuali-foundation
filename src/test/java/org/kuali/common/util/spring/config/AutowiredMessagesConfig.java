package org.kuali.common.util.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.beans.DefaultMessageImpl;
import org.kuali.common.util.spring.beans.Message;
import org.kuali.common.util.spring.beans.PrintMessagesExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutowiredMessagesConfig {

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

	@Bean
	@Autowired
	public List<Message> messages() {
		return null;
	}

	@Bean(initMethod = "execute")
	public Executable printMessagesExecutable() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(helloWorldMessage());
		messages.add(goodbyeMessage());

		PrintMessagesExecutable pme = new PrintMessagesExecutable();
		pme.setMessages(messages);
		return pme;
	}
}
