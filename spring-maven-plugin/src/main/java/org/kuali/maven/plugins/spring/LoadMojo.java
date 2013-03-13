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
import org.kuali.maven.plugins.spring.config.XmlLoadMojoConfig;

@Mojo(name = "LoadMojo")
@Execute(goal = "load")
public class LoadMojo extends AbstractSpringMojo {

	@Parameter(property = "spring.annotatedClass", required = true)
	private Class<?> annotatedClass;

	@Parameter(property = "spring.propertySourcesConfig")
	private Class<?> propertySourcesConfig;

	@Parameter(property = "spring.annotatedClasses")
	private List<Class<?>> annotatedClasses;

	@Override
	public void execute() throws MojoExecutionException {
		SpringService service = ReflectionUtils.newInstance(serviceClass);
		service.load(XmlLoadMojoConfig.class, AUTOWIRED_MOJO_QUALIFIER, this);
	}

}
