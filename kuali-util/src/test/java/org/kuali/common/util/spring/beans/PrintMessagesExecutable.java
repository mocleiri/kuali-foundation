package org.kuali.common.util.spring.beans;

import java.util.List;

import org.kuali.common.util.execute.Executable;

public class PrintMessagesExecutable implements Executable {

	List<Message> messages;

	@Override
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
