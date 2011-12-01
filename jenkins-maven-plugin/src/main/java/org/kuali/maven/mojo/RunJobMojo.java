package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;

/**
 * @goal runjob
 * @requiresDependencyResolution test
 */
public class RunJobMojo extends AbstractCliMojo {

	/**
	 * The type of job to create
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	@Override
	public void execute() throws MojoExecutionException {
		setCmd(StringUtils.isBlank(getCmd()) ? "build" : getCmd());
		helper.executeCliJobCommand(this, type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}