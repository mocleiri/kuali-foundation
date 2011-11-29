package org.kuali.maven.mojo;

import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * @goal generatejobconfig
 */
public class GenerateJobConfigMojo extends AbstractMojo {
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
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	/**
	 * @parameter expression="${jenkins.template}" default-value="classpath:org/kuali/jenkins/jobs/template.xml"
	 * @required
	 */
	private String template;

	/**
	 * @parameter expression="${jenkins.configDir}" default-value="${project.build.directory}/jenkins"
	 */
	private String configDir;

	/**
	 * @parameter expression="${jenkins.timestampFormat}" default-value="yyyy-MM-dd HH:mm:ss z"
	 * @required
	 */
	private String timestampFormat;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			JobContext context = generator.getJobContext(project, configDir, type, template);
			getLog().info("Generating: " + context.getFilename());
			generator.generate(context);
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	public MavenProject getProject() {
		return project;
	}

	public void setProject(MavenProject project) {
		this.project = project;
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
