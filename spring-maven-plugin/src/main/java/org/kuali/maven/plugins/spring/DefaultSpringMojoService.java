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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.maven.model.Dependency;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.maven.plugins.spring.config.MojoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

@Service
public class DefaultSpringMojoService implements SpringMojoService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringMojoService.class);

	@Override
	public void loadSpring(AbstractSpringMojo mojo) {
		logger.debug("----------------- Delegating mojo execution to Spring ------------------");
		SpringService service = ReflectionUtils.newInstance(mojo.getSpringService());

		List<String> beanNames = new ArrayList<String>();
		beanNames.add(MavenConstants.SPRING_MOJO_SERVICE_BEAN_NAME);
		beanNames.add(MavenConstants.DEFAULT_MAVEN_MOJO_BEAN_NAME);

		List<Object> beans = new ArrayList<Object>();
		beans.add(this);
		beans.add(mojo);

		PropertiesPropertySource propertySource = getMavenPropertySource(mojo);

		SpringContext context = new SpringContext();
		context.setPropertySourceContext(new PropertySourceContext(SpringUtils.asList(propertySource)));
		context.setAnnotatedClasses(CollectionUtils.asList(MojoConfig.class));
		context.setBeanNames(beanNames);
		context.setBeans(beans);
		service.load(context);
	}

	@Override
	public void mojoCallback(LoadXmlMojo mojo) {
		LoadContext lc = getLoadContext(mojo);
		if (lc == null) {
			return;
		}

		// Aggregate objects into a SpringContext
		SpringContext context = getSpringContext(mojo, lc.getMavenProperties());

		// Provide some context for looking up property sources
		PropertySourcesContext psc = getPropertySourcesContext(mojo, lc);

		// Add the property sources
		addPropertySources(psc, mojo, context);

		logConfiguration(mojo, lc.getMavenProperties(), context.getLocations());

		// Invoke the service to load the context using custom property sources and pre-registered beans
		lc.getService().load(context);
	}

	@Override
	public void mojoCallback(LoadMojo mojo) {
		LoadContext lc = getLoadContext(mojo);
		if (lc == null) {
			return;
		}

		// Aggregate objects into a SpringContext
		SpringContext context = getSpringContext(mojo, lc.getMavenProperties());

		// Provide some context for looking up property sources
		PropertySourcesContext psc = getPropertySourcesContext(mojo, lc);

		// Add the property sources
		addPropertySources(psc, mojo, context);

		// Show what we are up to
		logConfiguration(mojo, lc.getMavenProperties(), context.getAnnotatedClasses());

		// Invoke the service to load the context using custom property sources and pre-registered beans
		lc.getService().load(context);
	}

	protected void addPropertySources(PropertySourcesContext ctx, AbstractSpringMojo mojo, SpringContext context) {
		// Are we adding any custom property sources?
		if (mojo.isAddPropertySources()) {
			// Source is either an XML file or an annotated class
			String source = ctx.getLocation() == null ? ctx.getAnnotatedClass().getName() : ctx.getLocation();
			// Extract PropertySource objects from the Spring application context
			logger.debug("Acquiring custom property sources - [{}]", source);
			List<PropertySource<?>> sources = getPropertySources(ctx);
			String msg = sources.size() == 1 ? "source" : "sources";
			logger.debug("Located {} custom property {}", sources.size(), msg);
			for (PropertySource<?> ps : sources) {
				String name = ps.getName();
				String type = ps.getClass().getName();
				logger.info("Adding property source - [{}] -> [{}]", name, type);
			}
			// Add them to the SpringContext
			context.setPropertySourceContext(new PropertySourceContext(sources));
		}

	}

	protected PropertySourcesContext getPropertySourcesContext(LoadXmlMojo mojo, LoadContext context) {
		PropertySourcesContext psc = new PropertySourcesContext();
		psc.setLocation(mojo.getPropertySourcesLocation());
		psc.setProperties(context.getMavenProperties());
		psc.setService(context.getService());
		psc.setPropertiesBeanName(mojo.getMavenPropertiesBeanName());
		return psc;
	}

	protected PropertySourcesContext getPropertySourcesContext(LoadMojo mojo, LoadContext context) {
		Class<?> annotatedClass = ReflectionUtils.getClass(mojo.getPropertySourcesConfig());

		PropertySourcesContext psc = new PropertySourcesContext();
		psc.setAnnotatedClass(annotatedClass);
		psc.setProperties(context.getMavenProperties());
		psc.setService(context.getService());
		psc.setPropertiesBeanName(mojo.getMavenPropertiesBeanName());
		return psc;
	}

	protected List<PropertySource<?>> getPropertySources(PropertySourcesContext ctx) {
		if (ctx.getLocation() != null) {
			return getPropertySources(ctx.getService(), ctx.getLocation(), ctx.getPropertiesBeanName(), ctx.getProperties());
		} else if (ctx.getAnnotatedClass() != null) {
			return getPropertySources(ctx.getService(), ctx.getAnnotatedClass(), ctx.getPropertiesBeanName(), ctx.getProperties());
		} else {
			throw new IllegalArgumentException("Must supply either location or an annotated class");
		}
	}

	protected List<PropertySource<?>> getPropertySources(SpringService service, Class<?> annotatedClass, String mavenPropertiesBeanName, Properties mavenProperties) {
		ConfigurableApplicationContext parent = SpringUtils.getContextWithPreRegisteredBean(mavenPropertiesBeanName, mavenProperties);
		AnnotationConfigApplicationContext child = new AnnotationConfigApplicationContext();
		child.setParent(parent);
		child.register(annotatedClass);
		child.refresh();
		return SpringUtils.getPropertySources(child);
	}

	protected List<PropertySource<?>> getPropertySources(SpringService service, String location, String mavenPropertiesBeanName, Properties mavenProperties) {
		String[] locationsArray = { location };
		ConfigurableApplicationContext parent = SpringUtils.getContextWithPreRegisteredBean(mavenPropertiesBeanName, mavenProperties);
		ConfigurableApplicationContext child = new ClassPathXmlApplicationContext(locationsArray, parent);
		return SpringUtils.getPropertySources(child);
	}

	protected SpringContext getSpringContext(LoadMojo mojo, Properties mavenProperties) {
		if (mojo.getAnnotatedClass() == null) {
			String className = getDefaultAnnotatedClassname(mojo.getProject());
			try {
				Class<?> annotatedClass = ReflectionUtils.getClass(className);
				mojo.setAnnotatedClass(annotatedClass.getName());
			} catch (IllegalStateException e) {
				throw new IllegalStateException("No annotated class was provided and the default class [" + className + "] could not be created", e);
			}
		}

		// Combine the main context location with any optional locations
		List<String> annotatedClassNames = CollectionUtils.combine(mojo.getAnnotatedClass(), mojo.getAnnotatedClasses());
		List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
		for (String annotatedClassName : annotatedClassNames) {
			Class<?> annotatedClass = ReflectionUtils.getClass(annotatedClassName);
			annotatedClasses.add(annotatedClass);
		}

		// Assemble any beans we may be injecting
		List<Boolean> includes = Arrays.asList(mojo.isInjectMavenProperties(), mojo.isInjectMavenProject(), mojo.isInjectMavenMojo());
		List<String> beanNames = CollectionUtils.getList(includes, Arrays.asList(mojo.getMavenPropertiesBeanName(), mojo.getMavenProjectBeanName(), mojo.getMavenMojoBeanName()));
		List<Object> beans = CollectionUtils.getList(includes, Arrays.asList(mavenProperties, mojo.getProject(), mojo));

		SpringContext context = new SpringContext();
		context.setAnnotatedClasses(annotatedClasses);
		context.setBeanNames(beanNames);
		context.setBeans(beans);
		return context;
	}

	protected SpringContext getSpringContext(LoadXmlMojo mojo, Properties mavenProperties) {
		// If no location was provided to the mojo, calculate one based on groupId + artifactId
		String location = mojo.getLocation() == null ? getDefaultLocation(mojo.getProject()) : mojo.getLocation();

		// Combine the main context location with any optional locations
		List<String> contextLocations = CollectionUtils.combine(location, mojo.getLocations());

		// Assemble any beans we may be injecting
		List<Boolean> includes = Arrays.asList(mojo.isInjectMavenProperties(), mojo.isInjectMavenProject(), mojo.isInjectMavenMojo());
		List<String> beanNames = CollectionUtils.getList(includes, Arrays.asList(mojo.getMavenPropertiesBeanName(), mojo.getMavenProjectBeanName(), mojo.getMavenMojoBeanName()));
		List<Object> beans = CollectionUtils.getList(includes, Arrays.asList(mavenProperties, mojo.getProject(), mojo));

		SpringContext context = new SpringContext();
		context.setLocations(contextLocations);
		context.setBeanNames(beanNames);
		context.setBeans(beans);
		return context;
	}

	protected void logConfiguration(AbstractSpringMojo mojo, Properties props, List<?> configurations) {
		logger.debug("---------------- Loading requested Spring configuration ----------------");
		for (Object configuration : configurations) {
			logger.info("Loading - [{}]", configuration);
		}
		if (mojo.isInjectMavenProperties()) {
			logger.info("Injecting Maven properties - {} total", props.size());
			logger.debug("Displaying " + props.size() + " properties\n\n" + PropertyUtils.toString(props));
		}
		if (mojo.isInjectMavenProject()) {
			logger.debug("Injecting [{}] -> [{}]", mojo.getMavenProjectBeanName(), mojo.getProject().getClass().getName());
		}
		if (mojo.isInjectMavenMojo()) {
			logger.debug("Injecting [{}] -> [{}]", mojo.getMavenMojoBeanName(), mojo.getClass().getName());
		}
	}

	protected Properties getMavenProperties(AbstractSpringMojo mojo) {
		MavenProject project = mojo.getProject();
		// Get internal Maven config as a properties object
		Properties internal = getInternalProperties(project);
		// The ordering here is significant.
		// Properties supplied directly to the mojo override properties from project.getProperties()
		// But, internal Maven properties need to always win.
		// ${project.artifactId} needs to always faithfully represent the correct artifactId
		Properties properties = PropertyUtils.combine(project.getProperties(), project.getProperties(), internal);
		// Explicitly override internal Maven props with system/env props (simulates the default maven behavior)
		PropertyUtils.overrideWithGlobalValues(properties, GlobalPropertiesMode.BOTH);
		// Return the overridden properties
		return properties;
	}

	protected PropertiesPropertySource getMavenPropertySource(AbstractSpringMojo mojo) {
		String name = MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME;
		Properties source = getMavenProperties(mojo);
		return new PropertiesPropertySource(name, source);
	}

	protected String getDefaultLocation(MavenProject project) {
		StringBuilder sb = new StringBuilder();
		sb.append("classpath:");
		sb.append(Str.getPath(project.getGroupId()));
		sb.append("/");
		sb.append("spring");
		sb.append("/");
		sb.append(project.getArtifactId());
		sb.append("-");
		sb.append("context.xml");
		return sb.toString();
	}

	protected String getDefaultAnnotatedClassname(MavenProject project) {
		StringBuilder sb = new StringBuilder();
		sb.append(project.getGroupId());
		sb.append(".");
		sb.append("spring");
		sb.append(".");
		String artifactId = project.getArtifactId();
		String[] tokens = StringUtils.split(artifactId, "-");
		for (String token : tokens) {
			String capitalized = StringUtils.capitalizeFirstLetter(token);
			sb.append(capitalized);
		}
		sb.append("Config");
		return sb.toString();
	}

	protected Properties getInternalProperties(MavenProject project) {
		Properties properties = new Properties();
		nullSafeSet(properties, "project.id", project.getId());
		nullSafeSet(properties, "project.groupId", project.getGroupId());
		nullSafeSet(properties, "project.artifactId", project.getArtifactId());
		nullSafeSet(properties, "project.version", project.getVersion());
		nullSafeSet(properties, "project.packaging", project.getPackaging());
		nullSafeSet(properties, "project.name", project.getName());
		nullSafeSet(properties, "project.description", project.getDescription());
		nullSafeSet(properties, "project.inceptionYear", project.getInceptionYear());
		nullSafeSet(properties, "project.basedir", LocationUtils.getCanonicalPath(project.getBasedir()));
		if (project.getCiManagement() != null) {
			nullSafeSet(properties, "project.ciManagement.system", project.getCiManagement().getSystem());
			nullSafeSet(properties, "project.ciManagement.url", project.getCiManagement().getUrl());
		}
		if (project.getIssueManagement() != null) {
			nullSafeSet(properties, "project.issueManagement.system", project.getIssueManagement().getSystem());
			nullSafeSet(properties, "project.issueManagement.url", project.getIssueManagement().getUrl());
		}
		if (project.getBuild() != null) {
			nullSafeSet(properties, "project.build.directory", project.getBuild().getDirectory());
			nullSafeSet(properties, "project.build.outputDirectory", project.getBuild().getOutputDirectory());
			nullSafeSet(properties, "project.build.testOutputDirectory", project.getBuild().getTestOutputDirectory());
			nullSafeSet(properties, "project.build.sourceDirectory", project.getBuild().getSourceDirectory());
			nullSafeSet(properties, "project.build.scriptSourceDirectory", project.getBuild().getScriptSourceDirectory());
			nullSafeSet(properties, "project.build.testSourceDirectory", project.getBuild().getTestSourceDirectory());
		}
		if (project.getScm() != null) {
			nullSafeSet(properties, "project.scm.connection", project.getScm().getConnection());
			nullSafeSet(properties, "project.scm.developerConnection", project.getScm().getDeveloperConnection());
			nullSafeSet(properties, "project.scm.url", project.getScm().getDeveloperConnection());
		}
		nullSafeSet(properties, "project.pom.location", getPomLocation(project));
		if (project.getDependencies() != null) {
			nullSafeSet(properties, "project.dependencies", getDependenciesCSV(project.getDependencies()));
		} else {
			nullSafeSet(properties, "project.dependencies", "NONE");
		}
		return properties;
	}

	protected String getDependenciesCSV(List<Dependency> dependencies) {
		if (CollectionUtils.isEmpty(dependencies)) {
			return "NONE";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dependencies.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			Dependency dependency = dependencies.get(i);
			sb.append(getGavString(dependency));
		}
		return sb.toString();
	}

	protected String getGavString(Dependency dep) {
		StringBuilder sb = new StringBuilder();
		sb.append(dep.getGroupId());
		sb.append(":");
		sb.append(dep.getArtifactId());
		sb.append(":");
		sb.append(dep.getType());
		sb.append(":");
		sb.append(dep.getVersion());
		sb.append(":");
		sb.append(dep.getClassifier());
		sb.append(":");
		sb.append(dep.getScope());
		return sb.toString();
	}

	// Maven automatically stores the pom to this location
	protected String getPomLocation(MavenProject project) {
		StringBuilder sb = new StringBuilder();
		sb.append("classpath:");
		sb.append("META-INF");
		sb.append("/");
		sb.append("maven");
		sb.append("/");
		sb.append(project.getGroupId());
		sb.append("/");
		sb.append(project.getArtifactId());
		sb.append("/");
		sb.append("pom.xml");
		return sb.toString();
	}

	protected void nullSafeSet(Properties properties, String key, String value) {
		if (value != null) {
			properties.setProperty(key, value);
		}
	}

	protected LoadContext getLoadContext(AbstractSpringMojo mojo) {
		// Might be skipping execution altogether
		if (MavenUtils.skip(mojo.isForceMojoExecution(), mojo.isSkip(), mojo.getProject().getPackaging())) {
			// The MavenUtils.skip() method already emits a log message informing them that we are skipping execution
			return null;
		}

		// Combine mojo properties, project properties and internal maven properties into a Properties object
		Properties mavenProperties = getMavenProperties(mojo);

		// Get the desired SpringService implementation
		SpringService service = ReflectionUtils.newInstance(mojo.getSpringService());

		return new LoadContext(mavenProperties, service);
	}
}
