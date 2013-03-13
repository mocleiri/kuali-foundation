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
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.service.DefaultSpringService;

/**
 * <p>
 * This mojo provides the ability to load a Spring context XML file. It uses a lightweight integration technique between Spring and Maven centered around
 * <code>java.util.Properties</code>. Prior to the Spring context being loaded, it is injected with a <code>java.util.Properties</code> object containing the full set of Maven
 * properties. The <code>java.util.Properties</code> object is registered in the context as a bean under the name <code>mavenProperties</code>.
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
	 * Location of a Spring context XML file. This can be a file on the local file system, or any URL Spring's Resource loading framework understands eg
	 * {@code classpath:my-context.xml}
	 * 
	 * @parameter property="${spring.location}" default-value="classpath:${project.artifactId}-context.xml"
	 * @required
	 */
	private String location;

	/**
	 * This context registers a single <code>PropertySource</code> bean backed by Maven properties
	 * 
	 * @parameter property="${spring.propertySourcesLocation}" default-value="classpath:org/kuali/maven/plugins/spring/property-sources.xml"
	 * @required
	 */
	private String propertySourcesLocation = "classpath:org/kuali/maven/plugins/spring/property-sources.xml";

	/**
	 * If true, <code>propertySourcesLocation</code> is loaded. Any beans from that context that implement <code>PropertySource</code> are added as property sources.
	 * 
	 * @parameter property="${spring.addPropertySources}" default-value="true"
	 */
	private boolean addPropertySources = true;

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
	 * @parameter property="${spring.injectMavenProperties}" default-value="true"
	 */
	private boolean injectMavenProperties = true;

	/**
	 * If true, the <code>MavenProject</code> object is injected into the Spring context
	 * 
	 * @parameter property="${spring.injectMavenProject}" default-value="false"
	 */
	private boolean injectMavenProject = false;

	/**
	 * If true, this <code>LoadMojo</code> object is injected into the Spring context
	 * 
	 * @parameter property="${spring.injectMavenMojo}" default-value="false"
	 */
	private boolean injectMavenMojo = false;

	/**
	 * The name to use when registering the <code>java.util.Properties</code> object containing Maven build properties as a bean in the Spring context.
	 * 
	 * @parameter property="${spring.mavenPropertiesBeanName}" default-value="mavenProperties"
	 */
	private String mavenPropertiesBeanName = "mavenProperties";

	/**
	 * The name to use when registering the <code>MavenProject</code> object as a bean in the Spring context.
	 * 
	 * @parameter property="${spring.mavenProjectBeanName}" default-value="mavenProject"
	 */
	private String mavenProjectBeanName = "mavenProject";

	/**
	 * The name to use when registering this <code>mojo</code> object as a bean in the Spring context.
	 * 
	 * @parameter property="${spring.mojoBeanName}" default-value="mavenMojo"
	 */
	private String mavenMojoBeanName = "mavenMojo";

	/**
	 * The implementation of {@code org.kuali.common.util.service.SpringService} to use
	 * 
	 * @parameter property="${spring.serviceClassname}" default-value="org.kuali.common.util.service.DefaultSpringService"
	 * @required
	 */
	private String serviceClassname = DefaultSpringService.class.getName();

	/**
	 * By default, execution of this mojo is automatically skipped for Maven projects with a packaging of type <code>pom</code>. If <code>forceMojoExecution</code> is
	 * <code>true</code> this mojo will always execute. <code>forceMojoExecution</code> overrides <code>skip</code>.
	 * 
	 * @parameter property="${spring.forceMojoExecution}" default-value="false"
	 */
	private boolean forceMojoExecution = false;

	/**
	 * By default, execution of this mojo is automatically skipped for Maven projects with a packaging of type <code>pom</code>. Set this parameter to <code>true</code> to
	 * explicitly skip executing this mojo for other scenarios. NOTE: <code>forceMojoExecution</code> overrides <code>skip</code>.
	 * 
	 * @parameter property="${spring.skip}" default-value="false"
	 */
	private boolean skip = false;

	@Override
	public void execute() throws MojoExecutionException {
		LoadMojoService service = new LoadMojoService();
		service.execute(this);
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

	public boolean isInjectMavenProperties() {
		return injectMavenProperties;
	}

	public void setInjectMavenProperties(boolean injectMavenProperties) {
		this.injectMavenProperties = injectMavenProperties;
	}

	public boolean isInjectMavenProject() {
		return injectMavenProject;
	}

	public void setInjectMavenProject(boolean injectMavenProject) {
		this.injectMavenProject = injectMavenProject;
	}

	public String getMavenPropertiesBeanName() {
		return mavenPropertiesBeanName;
	}

	public void setMavenPropertiesBeanName(String mavenPropertiesBeanName) {
		this.mavenPropertiesBeanName = mavenPropertiesBeanName;
	}

	public String getMavenProjectBeanName() {
		return mavenProjectBeanName;
	}

	public void setMavenProjectBeanName(String mavenProjectBeanName) {
		this.mavenProjectBeanName = mavenProjectBeanName;
	}

	public String getMavenMojoBeanName() {
		return mavenMojoBeanName;
	}

	public void setMavenMojoBeanName(String mojoBeanName) {
		this.mavenMojoBeanName = mojoBeanName;
	}

	public String getServiceClassname() {
		return serviceClassname;
	}

	public void setServiceClassname(String serviceClassname) {
		this.serviceClassname = serviceClassname;
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

	public MavenProject getProject() {
		return project;
	}

	public String getPropertySourcesLocation() {
		return propertySourcesLocation;
	}

	public void setPropertySourcesLocation(String propertySourceContextLocation) {
		this.propertySourcesLocation = propertySourceContextLocation;
	}

	public boolean isAddPropertySources() {
		return addPropertySources;
	}

	public void setAddPropertySources(boolean configurePropertySources) {
		this.addPropertySources = configurePropertySources;
	}

	public boolean isInjectMavenMojo() {
		return injectMavenMojo;
	}

	public void setInjectMavenMojo(boolean injectMavenMojo) {
		this.injectMavenMojo = injectMavenMojo;
	}

	public void setProject(MavenProject project) {
		this.project = project;
	}

}
