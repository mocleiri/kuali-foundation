package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal antrun
 */
public class JenkinsAntRunMojo extends AbstractAntRunMojo {

	@Override
	public void execute() throws MojoExecutionException {
		getLog().info("hello world");
		super.execute();
	}

}
