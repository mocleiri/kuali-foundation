package org.kuali.common.util.helloworld;

import org.kohsuke.MetaInfServices;

@MetaInfServices
public class DefaultHelloWorldService implements HelloWorldService {

	@Override
	public void sayHello() {
		System.out.println("hello world");
	}

}
