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

import java.util.Properties;

import org.codehaus.plexus.util.StringUtils;
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
		String[] tokens = StringUtils.split(gav);

		Project project = new Project();
		if (tokens.length > 0) {
			project.setGroupId(StringUtils.trim(tokens[1]));
		}
		if (tokens.length > 1) {
			project.setArtifactId(StringUtils.trim(tokens[2]));
		}
		if (tokens.length > 2) {
			project.setPackaging(StringUtils.trim(tokens[3]));
		}
		if (tokens.length > 3) {
			project.setVersion(StringUtils.trim(tokens[4]));
		}
		return project;
	}

	public static Properties getProperties(Project project) {
		String location = getPropertiesLocation(project);
		if (!LocationUtils.exists(location)) {
			throw new IllegalArgumentException(location + " does not exist");
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
