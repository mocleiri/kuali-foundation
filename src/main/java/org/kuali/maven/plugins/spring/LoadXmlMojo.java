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

import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Load a Spring context from an XML file.
 */
@Mojo(name = MavenConstants.LOAD_XML_MOJO, threadSafe = true)
@Execute(goal = MavenConstants.LOAD_XML_MOJO)
public class LoadXmlMojo extends AbstractSpringMojo {

	/**
	 * Location of a Spring context XML file. This can be a file on the local file system, or any URL Spring's Resource loading framework understands eg <br>
	 * If not provided, a context based on ${project.groupId} + ${project.artifactId} is used.<br>
	 * Given a groupId of <code>org.kuali.rice</code> and an artifactId of <code>rice-sampleapp</code> this mojo will load
	 * <code>classpath:org/kuali/rice/spring/rice-sampleapp-context.xml</code>
	 */
	@Parameter(property = "spring.location")
	String location;

	/**
	 * This context registers a single <code>PropertySource</code> bean backed by Maven properties
	 */
	@Parameter(defaultValue = MavenConstants.DEFAULT_PROPERTY_SOURCES_LOCATION)
	String propertySourcesLocation = MavenConstants.DEFAULT_PROPERTY_SOURCES_LOCATION;

	/**
	 * List of additional Spring context XML files to load (if any).
	 */
	@Parameter
	List<String> locations;

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
