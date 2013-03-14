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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Load a Spring context from an annotated class
 */
@Mojo(name = MavenConstants.LOAD_MOJO)
@Execute(goal = MavenConstants.LOAD_MOJO)
public class LoadMojo extends AbstractSpringMojo {

	/**
	 * The annotated class containing the Spring configuration. If not supplied, a class based on ${project.groupId} + ${project.artifactId} is used. Given a groupId of
	 * <code>org.kuali.rice</code> and an artifactId of <code>rice-sampleapp</code>, this mojo attempts to use <code>org.kuali.rice.spring.RiceSampleappConfig</code>
	 */
	@Parameter(property = "spring.annotatedClass", required = true)
	Class<?> annotatedClass;

	/**
	 * The annotated class containing <code>PropertySource</code> definitions. By default, this gets set to <code>org.kuali.maven.plugins.spring.config.PropertySourcesConfig</code>
	 * which registers a single PropertySource object backed by the full set of Maven properties.
	 */
	@Parameter(property = "spring.propertySourcesConfig")
	Class<?> propertySourcesConfig = MavenConstants.DEFAULT_PROPERTY_SOURCES_CONFIG;

	/**
	 * Additional annotated classes containing Spring configuration.
	 */
	@Parameter(property = "spring.annotatedClasses")
	List<Class<?>> annotatedClasses;

	@Override
	public void execute() throws MojoExecutionException {
		// Delegate execution to Spring
		new DefaultSpringMojoService().execute(this);
	}

	public Class<?> getAnnotatedClass() {
		return annotatedClass;
	}

	public void setAnnotatedClass(Class<?> annotatedClass) {
		this.annotatedClass = annotatedClass;
	}

	public Class<?> getPropertySourcesConfig() {
		return propertySourcesConfig;
	}

	public void setPropertySourcesConfig(Class<?> propertySourcesConfig) {
		this.propertySourcesConfig = propertySourcesConfig;
	}

	public List<Class<?>> getAnnotatedClasses() {
		return annotatedClasses;
	}

	public void setAnnotatedClasses(List<Class<?>> annotatedClasses) {
		this.annotatedClasses = annotatedClasses;
	}

}
