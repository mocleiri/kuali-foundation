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

public abstract class BaseMojo extends AbstractMojo {

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
	private MavenProject project;

	@Parameter(property = "spring.addPropertySources", defaultValue = DEFAULT_ADD_PROPERTY_SOURCES)
	private boolean addPropertySources = new Boolean(DEFAULT_ADD_PROPERTY_SOURCES);

	@Parameter(property = "spring.injectMavenProperties", defaultValue = DEFAULT_INJECT_MAVEN_PROPERTIES)
	private boolean injectMavenProperties = new Boolean(DEFAULT_INJECT_MAVEN_PROPERTIES);

	@Parameter(property = "spring.injectMavenProject", defaultValue = DEFAULT_INJECT_MAVEN_PROJECT)
	private boolean injectMavenProject = new Boolean(DEFAULT_INJECT_MAVEN_PROJECT);

	@Parameter(property = "spring.injectMavenMojo", defaultValue = DEFAULT_INJECT_MAVEN_MOJO)
	private boolean injectMavenMojo = new Boolean(DEFAULT_INJECT_MAVEN_MOJO);

	@Parameter(property = "spring.mavenPropertiesBeanName", defaultValue = DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	private String mavenPropertiesBeanName = DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;

	@Parameter(property = "spring.mavenProjectBeanName", defaultValue = DEFAULT_MAVEN_PROJECT_BEAN_NAME)
	private String mavenProjectBeanName = DEFAULT_MAVEN_PROJECT_BEAN_NAME;

	@Parameter(property = "spring.mavenMojoBeanName", defaultValue = DEFAULT_MAVEN_MOJO_BEAN_NAME)
	private String mavenMojoBeanName = DEFAULT_MAVEN_MOJO_BEAN_NAME;

	@Parameter(property = "spring.serviceClass")
	private Class<? extends SpringService> serviceClass = DEFAULT_SERVICE_CLASS;

	@Parameter(property = "spring.properties")
	private Properties properties;

	@Parameter(property = "spring.forceMojoExecution", defaultValue = DEFAULT_FORCE_MOJO_EXECUTION)
	private boolean forceMojoExecution = new Boolean(DEFAULT_FORCE_MOJO_EXECUTION);

	@Parameter(property = "spring.skip", defaultValue = DEFAULT_SKIP)
	private boolean skip = new Boolean(DEFAULT_SKIP);

}
