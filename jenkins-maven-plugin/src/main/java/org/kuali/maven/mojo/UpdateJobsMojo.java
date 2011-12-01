package org.kuali.maven.mojo;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.mojo.context.MojoContext;

/**
 * @goal updatejobs
 * @requiresDependencyResolution test
 */
public class UpdateJobsMojo extends AbstractCliMojo {

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
		setCmd(StringUtils.isBlank(getCmd()) ? "update-job" : getCmd());
		setStopOnError(false);
		String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
		List<MojoContext> contexts = helper.pushJobsToJenkins(this, tokens);
		helper.handleResults(contexts);
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}