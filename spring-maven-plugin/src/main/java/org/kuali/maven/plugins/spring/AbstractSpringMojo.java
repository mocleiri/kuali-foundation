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
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringContext;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 *
 */
public abstract class AbstractSpringMojo extends AbstractMojo implements SpringContext {

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

	/**
	 * Any properties supplied here are passed through to the Spring context
	 *
	 * @parameter
	 */
	private Properties properties;

	GlobalPropertiesMode globalPropertiesMode = Constants.DEFAULT_GLOBAL_PROPERTIES_MODE;
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	SpringService service = new DefaultSpringService();

	protected abstract void executeMojo();

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		setProperties(getMavenPropertiesForSpring());
		executeMojo();
	}

	protected Properties getMavenPropertiesForSpring() {
		Properties props = new Properties();
		// Duplicate the existing project properties
		props.putAll(PropertyUtils.duplicate(project.getProperties()));
		// Add any properties supplied directly to the mojo
		if (properties != null) {
			props.putAll(properties);
		}
		// Add standard Maven config that isn't present in project.getProperties()
		props.putAll(getStandardMavenProperties(project));
		// Return our merged properties object
		return props;
	}

	protected Properties getStandardMavenProperties(MavenProject project) {
		Properties properties = new Properties();
		properties.setProperty("project.groupId", project.getGroupId());
		properties.setProperty("project.artifactId", project.getArtifactId());
		properties.setProperty("project.version", project.getVersion());
		properties.setProperty("project.basedir", LocationUtils.getCanonicalPath(project.getBasedir()));
		properties.setProperty("project.build.directory", project.getBuild().getDirectory());
		return properties;
	}

	@Override
	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	@Override
	public GlobalPropertiesMode getGlobalPropertiesMode() {
		return globalPropertiesMode;
	}

	public void setGlobalPropertiesMode(GlobalPropertiesMode globalPropertiesMode) {
		this.globalPropertiesMode = globalPropertiesMode;
	}

	@Override
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public String getContextLocation() {
		return contextLocation;
	}

	public void setContextLocation(String contextLocation) {
		this.contextLocation = contextLocation;
	}

	@Override
	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	@Override
	public boolean isFilterContext() {
		return filterContext;
	}

	public void setFilterContext(boolean filterContext) {
		this.filterContext = filterContext;
	}

	@Override
	public List<String> getFilterIncludes() {
		return filterIncludes;
	}

	public void setFilterIncludes(List<String> filterIncludes) {
		this.filterIncludes = filterIncludes;
	}

	@Override
	public List<String> getFilterExcludes() {
		return filterExcludes;
	}

	public void setFilterExcludes(List<String> filterExcludes) {
		this.filterExcludes = filterExcludes;
	}

	public MavenProject getProject() {
		return project;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public SpringService getService() {
		return service;
	}

	public void setService(SpringService service) {
		this.service = service;
	}

}
