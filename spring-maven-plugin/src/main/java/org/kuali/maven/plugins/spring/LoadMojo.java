/**
 * Copyright 2011-2012 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringContext;

/**
 * <p>
 * This mojo provides the ability to load a Spring context XML file. It uses a lightweight integration technique between Spring and Maven
 * centered around <code>java.util.Properties</code>. Prior to the Spring context being loaded, it is injected with a
 * <code>java.util.Properties</code> object containing the full set of Maven properties. The <code>java.util.Properties</code> object is
 * registered in the context as a bean under the name <code>maven.properties</code>.
 * </p>
 * <p>
 * One typical use of the injected Maven properties in a Spring context is for replacing property placeholders.
 * </p>
 * <p>
 * For example:
 * </p>
 *
 * <pre>
 *  &lt;beans&gt;
 *
 *   &lt;context:property-placeholder properties-ref="maven.properties" /&gt;
 *
 *   &lt;bean id="artifactId" class="java.lang.String"&gt;
 *    &lt;constructor-arg value="${project.artifactId}" /&gt;
 *   &lt;/bean&gt;
 *
 *  &lt;/beans&gt;
 * </pre>
 *
 * @goal load
 */
public class LoadMojo extends AbstractMojo implements SpringContext {

	/**
	 * Maven project
	 *
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * Location of a Spring context XML file. This can be a file on the local file system, or any URL Spring's Resource loading framework
	 * understands eg {@code classpath:my-context.xml}
	 *
	 * @parameter expression="${spring.location}" default-value="classpath:${project.artifactId}-context.xml"
	 * @required
	 */
	private String location;

	/**
	 * List of additional Spring context XML files to load (if any).
	 *
	 * @parameter
	 */
	private List<String> locations;

	/**
	 * Additional properties to supply to the Spring context.
	 *
	 * @parameter
	 */
	private Properties properties;

	/**
	 * The name to use when registering the <code>java.util.Properties</code> object containing Maven properties as a bean in the Spring
	 * context.
	 *
	 * @parameter expression="${spring.propertiesBeanName}" default-value="maven.properties"
	 * @required
	 */
	private String propertiesBeanName;

	/**
	 * The implementation of {@code org.kuali.common.util.service.SpringService} to use
	 *
	 * @parameter expression="${spring.serviceClassname}" default-value="org.kuali.common.util.service.DefaultSpringService"
	 * @required
	 */
	private String serviceClassname;

	@Override
	public void execute() throws MojoExecutionException {

		// The ordering here is significant.
		// Properties supplied directly to the mojo override properties from project.getProperties()
		// But, internal Maven properties need to always win.
		// ${project.artifactId} needs to always faithfully represent the correct artifactId
		this.properties = PropertyUtils.combine(project.getProperties(), properties, MavenUtils.getInternalProperties(project));

		// Combine the list with the single value
		this.locations = combine(locations, location);

		// Invoke the service to load the context
		invokeService(serviceClassname);
	}

	protected void invokeService(String serviceClassname) {
		try {
			Class<?> serviceClass = Class.forName(serviceClassname);
			SpringService service = (SpringService) serviceClass.newInstance();
			service.load(this);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Unexpected error", e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Unexpected error", e);
		} catch (InstantiationException e) {
			throw new IllegalStateException("Unexpected error", e);
		}
	}

	protected List<String> combine(List<String> locations, String location) {
		if (locations == null) {
			return Collections.singletonList(location);
		} else {
			// Insert location as the first element in the list
			List<String> combined = new ArrayList<String>(locations);
			combined.add(0, location);
			return combined;
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String getPropertiesBeanName() {
		return propertiesBeanName;
	}

	public void setPropertiesBeanName(String propertiesBeanName) {
		this.propertiesBeanName = propertiesBeanName;
	}

	public String getServiceClassname() {
		return serviceClassname;
	}

	public void setServiceClassname(String serviceClassname) {
		this.serviceClassname = serviceClassname;
	}

	public MavenProject getProject() {
		return project;
	}

}
