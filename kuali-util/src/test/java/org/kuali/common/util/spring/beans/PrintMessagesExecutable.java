package org.kuali.common.util.spring.beans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintMessagesExecutable implements Executable {

	@Autowired
	List<Message> messages;

	@Override
	@PostConstruct
	public void execute() {
		for (Message message : messages) {
			System.out.println(message.getMessage());
		}
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
