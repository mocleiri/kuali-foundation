package org.kuali.common.util.spring.beans;

public class HelloWorldMessage extends DefaultMessageImpl {

	public HelloWorldMessage() {
		super();
		this.message = "Hello world";
	}

}
