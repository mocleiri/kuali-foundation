package org.kuali.common.util.spring.beans;

public class DefaultMessageImpl implements Message {

	String message;

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
