/**
 * Copyright 2011-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.kuali.common.util.service.LocationService;
import org.kuali.common.util.spring.ToStringContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * Load Spring context given a <code>contextLocation</code>. If <code>filterContext</code> is true the context is filtered using Maven
 * properties before it is loaded.
 *
 * @goal load
 */
public class LoadMojo extends AbstractMojo {
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	LocationService locationService = new LocationService();

	/**
	 * Maven project
	 *
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * Character encoding for the context XML file
	 *
	 * @parameter expression="${spring.encoding}" default-value="${project.build.sourceEncoding}"
	 */
	private String encoding;

	/**
	 * Location of a Spring context XML file. This can be any URL Spring's Resource loading framework understands eg
	 * <code>classpath:mycontext.xml</code>
	 *
	 * @parameter expression="${spring.contextLocation}" default-value="classpath:${project.artifactId}-context.xml"
	 * @required
	 */
	private String contextLocation;

	/**
	 * Spring managed bean that implements <code>Executable</code>
	 *
	 * @parameter expression="${spring.executableBean}" default-value="executable"
	 * @required
	 */
	private String executableBean;

	/**
	 * Working directory for the plugin.
	 *
	 * @parameter expression="${spring.workingDir}" default-value="${project.build.directory}/spring"
	 */
	private File workingDir;

	/**
	 * If true <code>contextLocation</code> is filtered before being loaded
	 *
	 * @parameter expression="${spring.filterContext}" default-value="false"
	 */
	private boolean filterContext;

	/**
	 * List of Maven properties to include in the filtering process when <code>filterContext</code> is <code>true</code>. If nothing is
	 * provided here, all maven properties are used.
	 *
	 * @parameter
	 */
	private List<String> filterIncludes;

	/**
	 * List of Maven properties to exclude from the filtering process.
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
			ApplicationContext ctx = loadApplicationContext();
			getLog().info("Loaded " + ctx.getDisplayName());
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected void showProperties(ApplicationContext ctx) {
		Properties properties = (Properties) ctx.getBean("properties");
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			getLog().info(key + "=" + Str.flatten(value));
		}
	}

	protected ApplicationContext loadApplicationContext() throws IOException {
		boolean exists = LocationUtils.exists(contextLocation);
		if (!exists) {
			throw new IllegalArgumentException(contextLocation + " does not exists");
		}
		if (!filterContext) {
			if (LocationUtils.isExistingFile(contextLocation)) {
				File file = new File(contextLocation);
				String url = LocationUtils.getURLString(file);
				return new FileSystemXmlApplicationContext(url);
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
		getLog().info("Filtering [" + contextLocation + "] using " + global.size() + " properties");
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
