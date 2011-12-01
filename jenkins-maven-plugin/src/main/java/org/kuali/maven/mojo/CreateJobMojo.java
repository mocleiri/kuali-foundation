package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.mojo.context.MojoContext;

/**
 * @goal createjob
 * @requiresDependencyResolution test
 */
public class CreateJobMojo extends AbstractCliMojo {

	/**
	 * The location of the jenkins job config template
	 * 
	 * @parameter expression="${jenkins.template}" default-value="classpath:org/kuali/jenkins/jobs/template.xml"
	 * @required
	 */
	private String template;

	/**
	 * The type of job to create
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	@Override
	public void execute() throws MojoExecutionException {
		setCmd(StringUtils.isBlank(getCmd()) ? "create-job" : getCmd());
		MojoContext context = helper.generate(this, type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}