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
public class LoadMojo extends AbstractMojo {

	public static final String AUTOWIRED_QUALIFIER = "mojo";

	@Component
	private MavenProject project;

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

	@Parameter(property = "spring.annotatedClass", required = true)
	private Class<?> annotatedClass;

	@Parameter(property = "spring.propertySourcesConfig")
	private Class<?> propertySourcesConfig;
	private boolean addPropertySources = true;
	private List<String> locations;
	private Properties properties;
	private boolean injectMavenProperties = true;
	private boolean injectMavenProject = false;
	private boolean injectMavenMojo = false;
	private String mavenPropertiesBeanName = "mavenProperties";
	private String mavenProjectBeanName = "mavenProject";
	private String mavenMojoBeanName = "mavenMojo";
	private Class<? extends SpringService> serviceClass = DefaultSpringService.class;
	private boolean forceMojoExecution = false;
	private boolean skip = false;

	@Override
	public void execute() throws MojoExecutionException {
		SpringService service = LoadMojoService.newInstance(serviceClass);
		service.load(LoadMojoConfig.class, AUTOWIRED_QUALIFIER, this);
	}
}
