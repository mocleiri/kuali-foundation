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

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.service.SpringService;

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
 * For example, to inject the Maven version number into a Spring context:
 * </p>
 *
 * <pre>
 *  &lt;beans&gt;
 *   &lt;context:property-placeholder properties-ref="mavenProperties" /&gt;
 *   &lt;bean id="version" class="java.lang.String"&gt;
 *    &lt;constructor-arg value="${project.version}" /&gt;
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
	 * If true, this <code>mojo</code> object is injected into the Spring context
	 *
	 * @parameter expression="${spring.injectMojo}" default-value="false"
	 */
	private boolean injectMojo;

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
	 * The name to use when registering this <code>mojo</code> object as a bean in the Spring context.
	 *
	 * @parameter expression="${spring.mojoBeanName}" default-value="mojo"
	 */
	private String mojoBeanName;

	/**
	 * The implementation of {@code org.kuali.common.util.service.SpringService} to use
	 *
	 * @parameter expression="${spring.serviceClassname}" default-value="org.kuali.common.util.service.DefaultSpringService"
	 * @required
	 */
	private String serviceClassname;

	/**
	 * By default, execution of this mojo is automatically skipped for Maven projects with a packaging of type <code>pom</code>. If
	 * <code>forceMojoExecution</code> is <code>true</code> this mojo will always execute. <code>forceMojoExecution</code> overrides
	 * <code>skip</code>.
	 *
	 * @parameter expression="${spring.forceMojoExecution}" default-value="false"
	 */
	private boolean forceMojoExecution;

	/**
	 * By default, execution of this mojo is automatically skipped for Maven projects with a packaging of type <code>pom</code>. Set this
	 * parameter to <code>true</code> to explicitly skip executing this mojo for other scenarios. NOTE: <code>forceMojoExecution</code>
	 * overrides <code>skip</code>.
	 *
	 * @parameter expression="${spring.skip}" default-value="false"
	 */
	private boolean skip;

	@Override
	public void execute() throws MojoExecutionException {
		// Might be skipping execution altogether
		if (MavenUtils.skip(forceMojoExecution, skip, project.getPackaging())) {
			return;
		}

		// Combine mojo properties, project properties and internal maven properties into a Properties object
		Properties mavenProperties = MojoUtils.getMavenProperties(project, properties);

		// Combine the main context location with any optional locations
		List<String> contextLocations = CollectionUtils.combine(location, locations);

		// Assemble any beans we may be injecting
		List<Boolean> includes = Arrays.asList(injectProperties, injectProject, injectMojo);
		List<String> beanNames = CollectionUtils.getList(includes, Arrays.asList(propertiesBeanName, projectBeanName, mojoBeanName));
		List<Object> beans = CollectionUtils.getList(includes, Arrays.asList(mavenProperties, project, this));

		// Show what we are up to
		logConfiguration(mavenProperties, contextLocations);

		// Instantiate the implementation of SpringService we will be using
		SpringService service = MojoUtils.getService(serviceClassname);

		// Invoke the service to load the context and inject it with beans as appropriate
		service.load(contextLocations, beanNames, beans);
	}

	protected void logConfiguration(Properties props, List<String> contextLocations) {
		if (injectProperties) {
			getLog().info("Injecting " + props.size() + " Maven properties as a [" + props.getClass().getName() + "] bean under the id [" + propertiesBeanName + "]");
			getLog().debug("Displaying " + props.size() + " properties\n\n" + PropertyUtils.toString(props));
		}
		if (injectProject) {
			getLog().info("Injecting the Maven project as a [" + project.getClass().getName() + "] bean under the id [" + projectBeanName + "]");
		}
		if (injectMojo) {
			getLog().info("Injecting this mojo as a [" + this.getClass().getName() + "] bean under the id [" + mojoBeanName + "]");
		}
		if (contextLocations.size() > 1) {
			getLog().info("Loading " + contextLocations.size() + " Spring context files");
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

	public boolean isInjectMojo() {
		return injectMojo;
	}

	public void setInjectMojo(boolean injectMojo) {
		this.injectMojo = injectMojo;
	}

	public String getMojoBeanName() {
		return mojoBeanName;
	}

	public void setMojoBeanName(String mojoBeanName) {
		this.mojoBeanName = mojoBeanName;
	}

	public boolean isForceMojoExecution() {
		return forceMojoExecution;
	}

	public void setForceMojoExecution(boolean forceMojoExecution) {
		this.forceMojoExecution = forceMojoExecution;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
