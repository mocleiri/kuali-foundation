package org.kuali.maven.plugins.spring;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.kuali.common.util.service.LocationService;
import org.kuali.common.util.spring.ToStringContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * Given a Spring context containing a bean that implements <code>org.kuali.common.util.execute.Executable</code> load the context, extract
 * the <code>Executable</code> bean and invoke its <code>execute()</code> method. If <code>filterContext</code> is true the context is
 * filtered using Maven properties before it is loaded.
 *
 * @goal execute
 */
public class ExecuteMojo extends AbstractMojo {
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	LocationService locationService = new LocationService();

	/**
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * @parameter expression="${spring.encoding}" default-value="${project.build.sourceEncoding}"
	 * @required
	 */
	private String encoding;

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
	 * If true <code>contextLocation</code> is filtered before being loaded
	 *
	 * @parameter expression="${spring.filterContext}" default-value="false"
	 * @required
	 */
	private boolean filterContext;

	/**
	 * List of Maven properties to include in the filtering process. All maven properties are used by default.
	 *
	 * @parameter
	 */
	private List<String> filterIncludes;

	/**
	 * List of Maven properties to exclude from the filtering process. All maven properties are used by default.
	 *
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
			ApplicationContext ctx = getApplicationContext();
			Executable executable = (Executable) ctx.getBean(executableBean);
			executable.execute();
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected ApplicationContext getApplicationContext() throws IOException {
		boolean exists = LocationUtils.exists(contextLocation);
		if (!exists) {
			throw new IllegalArgumentException(contextLocation + " does not exists");
		}
		if (!filterContext) {
			if (LocationUtils.isExistingFile(contextLocation)) {
				return new FileSystemXmlApplicationContext(contextLocation);
			} else {
				return new ClassPathXmlApplicationContext(contextLocation);
			}
		}
		Properties mavenProperties = getMavenProperties(project);
		String contextContent = getFilteredContextContent(mavenProperties, filterIncludes, filterExcludes);
		String filename = LocationUtils.getFilename(contextLocation);
		File newFile = new File(workingDir, filename);
		getLog().info("Creating [" + newFile.getAbsolutePath() + "]");
		FileUtils.write(newFile, contextContent);
		String url = LocationUtils.getURLString(newFile);
		return new FileSystemXmlApplicationContext(url);
	}

	protected Properties getMavenProperties(MavenProject project) {
		Properties properties = PropertyUtils.duplicate(project.getProperties());
		properties.setProperty("project.groupId", project.getGroupId());
		properties.setProperty("project.artifactId", project.getArtifactId());
		properties.setProperty("project.version", project.getVersion());
		properties.setProperty("project.basedir", LocationUtils.getCanonicalPath(project.getBasedir()));
		properties.setProperty("project.build.directory", project.getBuild().getDirectory());
		return properties;
	}

	protected String getFilteredContextContent(Properties mavenProperties, List<String> includes, List<String> excludes) {
		Properties global = PropertyUtils.getProperties(mavenProperties, GlobalPropertiesMode.BOTH);
		PropertyUtils.trim(global, includes, excludes);
		String originalContextContent = getContextContent(contextLocation, encoding);
		getLog().info("Filtering [" + contextLocation + "] using " + mavenProperties.size() + " properties");
		return helper.replacePlaceholders(originalContextContent, global);
	}

	protected String getContextContent(String contextLocation, String encoding) {
		ToStringContext context = new ToStringContext();
		context.setEncoding(encoding);
		context.setLocation(contextLocation);
		return locationService.toString(context);
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

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
