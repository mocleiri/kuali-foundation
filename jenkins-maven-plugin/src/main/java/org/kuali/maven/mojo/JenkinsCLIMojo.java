package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal cli
 */
public class JenkinsCLIMojo extends AntMojo {

	/**
	 * The location of the Ant build.xml file. This can be a file on the file system, a file on the classpath, or any
	 * URL that Spring's resource loading can understand
	 * 
	 * @parameter expression="${jenkins.cliWrapper}"
	 *            default-value="classpath:org/kuali/jenkins/jobs/ant/cli-wrapper.xml"
	 */
	private String cliWrapper;

	@Override
	public void execute() throws MojoExecutionException {
	}

	public String getCliWrapper() {
		return cliWrapper;
	}

	public void setCliWrapper(String cliWrapper) {
		this.cliWrapper = cliWrapper;
	}

}
