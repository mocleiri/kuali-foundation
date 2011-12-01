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

	/**
	 * The directory where the job config will be generated
	 * 
	 * @parameter expression="${jenkins.configDir}" default-value="${project.build.directory}/jenkins"
	 * @required
	 */
	private String configDir;

	protected JobContext getJobContext(String type) {
		JobContext context = new JobContext();
		context.setConfigDir(configDir);
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

	public String getConfigDir() {
		return configDir;
	}

	public void setConfigDir(String configDir) {
		this.configDir = configDir;
	}

}
