package org.kuali.common.util.spring.beans;

public class GoodbyeMessage implements Message {

	String message = "Goodbye";

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
