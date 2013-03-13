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

import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.kuali.maven.plugins.spring.config.LoadMojoConfig;

@Mojo(name = "LoadMojo")
@Execute(goal = "load")
public class BaseMojo extends AbstractMojo {

	public static final String AUTOWIRED_MOJO_QUALIFIER = "mojo";
	public static final String MAVEN_PROPERTIES = "mavenProperties";
	public static final String MAVEN_PROJECT = "mavenProject";
	public static final String MAVEN_MOJO = "mavenMojo";

	@Component
	private MavenProject project;

	@Parameter(property = "spring.annotatedClass", required = true)
	private Class<?> annotatedClass;

	@Parameter(property = "spring.propertySourcesConfig")
	private Class<?> propertySourcesConfig;

	@Parameter(property = "spring.addPropertySources", defaultValue = "true")
	private boolean addPropertySources = true;

	@Parameter(property = "spring.annotatedClasses")
	private List<Class<?>> annotatedClasses;

	@Parameter(property = "spring.properties")
	private Properties properties;

	@Parameter(property = "spring.injectMavenProperties", defaultValue = "true")
	private boolean injectMavenProperties = true;

	@Parameter(property = "spring.injectMavenProject", defaultValue = "false")
	private boolean injectMavenProject = false;

	@Parameter(property = "spring.injectMavenMojo", defaultValue = "false")
	private boolean injectMavenMojo = false;

	@Parameter(property = "spring.mavenPropertiesBeanName", defaultValue = MAVEN_PROPERTIES)
	private String mavenPropertiesBeanName = MAVEN_PROPERTIES;

	@Parameter(property = "spring.mavenProjectBeanName", defaultValue = MAVEN_PROJECT)
	private String mavenProjectBeanName = MAVEN_PROJECT;

	@Parameter(property = "spring.mavenMojoBeanName", defaultValue = MAVEN_MOJO)
	private String mavenMojoBeanName = MAVEN_MOJO;

	@Parameter(property = "spring.serviceClass")
	private Class<? extends SpringService> serviceClass = DefaultSpringService.class;

	@Parameter(property = "spring.forceMojoExecution", defaultValue = "false")
	private boolean forceMojoExecution = false;

	@Parameter(property = "spring.skip", defaultValue = "false")
	private boolean skip = false;

	@Override
	public void execute() throws MojoExecutionException {
		SpringService service = LoadMojoService.newInstance(serviceClass);
		service.load(LoadMojoConfig.class, AUTOWIRED_MOJO_QUALIFIER, this);
	}

	protected String getDefaultAnnotatedClassname(MavenProject project) {
		StringBuilder sb = new StringBuilder();
		sb.append("classpath:");
		sb.append(Str.getPath(project.getGroupId()));
		sb.append("/");
		String artifactId = project.getArtifactId();
		String[] tokens = StringUtils.split(artifactId, "-");
		for (String token : tokens) {
			String capitalized = StringUtils.capitalizeFirstLetter(token);
			sb.append(capitalized);
		}
		return sb.toString();
	}

}
