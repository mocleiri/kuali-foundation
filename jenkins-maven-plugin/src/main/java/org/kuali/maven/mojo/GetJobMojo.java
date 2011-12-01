package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;

/**
 * Connect to a Jenkins server and retrieve an XML document describing the job configuration
 * 
 * @goal getjob
 * @requiresDependencyResolution test
 */
public class GetJobMojo extends AbstractCliMojo {

	/**
	 * The type of job.  This is combined with artifactId and majorVersion to produce a fully qualified job name.  eg "jenkins-maven-plugin-1.0-publish"
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 */
	private String type;

	/**
	 * The name of the job to retrieve XML for
	 * 
	 * @parameter expression="${jenkins.name}"
	 */
	private String name;

	@Override
	public void execute() throws MojoExecutionException {
		setCmd(StringUtils.isBlank(getCmd()) ? "get-job" : getCmd());
		helper.getJob(this, name, type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}