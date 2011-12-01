package org.kuali.maven.mojo;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.mojo.context.MojoContext;

/**
 * Connect to a Jenkins server and retrieve XML documents describing one or more job configurations
 * 
 * @goal getjobs
 * @requiresDependencyResolution test
 */
public class GetJobsMojo extends AbstractCliMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="get-job"
	 * @required
	 */
	private String cmd;

	/**
	 * Comma separated list of job types
	 * 
	 * @parameter expression="${jenkins.types}" default-value="publish,unit,license,release"
	 */
	private String types;

	/**
	 * The explicit list of jobs to get. If names are provided, 'types' is ignored.
	 * 
	 * @parameter
	 */
	private List<String> names;

	@Override
	public void execute() throws MojoExecutionException {
		String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
		setStopOnError(false);
		getLog().info("");
		getLog().info("Working Dir - " + getWorkingDir().getAbsolutePath());
		getLog().info("");
		List<MojoContext> contexts = helper.getJobs(this, names, tokens);
		helper.handleResults(contexts);
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}