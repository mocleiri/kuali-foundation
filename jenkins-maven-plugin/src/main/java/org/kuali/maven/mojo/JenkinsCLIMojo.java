package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal cli
 */
public class JenkinsCLIMojo extends AntMojo {

	/**
	 * @parameter expression="${jenkins.ant.wrapper}" default-value="classpath:org/kuali/jenkins/jobs/ant/cli-wrapper.xml"
	 */
	private String wrapper;

	/**
	 * @parameter expression="${jenkins.properties}" default-value="classpath:org/kuali/jenkins/jobs/jenkins.properties"
	 */
	private String properties;

	@Override
	public void execute() throws MojoExecutionException {
	}

	public String getWrapper() {
		return wrapper;
	}

	public void setWrapper(String wrapper) {
		this.wrapper = wrapper;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

}
