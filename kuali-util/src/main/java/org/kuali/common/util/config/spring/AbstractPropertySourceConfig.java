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
package org.kuali.common.util.config.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.config.Location;
import org.kuali.common.util.config.ProjectConfigService;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * 
 */
@Configuration
@Import({ ProjectConfigSpringConfig.class })
public abstract class AbstractPropertySourceConfig {

	private static final PropertyPlaceholderHelper HELPER = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;

	@Autowired
	ProjectConfigSpringConfig projectConfigSpringConfig;

	protected abstract String getGroupId();

	protected abstract String getArtifactId();

	protected List<String> getConfigIds() {
		return Collections.emptyList();
	}

	@Bean
	public List<Location> utilProjectConfigLocations() {
		ProjectConfigService service = projectConfigSpringConfig.utilProjectConfigService();
		List<Location> locations = new ArrayList<Location>();
		List<String> configIds = getConfigIds();
		for (String configId : configIds) {
			List<Location> list = service.getLocations(configId);
			locations.addAll(list);
		}
		return locations;
	}

	protected List<Location> getLocations() {
		return utilProjectConfigLocations();
	}

	protected Properties getProjectProperties() {
		Project project = ProjectUtils.loadProject(getGroupId(), getArtifactId());
		return project.getProperties();
	}

	protected PropertySource<?> getPropertySource() {
		// Do "something" to get project properties
		Properties project = getProjectProperties();
		// Do "something" to get the list of locations holding properties we need to load
		List<Location> locations = getLocations();
		// Allocate some storage
		Properties properties = new Properties();
		// Get system/environment properties
		Properties global = PropertyUtils.getGlobalProperties();
		// Cycle through our list of locations
		for (Location location : locations) {
			// Combine properties we've already loaded with project and global properties
			Properties resolver = PropertyUtils.combine(properties, project, global);
			// Use the combined properties to resolve any placeholders in the location
			String resolvedLocation = HELPER.replacePlaceholders(location.getValue(), resolver);
			// If the location exists, load it
			if (LocationUtils.exists(resolvedLocation)) {
				Properties loaded = PropertyUtils.load(resolvedLocation, location.getEncoding());
				properties.putAll(loaded);
			} else {
				// Take appropriate action for missing locations (ignore, inform, warn, or error out)
				ModeUtils.validate(location.getMissingMode(), "Non-existent location [" + resolvedLocation + "]");
			}
		}
		// Override loaded properties with project properties
		properties.putAll(project);
		// Override everything with system/environment properties
		properties.putAll(global);
		// Decrypt them
		PropertyUtils.decrypt(properties);
		// Resolve them, throw an exception if any values cannot be fully resolved
		PropertyUtils.resolve(properties);
		// Convert the properties into a PropertySource<?> for Spring
		return SpringUtils.getGlobalPropertySource(properties);
	}

	@Bean
	public PropertySource<?> springPropertySource() {
		return getPropertySource();
	}

}
