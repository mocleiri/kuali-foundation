package org.kuali.common.util.spring.beans;

public class HelloWorldMessage implements Message {

	String message = "Hello World";

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
