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
	 * @parameter expression="${jenkins.filename}"
	 */
	private String filename;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			JobContext context = generator.getJobContext(project, filename, type, template);
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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
