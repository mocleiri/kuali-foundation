package org.kuali.maven.mojo;

import java.io.IOException;
import java.util.Properties;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.common.PropertiesUtils;

/**
 * @goal cli
 */
public class JenkinsCLIMojo extends AntMojo {
	PropertiesUtils propertiesUtils = new PropertiesUtils();

	/**
	 * The Maven project object
	 * 
	 * @parameter expression="${project}"
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * @parameter expression="${jenkins.ant.wrapper}" default-value="classpath:org/kuali/jenkins/jobs/ant/cli-wrapper.xml"
	 */
	private String wrapper;

	/**
	 * @parameter expression="${jenkins.properties}" default-value="classpath:org/kuali/jenkins/jobs/jenkins.properties"
	 */
	private String properties;

	/**
	 * @parameter expression="${jenkins.wrapperTarget}" default-value="cli"
	 */
	private String wrapperTarget;

	/**
	 * @parameter expression="${jenkins.cmd}" default-value="help"
	 */
	private String cmd;

	/**
	 * @parameter expression="${jenkins.server}" default-value="${project.ciManagement.url}"
	 */
	private String server;

	@Override
	public void execute() throws MojoExecutionException {
		try {
			Properties jenkinsProperties = propertiesUtils.getProperties(properties);
			project.getProperties().putAll(jenkinsProperties);
			project.getProperties().putAll(getPropertyMappings());
			setLocation(wrapper);
			setTarget(wrapperTarget);
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected error:", e);
		}
	}

	protected Properties getPropertyMappings() {
		Properties properties = new Properties();
		properties.setProperty("jenkins.server", server);
		properties.setProperty("jenkins.cli.cmd", cmd);
		return properties;
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
