package org.kuali.maven.plugins.spring;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.Executable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @goal context
 */
public class ContextMojo extends AbstractMojo {

	/**
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * @parameter expression="${spring.contextLocation}" default-value="classpath:${project.artifactId}-context.xml"
	 * @required
	 */
	private String contextLocation;

	/**
	 * @parameter expression="${spring.executableBean}" default-value="executable"
	 * @required
	 */
	private String executableBean;

	/**
	 * @parameter expression="${spring.workingDir}" default-value="${project.build.directory}/spring"
	 * @required
	 */
	private File workingDir;

	@Override
	public void execute() throws MojoExecutionException {
		getLog().info("Hello world");
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(contextLocation);
			Executable executable = (Executable) ctx.getBean(executableBean);
			executable.execute();
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	public String getContextLocation() {
		return contextLocation;
	}

	public void setContextLocation(String contextLocation) {
		this.contextLocation = contextLocation;
	}

	public MavenProject getProject() {
		return project;
	}

	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	public void setProject(MavenProject project) {
		this.project = project;
	}

}
