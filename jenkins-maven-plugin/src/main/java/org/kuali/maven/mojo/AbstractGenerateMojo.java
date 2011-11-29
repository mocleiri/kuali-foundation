package org.kuali.maven.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

/**
 */
public abstract class AbstractGenerateMojo extends AbstractMojo {
	Generator generator = new Generator();

	/**
	 * The Maven project this plugin runs in.
	 * 
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

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
	 */
	private String configDir;

	/**
	 * The format for timestamp displays
	 * 
	 * @parameter expression="${jenkins.timestampFormat}" default-value="yyyy-MM-dd HH:mm:ss z"
	 * @required
	 */
	private String timestampFormat;

	protected JobContext getJobContext(String type) {
		JobContext context = new JobContext();
		context.setConfigDir(configDir);
		context.setProject(project);
		context.setType(type);
		context.setTemplate(template);
		context.setTimestampFormat(timestampFormat);
		return context;

	}

	public MavenProject getProject() {
		return project;
	}

	public void setProject(MavenProject project) {
		this.project = project;
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

	public String getTimestampFormat() {
		return timestampFormat;
	}

	public void setTimestampFormat(String timestampFormat) {
		this.timestampFormat = timestampFormat;
	}
}
