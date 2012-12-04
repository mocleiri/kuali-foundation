package org.kuali.maven.plugins.spring;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.FileUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @goal execute
 */
public class ExecuteMojo extends AbstractMojo {

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

	/**
	 * @parameter expression="${spring.filterContext}" default-value="false"
	 * @required
	 */
	private boolean filterContext;

	/**
	 * @parameter
	 */
	private List<String> filterIncludes;

	/**
	 * @parameter
	 */
	private List<String> filterExcludes;

	@Override
	public void execute() throws MojoExecutionException {
		getLog().info("Context Location - " + contextLocation);
		getLog().info("Filter Context - " + filterContext);
		try {
			FileUtils.forceMkdir(workingDir);
			getLog().info("Working Dir - " + LocationUtils.getCanonicalPath(workingDir));
			ApplicationContext ctx = new ClassPathXmlApplicationContext(contextLocation);
			Executable executable = (Executable) ctx.getBean(executableBean);
			executable.execute();
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected ApplicationContext getApplicationContext(String contextLocation, boolean filterContext, List<String> filterIncludes, List<String> filterExcludes) {
		boolean exists = LocationUtils.exists(contextLocation);
		if (!exists) {
			throw new IllegalArgumentException(contextLocation + " does not exists");
		}
		return null;
	}

	protected void filterContext(String contextLocation, Properties properties, List<String> includes, List<String> excludes) {
		Properties duplicate = PropertyUtils.getProperties(properties, GlobalPropertiesMode.BOTH);
		PropertyUtils.trim(duplicate, includes, excludes);
		String originalContextContent = LocationUtils.toString(contextLocation);
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

	public String getExecutableBean() {
		return executableBean;
	}

	public void setExecutableBean(String executableBean) {
		this.executableBean = executableBean;
	}

	public boolean isFilterContext() {
		return filterContext;
	}

	public void setFilterContext(boolean filterContext) {
		this.filterContext = filterContext;
	}

	public List<String> getFilterIncludes() {
		return filterIncludes;
	}

	public void setFilterIncludes(List<String> filterIncludes) {
		this.filterIncludes = filterIncludes;
	}

	public List<String> getFilterExcludes() {
		return filterExcludes;
	}

	public void setFilterExcludes(List<String> filterExcludes) {
		this.filterExcludes = filterExcludes;
	}

}
