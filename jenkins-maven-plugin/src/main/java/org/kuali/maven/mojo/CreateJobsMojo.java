package org.kuali.maven.mojo;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.mojo.context.MojoContext;

/**
 * Create one ore more Jenkins jobs based on the information in the Maven pom and the 'types' provided.
 * 
 * @goal createjobs
 * @requiresDependencyResolution test
 */
public class CreateJobsMojo extends AbstractJobConfigMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="create-job"
	 * @required
	 */
	private String cmd;

	/**
	 * Comma delimited list of types of jobs to publish
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

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getCmd() {
		return cmd;
	}

}