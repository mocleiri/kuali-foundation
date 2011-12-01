package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Create a Jenkins job based on the information in the Maven pom and the 'type' provided.
 * 
 * @goal createjob
 * @requiresDependencyResolution test
 */
public class CreateJobMojo extends AbstractCliMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="create-job"
	 * @required
	 */
	private String cmd;

	/**
	 * The location of the jenkins job config template
	 * 
	 * @parameter expression="${jenkins.template}" default-value="classpath:org/kuali/jenkins/jobs/template.xml"
	 * @required
	 */
	private String template;

	/**
	 * The type of job to create
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	@Override
	public void execute() throws MojoExecutionException {
		helper.pushJobToJenkins(this, type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}