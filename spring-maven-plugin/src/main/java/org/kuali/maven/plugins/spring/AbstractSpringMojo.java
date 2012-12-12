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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
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
	 * List of properties to include. All properties are included by default.
	 *
	 * @parameter
	 */
	private List<String> exportIncludes;

	/**
	 * List of properties to exclude. No properties are excluded by default.
	 *
	 * @parameter
	 */
	private List<String> exportExcludes;

	/**
	 * List of properties to include. All properties are included by default.
	 *
	 * @parameter
	 */
	private List<String> filterIncludes;

	/**
	 * List of properties to exclude. No properties are excluded by default.
	 *
	 * @parameter
	 */
	private List<String> filterExcludes;

	/**
	 * Additional properties supplied directly to the mojo
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
	 * @parameter expression="${spring.exportPropertiesFile}" default-value="${project.build.directory}/spring/maven.properties"
	 */
	private File exportPropertiesFile;

	/**
	 * If <code>exportProperties</code> is true, the path to the exported properties file will be included as a property under this key
	 *
	 * @parameter expression="${spring.exportPropertiesFileProperty}" default-value="maven.spring.properties"
	 */
	private String exportPropertiesFileProperty;

	// The Maven convention is for system properties and environment variables to override properties provided elsewhere
	// This default setting follows that convention
	GlobalPropertiesMode globalPropertiesMode = Constants.DEFAULT_GLOBAL_PROPERTIES_MODE;
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	SpringService service = new DefaultSpringService();

	protected abstract void executeMojo();

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		executeMojo();
	}

	/**
	 * Order here is significant. Properties supplied directly to the mojo override properties from project.getProperties(). Standard Maven
	 * properties override everything.
	 */
	@Override
	public List<Properties> getPropertySources() {
		List<Properties> list = new ArrayList<Properties>();
		list.add(PropertyUtils.toEmpty(project.getProperties()));
		list.add(PropertyUtils.toEmpty(properties));
		list.add(PropertyUtils.toEmpty(MavenUtils.getStandardMavenProperties(project)));
		return list;
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
    public List<String> getExportIncludes() {
		return exportIncludes;
	}

	public void setExportIncludes(List<String> exportIncludes) {
		this.exportIncludes = exportIncludes;
	}

	@Override
    public List<String> getExportExcludes() {
		return exportExcludes;
	}

	public void setExportExcludes(List<String> exportExcludes) {
		this.exportExcludes = exportExcludes;
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

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
    public boolean isExportProperties() {
		return exportProperties;
	}

	public void setExportProperties(boolean exportProperties) {
		this.exportProperties = exportProperties;
	}

	@Override
    public File getExportPropertiesFile() {
		return exportPropertiesFile;
	}

	public void setExportPropertiesFile(File exportPropertiesFile) {
		this.exportPropertiesFile = exportPropertiesFile;
	}

	@Override
    public String getExportPropertiesFileProperty() {
		return exportPropertiesFileProperty;
	}

	public void setExportPropertiesFileProperty(String exportPropertiesFileProperty) {
		this.exportPropertiesFileProperty = exportPropertiesFileProperty;
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

	public MavenProject getProject() {
		return project;
	}
}
