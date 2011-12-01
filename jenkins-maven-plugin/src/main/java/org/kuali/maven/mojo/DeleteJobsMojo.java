package org.kuali.maven.mojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.mojo.context.MojoContext;

/**
 * Connect to a Jenkins server and delete one or more jobs
 * 
 * @goal deletejobs
 * @requiresDependencyResolution test
 */
public class DeleteJobsMojo extends AbstractCliMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="delete-job"
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
	 * Comma delimited list of types to delete
	 * 
	 * @parameter expression="${jenkins.types}" default-value="publish,unit,license,release"
	 * @required
	 */
	private String types;

	/**
	 * The explicit list of jobs to delete. If names are provided, 'types' is ignored.
	 * 
	 * @parameter
	 */
	private List<String> names;

	/**
	 * Comma delimited list of result codes to ignore. Result code of 1 means the job we are trying to delete does not exist.
	 * 
	 * @parameter expression="${jenkins.ignoreCodes}" default-value="1"
	 * @required
	 */
	private String ignoreCodes;

	@Override
	public void execute() throws MojoExecutionException {
		setStopOnError(false);
		String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
		List<MojoContext> contexts = helper.deleteJobs(this, names, tokens);
		List<Integer> ignoreCodeList = getIgnoreCodeList();
		helper.handleResults(contexts, ignoreCodeList);
	}

	protected List<Integer> getIgnoreCodeList() {
		List<Integer> ignoreCodeList = new ArrayList<Integer>();
		String[] tokens = PropertiesUtils.splitAndTrim(ignoreCodes, ",");
		for (String token : tokens) {
			ignoreCodeList.add(new Integer(token));
		}
		return ignoreCodeList;
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

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getIgnoreCodes() {
		return ignoreCodes;
	}

	public void setIgnoreCodes(String ignoreCodes) {
		this.ignoreCodes = ignoreCodes;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

}