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
import org.apache.maven.plugins.annotations.Parameter;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.service.SpringService;
import org.kuali.maven.plugins.spring.config.XmlLoadMojoConfig;

public class XmlLoadMojo extends AbstractSpringMojo {

	public static final String DEFAULT_LOCATION = "classpath:${project.artifactId}-context.xml";
	public static final String DEFAULT_PROPERTY_SOURCES_LOCATION = "classpath:org/kuali/maven/plugins/spring/property-sources.xml";

	/**
	 * Location of a Spring context XML file. This can be a file on the local file system, or any URL Spring's Resource loading framework understands eg
	 * {@code classpath:my-context.xml}
	 */
	@Parameter(property = "spring.location", defaultValue = DEFAULT_LOCATION, required = true)
	String location;

	/**
	 * This context registers a single <code>PropertySource</code> bean backed by Maven properties
	 */
	@Parameter(defaultValue = DEFAULT_PROPERTY_SOURCES_LOCATION)
	String propertySourcesLocation = DEFAULT_PROPERTY_SOURCES_LOCATION;

	/**
	 * List of additional Spring context XML files to load (if any).
	 */
	@Parameter
	List<String> locations;

	@Override
	public void execute() throws MojoExecutionException {
		SpringService service = ReflectionUtils.newInstance(serviceClass);
		service.load(XmlLoadMojoConfig.class, MavenConstants.AUTOWIRED_MOJO_QUALIFIER, this);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPropertySourcesLocation() {
		return propertySourcesLocation;
	}

	public void setPropertySourcesLocation(String propertySourcesLocation) {
		this.propertySourcesLocation = propertySourcesLocation;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

}
