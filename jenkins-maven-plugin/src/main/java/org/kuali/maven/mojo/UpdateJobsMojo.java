package org.kuali.maven.mojo;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.mojo.context.MojoContext;

/**
 * Contact a Jenkins server and update one or more existing jobs configuration
 * 
 * @goal updatejobs
 * @requiresDependencyResolution test
 */
public class UpdateJobsMojo extends AbstractJobConfigMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="update-job"
	 * @required
	 */
	private String cmd;

	/**
	 * Comma delimited list of types of jobs to update
	 * 
	 * @parameter expression="${jenkins.types}" default-value="publish,unit,license,release"
	 * @required
	 */
	private String types;

	@Override
	public void execute() throws MojoExecutionException {
		setStopOnError(false);
		String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
		List<MojoContext> contexts = helper.pushJobsToJenkins(this, tokens);
		helper.handleResults(contexts);
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}