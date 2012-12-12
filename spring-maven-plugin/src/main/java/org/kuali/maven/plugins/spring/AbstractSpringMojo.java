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

	public static final String MAVEN_SPRING_PROPERTIES = "maven.spring.properties";

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
	 * Comma separated list of Maven properties to include. All properties are included by default.
	 *
	 * @parameter expression="${spring.include}"
	 */
	private String include;

	/**
	 * Comma separated list of Maven properties to exclude. No properties are excluded by default.
	 *
	 * @parameter expression="${spring.exclude}"
	 */
	private String exclude;

	/**
	 * List of Maven properties to include. All properties are included by default.
	 *
	 * @parameter
	 */
	private List<String> includes;

	/**
	 * List of Maven properties to exclude. No properties are excluded by default.
	 *
	 * @parameter
	 */
	private List<String> excludes;

	/**
	 * List of Maven properties to include in the context filtering process. All properties are included by default.
	 *
	 * @parameter
	 */
	private List<String> filterIncludes;

	/**
	 * List of Maven properties to exclude from the context filtering process. No properties are excluded by default.
	 *
	 * @parameter
	 */
	private List<String> filterExcludes;

	/**
	 * Additional properties to use when filtering the Spring context
	 *
	 * @parameter
	 */
	private Properties properties;

	/**
	 * If true, Maven properties are exported to the file system prior to the filtering and loading of the Spring context.
	 *
	 * @parameter expression="${spring.exportProperties}" default-value="true"
	 */
	private boolean exportProperties;

	/**
	 * The file to export properties to when <code>exportProperties</code> is <code>true</code>
	 *
	 * @parameter expression="${spring.exportedPropertiesFile}" default-value="${project.build.directory}/spring/maven.properties"
	 */
	private File exportedPropertiesFile;

	// This makes sure system properties and environment variables override properties provided elsewhere
	GlobalPropertiesMode globalPropertiesMode = Constants.DEFAULT_GLOBAL_PROPERTIES_MODE;
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	SpringService service = new DefaultSpringService();

	protected abstract void executeMojo();

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		handleProperties();
		executeMojo();
	}

	/**
	 * Always merge internal properties with project properties and standard Maven config.<br>
	 * If <code>exportProperties</code> is <code>true</code> add the property <code>maven.spring.properties</code> and then store them to
	 * the file system.
	 */
	protected void handleProperties() {
		this.properties = getMavenPropertiesForSpring();
		if (this.exportProperties) {
			String path = LocationUtils.getCanonicalPath(this.exportedPropertiesFile);
			this.properties.setProperty(MAVEN_SPRING_PROPERTIES, path);
			PropertyUtils.store(this.properties, this.exportedPropertiesFile, this.encoding);
		}
	}

	/**
	 * Return a properties object containing merged properties from project.getProperties(), the internal properties supplied to this mojo
	 * along with standard Maven properties. Project properties are overridden by properties supplied directly to the mojo, and standard
	 * Maven properties override everything.
	 */
	protected Properties getMavenPropertiesForSpring() {
		Properties props = new Properties();
		// Duplicate the existing project properties
		props.putAll(PropertyUtils.duplicate(PropertyUtils.toEmpty(project.getProperties())));
		// Add any properties supplied directly to the mojo
		props.putAll(PropertyUtils.toEmpty(properties));
		// Add Maven config that isn't present in project.getProperties()
		props.putAll(getStandardMavenProperties(project));
		// Return the merged properties
		return props;
	}

	protected Properties getStandardMavenProperties(MavenProject project) {
		Properties properties = new Properties();
		properties.setProperty("project.id", project.getId());
		properties.setProperty("project.groupId", project.getGroupId());
		properties.setProperty("project.artifactId", project.getArtifactId());
		properties.setProperty("project.version", project.getVersion());
		properties.setProperty("project.packaging", project.getPackaging());
		properties.setProperty("project.name", project.getName());
		properties.setProperty("project.description", project.getDescription());
		properties.setProperty("project.inceptionYear", project.getInceptionYear());
		properties.setProperty("project.ciManagement.system", project.getCiManagement().getSystem());
		properties.setProperty("project.ciManagement.url", project.getCiManagement().getUrl());
		properties.setProperty("project.issueManagement.system", project.getIssueManagement().getSystem());
		properties.setProperty("project.issueManagement.url", project.getIssueManagement().getUrl());
		properties.setProperty("project.basedir", LocationUtils.getCanonicalPath(project.getBasedir()));
		properties.setProperty("project.build.directory", project.getBuild().getDirectory());
		properties.setProperty("project.build.outputDirectory", project.getBuild().getOutputDirectory());
		properties.setProperty("project.build.testOutputDirectory", project.getBuild().getTestOutputDirectory());
		properties.setProperty("project.build.sourceDirectory", project.getBuild().getSourceDirectory());
		properties.setProperty("project.build.scriptSourceDirectory", project.getBuild().getScriptSourceDirectory());
		properties.setProperty("project.build.testSourceDirectory", project.getBuild().getTestSourceDirectory());
		return properties;
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

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getExclude() {
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public boolean isExportProperties() {
		return exportProperties;
	}

	public void setExportProperties(boolean exportProperties) {
		this.exportProperties = exportProperties;
	}

	public File getExportedPropertiesFile() {
		return exportedPropertiesFile;
	}

	public void setExportedPropertiesFile(File exportedPropertiesFile) {
		this.exportedPropertiesFile = exportedPropertiesFile;
	}

	@Override
	public GlobalPropertiesMode getGlobalPropertiesMode() {
		return globalPropertiesMode;
	}

	public void setGlobalPropertiesMode(GlobalPropertiesMode globalPropertiesMode) {
		this.globalPropertiesMode = globalPropertiesMode;
	}

	@Override
	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	public SpringService getService() {
		return service;
	}

	public void setService(SpringService service) {
		this.service = service;
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

}
