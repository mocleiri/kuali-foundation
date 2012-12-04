package org.kuali.maven.plugins.spring;

import org.kuali.common.util.Executable;

public class HelloWorldExecutable implements Executable {

	@Override
    public void execute() {
		System.out.println("Hello world");
	}

}
