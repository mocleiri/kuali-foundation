package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;

/**
 * @goal getjob
 * @requiresDependencyResolution test
 */
public class GetJobMojo extends AbstractCliMojo {

	/**
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 */
	private String type;

	/**
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