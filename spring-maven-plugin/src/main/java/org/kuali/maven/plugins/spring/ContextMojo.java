package org.kuali.maven.plugins.spring;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

public class ContextMojo extends AbstractMojo {

	@Override
	public void execute() throws MojoExecutionException {
		getLog().info("Hello world");
	}

}
