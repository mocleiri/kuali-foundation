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

import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

public abstract class AbstractSpringMojo extends AbstractMojo {

	@Component
	MavenProject project;

	/**
	 * If true, any PropertySource's supplied to the mojo are added to the configured Spring environment.
	 */
	@Parameter(property = "spring.addPropertySources", defaultValue = MavenConstants.DEFAULT_ADD_PROPERTY_SOURCES)
	boolean addPropertySources = new Boolean(MavenConstants.DEFAULT_ADD_PROPERTY_SOURCES);

	/**
	 * If true, Maven properties are injected into the context as a java.util.Properties bean
	 */
	@Parameter(property = "spring.injectMavenProperties", defaultValue = MavenConstants.DEFAULT_INJECT_MAVEN_PROPERTIES)
	boolean injectMavenProperties = new Boolean(MavenConstants.DEFAULT_INJECT_MAVEN_PROPERTIES);

	/**
	 * If true, the Maven project object is injected into the context
	 */
	@Parameter(property = "spring.injectMavenProject", defaultValue = MavenConstants.DEFAULT_INJECT_MAVEN_PROJECT)
	boolean injectMavenProject = new Boolean(MavenConstants.DEFAULT_INJECT_MAVEN_PROJECT);

	/**
	 * If true, the Maven project object is injected into the context
	 */
	@Parameter(property = "spring.injectMavenMojo", defaultValue = MavenConstants.DEFAULT_INJECT_MAVEN_MOJO)
	boolean injectMavenMojo = new Boolean(MavenConstants.DEFAULT_INJECT_MAVEN_MOJO);

	/**
	 * The name under which the Maven properties object is injected
	 */
	@Parameter(property = "spring.mavenPropertiesBeanName", defaultValue = MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	String mavenPropertiesBeanName = MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;

	/**
	 * The name under which the Maven project object is injected
	 */
	@Parameter(property = "spring.mavenProjectBeanName", defaultValue = MavenConstants.DEFAULT_MAVEN_PROJECT_BEAN_NAME)
	String mavenProjectBeanName = MavenConstants.DEFAULT_MAVEN_PROJECT_BEAN_NAME;

	/**
	 * The name under which this mojo is injected
	 */
	@Parameter(property = "spring.mavenMojoBeanName", defaultValue = MavenConstants.DEFAULT_MAVEN_MOJO_BEAN_NAME)
	String mavenMojoBeanName = MavenConstants.DEFAULT_MAVEN_MOJO_BEAN_NAME;

	/**
	 * The implementation of SpringService to use. If not supplied this defaults to <code>org.kuali.common.util.service.DefaultSpringService</code>
	 */
	@Parameter(property = "spring.springService")
	String springService = MavenConstants.DEFAULT_SPRING_SERVICE.getName();

	/**
	 * The implementation of SpringMojoService to use. If not supplied this defaults to <code>org.kuali.maven.plugins.spring.DefaultSpringMojoService</code>
	 */
	@Parameter(property = "spring.springMojoService")
	String springMojoService = MavenConstants.DEFAULT_SPRING_MOJO_SERVICE.getName();

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

	public String getMavenPropertiesBeanName() {
		return mavenPropertiesBeanName;
	}

	public void setMavenPropertiesBeanName(String mavenPropertiesBeanName) {
		this.mavenPropertiesBeanName = mavenPropertiesBeanName;
	}

	public String getMavenProjectBeanName() {
		return mavenProjectBeanName;
	}

	public void setMavenProjectBeanName(String mavenProjectBeanName) {
		this.mavenProjectBeanName = mavenProjectBeanName;
	}

	public String getMavenMojoBeanName() {
		return mavenMojoBeanName;
	}

	public void setMavenMojoBeanName(String mavenMojoBeanName) {
		this.mavenMojoBeanName = mavenMojoBeanName;
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

	public String getSpringMojoService() {
		return springMojoService;
	}

	public void setSpringMojoService(String springMojoService) {
		this.springMojoService = springMojoService;
	}

}
