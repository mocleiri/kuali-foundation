package org.kuali.common.util.spring;

public class HelloMessage implements Message {

	String message = "Hello World";

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
