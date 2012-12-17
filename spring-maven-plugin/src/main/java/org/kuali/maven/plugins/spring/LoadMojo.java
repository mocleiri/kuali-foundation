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
import org.springframework.util.Assert;

/**
 * <p>
 * This mojo provides the ability to load a Spring context XML file. It uses a lightweight integration technique between Spring and Maven
 * centered around <code>java.util.Properties</code>. Prior to the Spring context being loaded, it is injected with a
 * <code>java.util.Properties</code> object containing the full set of Maven properties. The <code>java.util.Properties</code> object is
 * registered in the context as a bean under the name <code>mavenProperties</code>.
 * </p>
 * <p>
 * One common use of the injected Maven properties in a Spring context is for replacing property placeholders.
 * </p>
 * <p>
 * For example:
 * </p>
 *
 * <pre>
 *  &lt;beans&gt;
 *   &lt;context:property-placeholder properties-ref="mavenProperties" /&gt;
 *   &lt;bean id="artifactId" class="java.lang.String"&gt;
 *    &lt;constructor-arg value="${project.artifactId}" /&gt;
 *   &lt;/bean&gt;
 *  &lt;/beans&gt;
 * </pre>
 *
 * @goal load
 */
public class LoadMojo extends AbstractMojo {

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
	 * If true, Maven properties are injected into the Spring context as a <code>java.util.Properties</code> object
	 *
	 * @parameter expression="${spring.injectProperties}" default-value="true"
	 */
	private boolean injectProperties;

	/**
	 * If true, the <code>MavenProject</code> object is injected into the Spring context
	 *
	 * @parameter expression="${spring.injectProject}" default-value="false"
	 */
	private boolean injectProject;

	/**
	 * The name to use when registering the <code>java.util.Properties</code> object containing Maven properties as a bean in the Spring
	 * context.
	 *
	 * @parameter expression="${spring.propertiesBeanName}" default-value="mavenProperties"
	 */
	private String propertiesBeanName;

	/**
	 * The name to use when registering the <code>MavenProject</code> object as a bean in the Spring context.
	 *
	 * @parameter expression="${spring.projectBeanName}" default-value="project"
	 */
	private String projectBeanName;

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
		this.locations = combine(location, locations);

		// Show what we are up to
		logConfiguration();

		// Invoke the service to load the context
		SpringService service = getService(serviceClassname);
		service.load(locations, getBeanNames(), getBeans());
	}

	protected List<Object> getBeans() {
		List<Object> beans = new ArrayList<Object>();
		if (injectProperties) {
			beans.add(properties);
		}
		if (injectProject) {
			beans.add(project);
		}
		return beans;
	}

	protected List<String> getBeanNames() {
		List<String> beanNames = new ArrayList<String>();
		if (injectProperties) {
			beanNames.add(propertiesBeanName);
		}
		if (injectProject) {
			beanNames.add(projectBeanName);
		}
		return beanNames;
	}

	protected void logConfiguration() {
		if (injectProperties) {
			getLog().info("Injecting " + properties.size() + " Maven properties as a [" + properties.getClass().getName() + "] bean under the id [" + propertiesBeanName + "]");
			getLog().debug("Displaying " + properties.size() + " properties\n\n" + PropertyUtils.toString(properties));
		}
		if (injectProject) {
			getLog().info("Injecting the Maven project as a [" + project.getClass().getName() + "] bean under the id [" + projectBeanName + "]");
		}
		if (locations.size() > 1) {
			getLog().info("Loading " + locations.size() + " Spring context files");
		}
	}

	protected SpringService getService(String serviceClassname) {
		try {
			Class<?> serviceClass = Class.forName(serviceClassname);
			return (SpringService) serviceClass.newInstance();
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Unexpected error", e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("Unexpected error", e);
		} catch (InstantiationException e) {
			throw new IllegalStateException("Unexpected error", e);
		}
	}

	/**
	 * Return a combined list where <code>string</code> is always the first element and the Strings in the list are unique
	 */
	protected List<String> combine(String required, List<String> optional) {
		Assert.notNull(required);
		if (optional == null) {
			return Collections.singletonList(required);
		} else {
			List<String> combined = new ArrayList<String>();
			// Always insert required as the first element in the list
			combined.add(required);
			// Add the other strings but ensure the list does not already contain them
			for (String element : optional) {
				boolean doesNotContain = !combined.contains(element);
				Assert.isTrue(doesNotContain);
				combined.add(element);
			}
			return combined;
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

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

	public boolean isInjectProperties() {
		return injectProperties;
	}

	public void setInjectProperties(boolean injectProperties) {
		this.injectProperties = injectProperties;
	}

	public boolean isInjectProject() {
		return injectProject;
	}

	public void setInjectProject(boolean injectProject) {
		this.injectProject = injectProject;
	}

	public String getProjectBeanName() {
		return projectBeanName;
	}

	public void setProjectBeanName(String projectBeanName) {
		this.projectBeanName = projectBeanName;
	}

}
