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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.maven.model.Profile;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.maven.spring.MavenAwareUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LongCounter;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.filter.StringFilter;
import org.kuali.common.util.maven.MavenUtils;
import org.kuali.common.util.maven.spring.MavenProfileConstants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.kuali.common.util.spring.service.PropertySourceContext;
import org.kuali.common.util.spring.service.PropertySourceService;
import org.kuali.common.util.spring.service.SpringContext;
import org.kuali.common.util.spring.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

public class DefaultSpringMojoService implements SpringMojoService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSpringMojoService.class);
	private static final LongCounter SEQUENCE = new LongCounter();

	private final PropertySourceService propertySourceService;

	public DefaultSpringMojoService(PropertySourceService propertySourceService) {
		Assert.noNulls(propertySourceService);
		this.propertySourceService = propertySourceService;
	}

	@Override
	public boolean isDebugLoggingEnabled(AbstractSpringMojo mojo) {
		boolean mojoDebug = mojo.getLog().isDebugEnabled();
		boolean log4JDebug = PropertyUtils.getGlobalBoolean("log4j.debug", mojo.getProperties());
		boolean springDebug = PropertyUtils.getGlobalBoolean("spring.debug", mojo.getProperties());
		return mojoDebug || log4JDebug || springDebug;
	}

	@Override
	public void callback(LoadXmlMojo mojo) {
		LoadContext lc = getLoadContext(mojo);
		if (lc == null) {
			return;
		}

		// Aggregate objects into a SpringContext
		SpringContext context = getSpringContext(mojo, lc.getMavenProperties());

		// Provide some context for looking up property sources
		MavenPropertySourceContext psc = getPropertySourcesContext(mojo, lc);

		// Add the property sources
		addPropertySources(psc, mojo, context);

		logConfiguration(mojo, lc.getMavenProperties(), context.getLocations());

		// Invoke the service to load the context using custom property sources and pre-registered beans
		lc.getService().load(context);
	}

	@Override
	public void callback(LoadMojo mojo) {
		LoadContext lc = getLoadContext(mojo);
		if (lc == null) {
			return;
		}

		// Aggregate objects into a SpringContext
		SpringContext context = getSpringContext(mojo, lc.getMavenProperties());

		// Provide some context for looking up property sources
		MavenPropertySourceContext psc = getPropertySourcesContext(mojo, lc);

		// Add the property sources
		addPropertySources(psc, mojo, context);

		// Show what we are up to
		logConfiguration(mojo, lc.getMavenProperties(), context.getAnnotatedClasses());

		// Invoke the service to load the context using custom property sources and pre-registered beans
		lc.getService().load(context);
	}

	/**
	 * Load the annotated class (or xml file) they provided. Scan it for any beans that implement <code>PropertySource</code>. Any <code>PropertySource</code> beans are sorted
	 * alphabetically by name, and added to the <code>SpringContext</code>
	 */
	protected void addPropertySources(MavenPropertySourceContext ctx, AbstractSpringMojo mojo, SpringContext context) {
		// Are we adding any custom property sources?
		if (mojo.isAddPropertySources()) {
			// Source is either an XML file or an annotated class
			String source = ctx.getLocation() == null ? ctx.getConfig().getName() : ctx.getLocation();
			// Extract PropertySource objects from the Spring application context
			logger.debug("Acquiring custom property sources - [{}]", source);
			List<PropertySource<?>> sources = getPropertySources(ctx);
			String msg = sources.size() == 1 ? "source" : "sources";
			logger.debug("Located {} custom property {}", sources.size(), msg);
			for (PropertySource<?> ps : sources) {
				String name = ps.getName();
				String type = ps.getClass().getName();
				logger.debug("Adding property source - [{}] -> [{}]", name, type);
			}
			// Add them to the SpringContext
			context.setPropertySourceContext(new PropertySourceContext(sources, mojo.isRemoveExistingPropertySources()));
		}

	}

	protected MavenPropertySourceContext getPropertySourcesContext(LoadXmlMojo mojo, LoadContext context) {
		String location = mojo.getPropertySourcesLocation();
		List<String> activeProfiles = getActiveProfiles(mojo);
		List<String> defaultProfiles = getDefaultProfiles(mojo);
		Properties properties = context.getMavenProperties();
		return new MavenPropertySourceContext.Builder(context.getService(), properties).location(location).activeProfiles(activeProfiles).defaultProfiles(defaultProfiles).build();
	}

	protected MavenPropertySourceContext getPropertySourcesContext(LoadMojo mojo, LoadContext context) {
		Class<?> config = ReflectionUtils.getClass(mojo.getPropertySourcesConfig());
		List<String> activeProfiles = getActiveProfiles(mojo);
		List<String> defaultProfiles = getDefaultProfiles(mojo);
		Properties properties = context.getMavenProperties();
		return new MavenPropertySourceContext.Builder(context.getService(), properties).config(config).activeProfiles(activeProfiles).defaultProfiles(defaultProfiles).build();
	}

	protected List<PropertySource<?>> getPropertySources(MavenPropertySourceContext ctx) {
		Map<String, Object> beans = CollectionUtils.toEmptyMap(ctx.getPropertiesBeanName(), (Object) ctx.getProperties());
		if (ctx.getLocation() != null) {
			return propertySourceService.getPropertySources(beans, ctx.getDefaultProfiles(), ctx.getActiveProfiles(), ctx.getLocation());
		} else if (ctx.getConfig() != null) {
			return propertySourceService.getPropertySources(beans, ctx.getDefaultProfiles(), ctx.getActiveProfiles(), ctx.getConfig());
		} else {
			throw new IllegalArgumentException("Must supply either location or an annotated class");
		}
	}

	protected SpringContext getSpringContext(LoadMojo mojo, Properties mavenProperties) {

		// Combine the main annotated class with any optional annotated classes
		List<String> annotatedClassNames = getAnnotatedClassNames(mojo);

		// Convert the strings into actual classes
		List<Class<?>> annotatedClasses = getAnnotatedClasses(annotatedClassNames);

		// These are the beans containing Maven GAV info and (if configured) the Maven model objects
		Map<String, Object> beans = getBeans(mojo, mavenProperties);

		// Accumulate any active Maven profiles into a list
		List<String> activeProfiles = getActiveProfiles(mojo);
		List<String> defaultProfiles = getDefaultProfiles(mojo);

		// Assemble a SpringContext from the information we have
		SpringContext context = new SpringContext();
		context.setDisplayName("Spring Maven Plugin : Load : " + SEQUENCE.increment());
		context.setAnnotatedClasses(annotatedClasses);
		context.setContextBeans(beans);
		context.setActiveProfiles(activeProfiles);
		context.setDefaultProfiles(defaultProfiles);
		return context;
	}

	/**
	 * Return a list of any active Maven profiles + any named profiles provided in the mojo configuration
	 */
	protected List<String> getActiveProfiles(AbstractSpringMojo mojo) {

		// Setup some storage
		List<String> profiles = new ArrayList<String>();

		// Add any active Maven profiles
		List<Profile> mavenProfiles = mojo.getProject().getActiveProfiles();
		for (Profile profile : CollectionUtils.toEmptyList(mavenProfiles)) {
			String profileId = profile.getId();
			profiles.add(profileId);
		}

		// Add profiles from the plugin config (if any)
		profiles.addAll(CollectionUtils.getTrimmedListFromCSV(mojo.getActiveProfiles()));

		// If we have wired in the Maven properties, activate the profile "autowiredMavenProperties"
		if (mojo.isInjectMavenProperties()) {
			profiles.add(MavenProfileConstants.AUTOWIRED_MAVEN_PROPERTIES);
		}

		// Setup includes / excludes
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(mojo.getActiveProfileIncludes());
		List<String> excludes = CollectionUtils.getNoneSensitiveListFromCSV(mojo.getActiveProfileExcludes());

		// Setup a filter
		StringFilter filter = new StringFilter.Builder(includes, excludes).build();

		// Filter out profiles that don't belong
		CollectionUtils.filterAndSortStrings(profiles, filter);

		// Return the list
		return profiles;
	}

	/**
	 * Convert the default profiles CSV to a list
	 */
	protected List<String> getDefaultProfiles(AbstractSpringMojo mojo) {
		List<String> profiles = CollectionUtils.getTrimmedListFromCSV(mojo.getDefaultProfiles());

		// Setup includes / excludes
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(mojo.getDefaultProfileIncludes());
		List<String> excludes = CollectionUtils.getNoneSensitiveListFromCSV(mojo.getDefaultProfileExcludes());

		// Setup a filter
		StringFilter filter = new StringFilter.Builder(includes, excludes).build();

		// Filter out profiles that don't belong
		CollectionUtils.filterAndSortStrings(profiles, filter);

		return profiles;
	}

	protected List<String> getAnnotatedClassNames(LoadMojo mojo) {
		List<String> names = new ArrayList<String>();
		// Add the single annotated class
		if (!StringUtils.isBlank(mojo.getAnnotatedClass())) {
			names.add(mojo.getAnnotatedClass());
		}
		// Add any additional annotated classes
		if (!CollectionUtils.isEmpty(mojo.getAnnotatedClasses())) {
			names.addAll(mojo.getAnnotatedClasses());
		}
		// If nothing has been provided derive a default annotated classname from the project info
		if (names.size() == 0) {
			String name = getDefaultAnnotatedClassname(mojo.getProject());
			names.add(name);
		}
		return names;
	}

	/**
	 * If they supplied an annotated class, use it. Otherwise, use the artifactId to generate a default annotated class name to use.
	 */
	protected String getAnnotatedClassName(LoadMojo mojo) {
		if (!StringUtils.isBlank(mojo.getAnnotatedClass())) {
			return mojo.getAnnotatedClass();
		} else {
			String className = getDefaultAnnotatedClassname(mojo.getProject());
			try {
				Class<?> annotatedClass = ReflectionUtils.getClass(className);
				return annotatedClass.getName();
			} catch (IllegalStateException e) {
				throw new IllegalStateException("No annotated class was provided and the default class [" + className + "] could not be created", e);
			}
		}
	}

	protected List<Class<?>> getAnnotatedClasses(List<String> annotatedClassNames) {
		List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
		for (String annotatedClassName : annotatedClassNames) {
			Class<?> annotatedClass = ReflectionUtils.getClass(annotatedClassName);
			annotatedClasses.add(annotatedClass);
		}
		return annotatedClasses;
	}

	protected Map<String, Object> getBeans(AbstractSpringMojo mojo, Properties mavenProperties) {
		Map<String, Object> beans = new HashMap<String, Object>();

		if (mojo.isInjectMavenProperties()) {
			beans.put(MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME, mavenProperties);
		}

		if (mojo.isInjectMavenSettings()) {
			beans.put(MavenConstants.DEFAULT_MAVEN_SETTINGS_BEAN_NAME, mojo.getSettings());
		}

		if (mojo.isInjectMavenProject()) {
			beans.put(MavenConstants.DEFAULT_MAVEN_PROJECT_BEAN_NAME, mojo.getProject());
		}

		if (mojo.isInjectMavenMojo()) {
			beans.put(MavenConstants.DEFAULT_MAVEN_MOJO_BEAN_NAME, mojo);
		}

		return beans;
	}

	protected SpringContext getSpringContext(LoadXmlMojo mojo, Properties mavenProperties) {
		// If no location was provided to the mojo, calculate one based on groupId + artifactId
		String location = mojo.getLocation() == null ? getDefaultLocation(mojo.getProject()) : mojo.getLocation();

		// Combine the main context location with any optional locations
		List<String> contextLocations = CollectionUtils.combine(location, mojo.getLocations());

		// These are the beans containing Maven GAV info and (if configured) the Maven model objects
		Map<String, Object> beans = getBeans(mojo, mavenProperties);

		// Accumulate any active Maven profiles into a list (default is one profile named "maven")
		List<String> activeProfiles = getActiveProfiles(mojo);
		List<String> defaultProfiles = getDefaultProfiles(mojo);

		SpringContext context = new SpringContext();
		context.setDisplayName("Spring Maven Plugin : LoadXML : " + SEQUENCE.increment());
		context.setLocations(contextLocations);
		context.setContextBeans(beans);
		context.setActiveProfiles(activeProfiles);
		context.setDefaultProfiles(defaultProfiles);
		return context;
	}

	protected void logConfiguration(AbstractSpringMojo mojo, Properties props, List<?> configurations) {
		logger.debug("---------------- Loading requested Spring configuration ----------------");
		for (Object configuration : configurations) {
			if (configuration instanceof Class) {
				Class<?> type = (Class<?>) configuration;
				logger.info("Loading - [{}]", type.getCanonicalName());
			} else {
				logger.info("Loading - [{}]", configuration);
			}
		}
		if (mojo.isInjectMavenProperties()) {
			logger.debug("Injecting Maven properties - {} total", props.size());
			logger.debug("Displaying " + props.size() + " properties\n\n" + PropertyUtils.toString(props));
		}
		if (mojo.isInjectMavenProject()) {
			logger.debug("Injecting [{}] -> [{}]", MavenConstants.DEFAULT_MAVEN_PROJECT_BEAN_NAME, mojo.getProject().getClass().getName());
		}
		if (mojo.isInjectMavenMojo()) {
			logger.debug("Injecting [{}] -> [{}]", MavenConstants.DEFAULT_MAVEN_MOJO_BEAN_NAME, mojo.getClass().getName());
		}
	}

	protected Properties getMavenProperties(AbstractSpringMojo mojo) {
		MavenProject project = mojo.getProject();
		// Get internal Maven config as a properties object
		Properties internal = MavenAwareUtils.getInternalProperties(project, mojo.getSettings());
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

	protected LoadContext getLoadContext(AbstractSpringMojo mojo) {
		// Might be skipping execution altogether
		if (MavenUtils.skip(mojo.isForceMojoExecution(), mojo.isSkip(), mojo.getProject().getPackaging())) {
			// The MavenUtils.skip() method emits a log message explaining why execution is being skipped
			return null;
		}

		// Combine mojo properties, project properties and internal maven properties into a Properties object
		Properties mavenProperties = getMavenProperties(mojo);

		// Get the desired SpringService implementation
		SpringService service = ReflectionUtils.newInstance(mojo.getSpringService());

		return new LoadContext(mavenProperties, service);
	}

	public PropertySourceService getPropertySourceService() {
		return propertySourceService;
	}

}
