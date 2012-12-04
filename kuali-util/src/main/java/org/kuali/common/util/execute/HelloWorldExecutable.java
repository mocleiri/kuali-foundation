package org.kuali.common.util.execute;

import org.kuali.common.util.execute.Executable;

public class HelloWorldExecutable implements Executable {

	@Override
    public void execute() {
		System.out.println("Hello world");
	}

}
