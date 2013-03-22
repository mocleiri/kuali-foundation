/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * 
 */
public class ProjectUtils {

	private static final Logger logger = LoggerFactory.getLogger(ProjectUtils.class);
	private static final PropertyPlaceholderHelper PPH = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;

	public static Project loadProject(String gav) {
		Project project = getProject(gav);
		Properties properties = getProperties(project);
		return getProject(properties);
	}

	public static Project getProject(String gav) {
		logger.debug("Processing [{}]", gav);
		String[] tokens = StringUtils.split(gav, ":");

		Project project = new Project();
		if (tokens.length > 0) {
			project.setGroupId(RepositoryUtils.toNull(tokens[0]));
		}
		if (tokens.length > 1) {
			project.setArtifactId(RepositoryUtils.toNull(tokens[1]));
		}
		if (tokens.length > 2) {
			project.setPackaging(RepositoryUtils.toNull(tokens[2]));
		}
		if (tokens.length > 3) {
			project.setVersion(RepositoryUtils.toNull(tokens[3]));
		}
		if (tokens.length > 4) {
			project.setClassifier(RepositoryUtils.toNull(tokens[4]));
		}
		return project;
	}

	public static List<Dependency> getDependencies(String csv) {
		List<String> tokens = CollectionUtils.getTrimmedListFromCSV(csv);
		List<Dependency> dependencies = new ArrayList<Dependency>();
		for (String token : tokens) {
			Dependency dependency = RepositoryUtils.parseDependency(token);
			dependencies.add(dependency);
		}
		return dependencies;
	}

	public static Project getProject(Properties properties) {
		List<String> skipKeys = Arrays.asList("project.dependencies");
		String startsWith = "project.";
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, startsWith);
		Project project = new Project();
		project.setProperties(properties);
		Map<String, Object> description = describe(project);
		Set<String> beanProperties = description.keySet();
		for (String key : keys) {
			if (skipKeys.contains(key)) {
				continue;
			}
			String value = properties.getProperty(key);
			String beanProperty = getBeanProperty(key, startsWith);
			if (beanProperties.contains(beanProperty)) {
				copyProperty(project, beanProperty, value);
			}
		}
		String csv = RepositoryUtils.toNull(properties.getProperty("project.dependencies"));
		List<Dependency> dependencies = getDependencies(csv);
		project.setDependencies(dependencies);
		return project;
	}

	protected static String getBeanProperty(String key, String startsWith) {
		String s = StringUtils.substring(key, startsWith.length());
		String[] tokens = StringUtils.split(s, ".");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			if (i == 0) {
				sb.append(token);
			} else {
				sb.append(StringUtils.capitalize(token));
			}
		}
		return sb.toString();
	}

	public static Properties getProperties(String gav) {
		return getProperties(getProject(gav));
	}

	public static Properties getProperties(Project project) {
		String location = getPropertiesLocation(project);
		if (!LocationUtils.exists(location)) {
			throw new IllegalArgumentException("[" + location + "] does not exist");
		}
		return PropertyUtils.load(location);
	}

	public static String getPropertiesLocation(Project project) {
		Assert.hasText(project.getGroupId(), "groupId has no text");
		Assert.hasText(project.getArtifactId(), "artifactId has no text");

		Properties properties = new Properties();
		properties.setProperty("project.groupId.path", Str.getPath(project.getGroupId()));
		properties.setProperty("project.artifactId", project.getArtifactId());

		return PPH.replacePlaceholders(Constants.PROJECT_PROPERTIES_LOCATION, properties);
	}

	public static String getJavaSourceFileTemplate() {
		StringBuilder sb = new StringBuilder();
		sb.append("package ${project.groupId};\n");
		sb.append("\n");
		sb.append("/**\n");
		sb.append(" * ############################# WARNING ##############################\n");
		sb.append(" * \n");
		sb.append(" * This is a generated file. Do NOT edit.\n");
		sb.append(" * \n");
		sb.append(" * Automated process keeps this source file in sync with the Maven POM.\n");
		sb.append(" */\n");
		sb.append("public abstract class ${project.artifactId.classname} {\n");
		sb.append("\n");
		sb.append("	public static final String GROUP_ID = \"${project.groupId}\";\n");
		sb.append("	public static final String ARTIFACT_ID = \"${project.artifactId}\";\n");
		sb.append("	public static final String VERSION = \"${project.version}\";\n");
		sb.append("\n");
		sb.append("}\n");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	protected static Map<String, Object> describe(Object bean) {
		try {
			return BeanUtils.describe(bean);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		}
	}

	protected static void copyProperty(Object bean, String name, Object value) {
		try {
			BeanUtils.copyProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}

}
