package org.kuali.maven.mojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.mojo.context.MojoContext;

/**
 * @goal deletejobs
 * @requiresDependencyResolution test
 */
public class DeleteJobsMojo extends AbstractCliMojo {

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
	 * Comma delimited list of result codes to ignore. Result code of 1 means the job we are trying to delete does not exist.
	 * 
	 * @parameter expression="${jenkins.ignoreCodes}" default-value="1"
	 * @required
	 */
	private String ignoreCodes;

	@Override
	public void execute() throws MojoExecutionException {
		setCmd(StringUtils.isBlank(getCmd()) ? "delete-job" : getCmd());
		setStopOnError(false);
		String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
		List<MojoContext> contexts = helper.deleteJobs(this, tokens);
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

}