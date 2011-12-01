package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;

/**
 * Delete a Jenkins job. The name of the job to delete is derived from information in the POM combined with 'type'
 * 
 * @goal deletejob
 * @requiresDependencyResolution test
 */
public class DeleteJobMojo extends AbstractCliMojo {

	/**
	 * The type of job to delete
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	@Override
	public void execute() throws MojoExecutionException {
		setCmd(StringUtils.isBlank(getCmd()) ? "delete-job" : getCmd());
		helper.executeCliJobCommand(this, type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}