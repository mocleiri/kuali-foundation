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
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.service.SpringService;
import org.kuali.maven.plugins.spring.config.MojoConfig;
import org.kuali.maven.plugins.spring.config.PropertySourcesConfig;

/**
 * Load a Spring context from an annotated Java class
 */
@Mojo(name = "load")
@Execute(goal = "load")
public class LoadMojo extends AbstractSpringMojo {

	/**
	 * The annotated Java class containing the Spring configuration.
	 */
	@Parameter(property = "spring.annotatedClass", required = true)
	Class<?> annotatedClass;

	/**
	 * The annotated Java class containing <code>PropertySource</code> definitions. By default, this gets set to
	 * <code>org.kuali.maven.plugins.spring.config.PropertySourcesConfig</code>. <code>PropertySourcesConfig</code> registers a PropertySource object backed by the full set of
	 * Maven properties.
	 */
	@Parameter(property = "spring.propertySourcesConfig")
	Class<?> propertySourcesConfig = PropertySourcesConfig.class;

	/**
	 * Additional annotated Java classes containing Spring configuration.
	 */
	@Parameter(property = "spring.annotatedClasses")
	List<Class<?>> annotatedClasses;

	@Override
	public void execute() throws MojoExecutionException {
		SpringService service = ReflectionUtils.newInstance(serviceClass);
		service.load(MojoConfig.class, MavenConstants.AUTOWIRED_MOJO_QUALIFIER, this);
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
