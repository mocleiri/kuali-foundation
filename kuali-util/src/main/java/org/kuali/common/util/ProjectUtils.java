/**
 * Copyright 2010-2012 The Kuali Foundation
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
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

	public static Project getProject(String gav) {
		logger.debug("Processing [{}]", gav);
		String[] tokens = StringUtils.split(gav, ":");

		Project project = new Project();
		if (tokens.length > 0) {
			project.setGroupId(StringUtils.trim(tokens[0]));
		}
		if (tokens.length > 1) {
			project.setArtifactId(StringUtils.trim(tokens[1]));
		}
		if (tokens.length > 2) {
			project.setPackaging(StringUtils.trim(tokens[2]));
		}
		if (tokens.length > 3) {
			project.setVersion(StringUtils.trim(tokens[3]));
		}
		return project;
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

	public static Project getProject(Properties properties) {
		String startsWith = "project.";
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, startsWith);
		Project project = new Project();
		Map<String, Object> description = describe(project);
		for (String key : keys) {
			String value = properties.getProperty(key);
			String beanProperty = getBeanProperty(key, startsWith);
			if (description.keySet().contains(beanProperty)) {
				copyProperty(project, beanProperty, value);
			}
		}
		return project;
	}

	protected static String getBeanProperty(String key, String startsWith) {
		String s = StringUtils.substring(key, startsWith.length());
		String[] tokens = StringUtils.split(s, ".");
		StringBuilder sb = new StringBuilder();
		for (String token : tokens) {
			sb.append(StringUtils.capitalize(token));
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

}
