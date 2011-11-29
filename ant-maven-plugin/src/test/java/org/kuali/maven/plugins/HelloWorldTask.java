package org.kuali.maven.plugins;

import org.apache.tools.ant.taskdefs.Java;

public class HelloWorldTask extends Java {

	public HelloWorldTask() {
		super();
		setClassname("org.kuali.maven.plugins.HelloWorld");

	}

}
