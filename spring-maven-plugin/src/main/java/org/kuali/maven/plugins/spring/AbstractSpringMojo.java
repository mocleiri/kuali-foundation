/**
 * Copyright 2011-2013 The Kuali Foundation
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

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringExecutable;
import org.kuali.common.util.spring.service.SpringContext;
import org.kuali.common.util.spring.service.SpringService;
import org.kuali.maven.plugins.spring.config.MojoExecutableConfig;

public abstract class AbstractSpringMojo extends AbstractMojo {

	@Component
	MavenProject project;

	/**
	 * If true, any PropertySource's supplied to the mojo are added to the configured Spring environment.
	 */
	@Parameter(property = "spring.addPropertySources", defaultValue = MavenConstants.DEFAULT_ADD_PROPERTY_SOURCES)
	boolean addPropertySources = new Boolean(MavenConstants.DEFAULT_ADD_PROPERTY_SOURCES);

	/**
	 * If true, any existing PropertySource's are removed before adding property sources supplied to the plugin
	 */
	@Parameter(property = "spring.removeExistingPropertySources", defaultValue = MavenConstants.DEFAULT_REMOVE_EXISTING_PROPERTY_SOURCES)
	boolean removeExistingPropertySources = new Boolean(MavenConstants.DEFAULT_REMOVE_EXISTING_PROPERTY_SOURCES);

	/**
	 * If true, Maven properties are injected into the context as a <code>java.util.Properties</code> bean under the name <code>mavenProperties</code>
	 */
	@Parameter(property = "spring.injectMavenProperties", defaultValue = MavenConstants.DEFAULT_INJECT_MAVEN_PROPERTIES)
	boolean injectMavenProperties = new Boolean(MavenConstants.DEFAULT_INJECT_MAVEN_PROPERTIES);

	/**
	 * If true, the Maven project object is injected into the context under the name <code>mavenProject</code>
	 */
	@Parameter(property = "spring.injectMavenProject", defaultValue = MavenConstants.DEFAULT_INJECT_MAVEN_PROJECT)
	boolean injectMavenProject = new Boolean(MavenConstants.DEFAULT_INJECT_MAVEN_PROJECT);

	/**
	 * If true, this Maven mojo object is injected into the context under the name <code>mavenMojo</code>
	 */
	@Parameter(property = "spring.injectMavenMojo", defaultValue = MavenConstants.DEFAULT_INJECT_MAVEN_MOJO)
	boolean injectMavenMojo = new Boolean(MavenConstants.DEFAULT_INJECT_MAVEN_MOJO);

	/**
	 * The implementation of SpringService to use. If not supplied this defaults to <code>org.kuali.common.util.service.DefaultSpringService</code>
	 */
	@Parameter(property = "spring.springService")
	String springService = MavenConstants.DEFAULT_SPRING_SERVICE.getName();

	/**
	 * The annotated Spring configuration class used to execute this mojo. This defaults to <code>org.kuali.maven.plugins.spring.config.MojoExecutableConfig</code>
	 */
	@Parameter(property = "spring.mojoExecutableConfig")
	String mojoExecutableConfig = MojoExecutableConfig.class.getName();

	/**
	 * Comma separated list of profiles to activate. By default a profile named <code>maven</code> is activated.
	 */
	@Parameter(property = "spring.profiles.active", defaultValue = org.kuali.common.util.maven.MavenConstants.SPRING_PROFILE_NAME)
	String activeProfiles = org.kuali.common.util.maven.MavenConstants.SPRING_PROFILE_NAME;

	/**
	 * Comma separated list of active profiles to include. By default, all active Maven profiles plus the profile "maven" are set as active Spring profiles.
	 */
	@Parameter(property = "spring.activeProfileIncludes")
	String activeProfileIncludes;

	/**
	 * Comma separated list of active profiles to exclude. By default, all active Maven profiles plus the profile "maven" are set as active Spring profiles.
	 */
	@Parameter(property = "spring.activeProfileExcludes", defaultValue = Constants.NONE)
	String activeProfileExcludes;

	/**
	 * Comma separated list of default profiles to include. Spring uses the profile "default" when no other active or default profiles are specified.
	 */
	@Parameter(property = "spring.defaultProfileIncludes")
	String defaultProfileIncludes;

	/**
	 * Comma separated list of default profiles to exclude. Spring uses the profile "default" when no other active or default profiles are specified.
	 */
	@Parameter(property = "spring.defaultProfileExcludes", defaultValue = Constants.NONE)
	String defaultProfileExcludes;

	/**
	 * Comma separated list of default profiles. Spring always uses a single profile named <code>default</code> if no default profiles are provided.
	 */
	@Parameter(property = "spring.profiles.default")
	String defaultProfiles;

	/**
	 * Extra properties supplied directly to the mojo
	 */
	@Parameter(property = "spring.properties")
	Properties properties;

	/**
	 * If true, the mojo will always execute
	 */
	@Parameter(property = "spring.forceMojoExecution", defaultValue = MavenConstants.DEFAULT_FORCE_MOJO_EXECUTION)
	boolean forceMojoExecution = new Boolean(MavenConstants.DEFAULT_FORCE_MOJO_EXECUTION);

	/**
	 * If true, the mojo execution will always be skipped (unless forceMojoExecution is true)
	 */
	@Parameter(property = "spring.skip", defaultValue = MavenConstants.DEFAULT_SKIP)
	boolean skip = new Boolean(MavenConstants.DEFAULT_SKIP);

	@Override
	public void execute() throws MojoExecutionException {
		// Keep log4j in sync with Maven logging with regards to debug mode
		configureLogging();

		// Create a map containing a reference to this mojo
		Map<String, Object> beans = Collections.singletonMap(MavenConstants.DEFAULT_MAVEN_MOJO_BEAN_NAME, (Object) this);

		// Get a config class
		Class<?> config = ReflectionUtils.getClass(mojoExecutableConfig);

		// Setup a context containing the mojo and config class
		SpringContext context = new SpringContext(beans, config);

		// Get a reference to a SpringService instance (this is DefaultSpringService unless overridden)
		SpringService service = ReflectionUtils.newInstance(springService);

		// Delegate execution to Spring
		new SpringExecutable(service, context).execute();
	}

	/**
	 * Returns true if the System property <code>spring.debug</code> is set to true OR debug logging has been enabled for the execution of this mojo (typically done via -X on the
	 * Maven command line)
	 */
	protected boolean isDebugLoggingEnabled() {
		if (getLog().isDebugEnabled()) {
			return true;
		}
		String key = "spring.debug";
		if (Boolean.getBoolean(key)) {
			return true;
		}
		String value = getProject().getProperties().getProperty(key);
		return Boolean.parseBoolean(value);
	}

	@Deprecated
	protected void configureLogging() {
		// We are not in debug mode, don't do anything
		if (!isDebugLoggingEnabled()) {
			return;
		}

		String log4jDebug = System.getProperty("log4j.debug");
		if (StringUtils.isBlank(log4jDebug)) {
			// This makes log4j show how it is initializing itself
			getLog().debug("Setting system property - [log4j.debug=true]");
			System.setProperty("log4j.debug", "true");
		}

		// Did they supply a log4j.configuration setting already?
		String log4jConfig = System.getProperty("log4j.configuration");
		if (StringUtils.isBlank(log4jConfig)) {
			// If not, use the log4jdebug.xml bundled with the plugin
			getLog().debug("Setting system property - [log4j.configuration=log4jdebug.xml]");
			System.setProperty("log4j.configuration", "log4jdebug.xml");
		} else {
			// If so, just use the one they specified
			getLog().debug("Using existing value for the system property log4j.configuration=[" + log4jConfig + "]");
		}
	}

	public MavenProject getProject() {
		return project;
	}

	public void setProject(MavenProject project) {
		this.project = project;
	}

	public boolean isAddPropertySources() {
		return addPropertySources;
	}

	public void setAddPropertySources(boolean addPropertySources) {
		this.addPropertySources = addPropertySources;
	}

	public boolean isInjectMavenProperties() {
		return injectMavenProperties;
	}

	public void setInjectMavenProperties(boolean injectMavenProperties) {
		this.injectMavenProperties = injectMavenProperties;
	}

	public boolean isInjectMavenProject() {
		return injectMavenProject;
	}

	public void setInjectMavenProject(boolean injectMavenProject) {
		this.injectMavenProject = injectMavenProject;
	}

	public boolean isInjectMavenMojo() {
		return injectMavenMojo;
	}

	public void setInjectMavenMojo(boolean injectMavenMojo) {
		this.injectMavenMojo = injectMavenMojo;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public boolean isForceMojoExecution() {
		return forceMojoExecution;
	}

	public void setForceMojoExecution(boolean forceMojoExecution) {
		this.forceMojoExecution = forceMojoExecution;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String getSpringService() {
		return springService;
	}

	public void setSpringService(String serviceClassName) {
		this.springService = serviceClassName;
	}

	public boolean isRemoveExistingPropertySources() {
		return removeExistingPropertySources;
	}

	public void setRemoveExistingPropertySources(boolean removeExistingPropertySources) {
		this.removeExistingPropertySources = removeExistingPropertySources;
	}

	public String getActiveProfiles() {
		return activeProfiles;
	}

	public void setActiveProfiles(String activeProfiles) {
		this.activeProfiles = activeProfiles;
	}

	public String getDefaultProfiles() {
		return defaultProfiles;
	}

	public void setDefaultProfiles(String defaultProfiles) {
		this.defaultProfiles = defaultProfiles;
	}

	public String getMojoExecutableConfig() {
		return mojoExecutableConfig;
	}

	public void setMojoExecutableConfig(String mojoExecutableConfig) {
		this.mojoExecutableConfig = mojoExecutableConfig;
	}

	public String getActiveProfileIncludes() {
		return activeProfileIncludes;
	}

	public void setActiveProfileIncludes(String activeProfileIncludes) {
		this.activeProfileIncludes = activeProfileIncludes;
	}

	public String getActiveProfileExcludes() {
		return activeProfileExcludes;
	}

	public void setActiveProfileExcludes(String activeProfileExcludes) {
		this.activeProfileExcludes = activeProfileExcludes;
	}

	public String getDefaultProfileIncludes() {
		return defaultProfileIncludes;
	}

	public void setDefaultProfileIncludes(String defaultProfileIncludes) {
		this.defaultProfileIncludes = defaultProfileIncludes;
	}

	public String getDefaultProfileExcludes() {
		return defaultProfileExcludes;
	}

	public void setDefaultProfileExcludes(String defaultProfileExcludes) {
		this.defaultProfileExcludes = defaultProfileExcludes;
	}

}
