package org.kuali.maven.mojo;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.mojo.context.MojoContext;

/**
 * @goal getjobs
 * @requiresDependencyResolution test
 */
public class GetJobsMojo extends AbstractCliMojo {

	/**
	 * Comma separated list of job types
	 * 
	 * @parameter expression="${jenkins.types}" default-value="publish,unit,license,release"
	 */
	private String types;

	/**
	 * The explicit list of jobs to get
	 * 
	 * @parameter
	 */
	private List<String> names;

	@Override
	public void execute() throws MojoExecutionException {
		setCmd(StringUtils.isBlank(getCmd()) ? "get-job" : getCmd());
		String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
		setStopOnError(false);
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

}