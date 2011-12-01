package org.kuali.maven.mojo;

/**
 * 
 */
public abstract class AbstractGenerateMojo extends BaseMojo {

	/**
	 * The location of the jenkins job config template
	 * 
	 * @parameter expression="${jenkins.template}" default-value="classpath:org/kuali/jenkins/jobs/template.xml"
	 * @required
	 */
	private String template;

	protected JobContext getJobContext(String type) {
		JobContext context = new JobContext();
		context.setWorkingDir(getWorkingDir());
		context.setProject(getProject());
		context.setType(type);
		context.setTemplate(template);
		context.setTimestampFormat(getTimestampFormat());
		return context;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}
