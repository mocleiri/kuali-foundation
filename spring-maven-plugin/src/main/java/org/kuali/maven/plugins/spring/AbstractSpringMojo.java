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
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public abstract class AbstractSpringMojo extends AbstractMojo {

	public static final String AUTOWIRED_MOJO_QUALIFIER = "mojo";
	public static final String DEFAULT_MAVEN_PROPERTIES_BEAN_NAME = "mavenProperties";
	public static final String DEFAULT_MAVEN_PROJECT_BEAN_NAME = "mavenProject";
	public static final String DEFAULT_MAVEN_MOJO_BEAN_NAME = "mavenMojo";
	public static final String DEFAULT_ADD_PROPERTY_SOURCES = "true";
	public static final String DEFAULT_INJECT_MAVEN_PROPERTIES = "true";
	public static final String DEFAULT_INJECT_MAVEN_PROJECT = "false";
	public static final String DEFAULT_INJECT_MAVEN_MOJO = "false";
	public static final String DEFAULT_FORCE_MOJO_EXECUTION = "false";
	public static final String DEFAULT_SKIP = "false";
	public static final Class<? extends SpringService> DEFAULT_SERVICE_CLASS = DefaultSpringService.class;

	@Component
	MavenProject project;

	@Parameter(property = "spring.addPropertySources", defaultValue = DEFAULT_ADD_PROPERTY_SOURCES)
	boolean addPropertySources = new Boolean(DEFAULT_ADD_PROPERTY_SOURCES);

	@Parameter(property = "spring.injectMavenProperties", defaultValue = DEFAULT_INJECT_MAVEN_PROPERTIES)
	boolean injectMavenProperties = new Boolean(DEFAULT_INJECT_MAVEN_PROPERTIES);

	@Parameter(property = "spring.injectMavenProject", defaultValue = DEFAULT_INJECT_MAVEN_PROJECT)
	boolean injectMavenProject = new Boolean(DEFAULT_INJECT_MAVEN_PROJECT);

	@Parameter(property = "spring.injectMavenMojo", defaultValue = DEFAULT_INJECT_MAVEN_MOJO)
	boolean injectMavenMojo = new Boolean(DEFAULT_INJECT_MAVEN_MOJO);

	@Parameter(property = "spring.mavenPropertiesBeanName", defaultValue = DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	String mavenPropertiesBeanName = DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;

	@Parameter(property = "spring.mavenProjectBeanName", defaultValue = DEFAULT_MAVEN_PROJECT_BEAN_NAME)
	String mavenProjectBeanName = DEFAULT_MAVEN_PROJECT_BEAN_NAME;

	@Parameter(property = "spring.mavenMojoBeanName", defaultValue = DEFAULT_MAVEN_MOJO_BEAN_NAME)
	String mavenMojoBeanName = DEFAULT_MAVEN_MOJO_BEAN_NAME;

	@Parameter(property = "spring.serviceClass")
	Class<? extends SpringService> serviceClass = DEFAULT_SERVICE_CLASS;

	@Parameter(property = "spring.properties")
	Properties properties;

	@Parameter(property = "spring.forceMojoExecution", defaultValue = DEFAULT_FORCE_MOJO_EXECUTION)
	boolean forceMojoExecution = new Boolean(DEFAULT_FORCE_MOJO_EXECUTION);

	@Parameter(property = "spring.skip", defaultValue = DEFAULT_SKIP)
	boolean skip = new Boolean(DEFAULT_SKIP);

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

	public Class<? extends SpringService> getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(Class<? extends SpringService> serviceClass) {
		this.serviceClass = serviceClass;
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

}
