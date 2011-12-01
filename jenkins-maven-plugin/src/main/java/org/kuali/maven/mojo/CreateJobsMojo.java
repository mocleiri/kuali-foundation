package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.PropertiesUtils;

/**
 * @goal createjobs
 * @requiresDependencyResolution test
 */
public class CreateJobsMojo extends AbstractCliMojo {

	/**
	 * The location of the jenkins job config template
	 * 
	 * @parameter expression="${jenkins.template}" default-value="classpath:org/kuali/jenkins/jobs/template.xml"
	 * @required
	 */
	private String template;

	/**
	 * Comma delimited list of types of jobs to publish
	 * 
	 * @parameter expression="${jenkins.types}" default-value="publish,unit,license,release"
	 * @required
	 */
	private String types;

	@Override
	public void execute() throws MojoExecutionException {
		setCmd(StringUtils.isBlank(getCmd()) ? "create-job" : getCmd());
		setStopOnError(false);
		String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
		helper.pushJobsToJenkins(this, tokens);
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}