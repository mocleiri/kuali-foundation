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
package org.kuali.common.util.feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;

public class DefaultFeatureService implements FeatureService {

	protected static final String COMMON_PROPERTIES_FILENAME = "common.properties";
	protected static final String DEFAULT_CONTEXT_NAME = "default";
	protected static final String FEATURE_PROPERTIES_FILENAME = "feature.properties";
	protected static final String CLASSPATH_PREFIX = "classpath:";
	protected static final String CONTEXTS_KEY = "feature.contexts";

	@Override
	public Feature getFeature(String groupId, String artifactId, String featureId) {
		Project project = ProjectUtils.loadProject(groupId, artifactId);

		Properties featureProperties = getFeatureProperties(project, featureId);

		DefaultFeature feature = new DefaultFeature();
		feature.setArtifactId(artifactId);
		feature.setGroupId(groupId);
		feature.setFeatureId(featureId);
		feature.setProperties(featureProperties);
		return feature;
	}

	protected String getClasspathLocation(Project project, String featureId, String filename) {
		String resourcePath = ProjectUtils.getResourcePath(project) + "/" + featureId;
		return CLASSPATH_PREFIX + resourcePath + "/" + filename;
	}

	protected Properties getFeatureProperties(Project project, String featureId) {
		String location = getClasspathLocation(project, featureId, FEATURE_PROPERTIES_FILENAME);
		if (LocationUtils.exists(location)) {
			return PropertyUtils.load(location, project.getEncoding());
		} else {
			return new Properties();
		}
	}

	protected List<FeatureContext> getDefaultFeatureContexts(Project project, Feature feature) {
		String location = getClasspathLocation(project, feature.getFeatureId(), COMMON_PROPERTIES_FILENAME);
		if (LocationUtils.exists(location)) {
			LocationContext locationContext = new LocationContext(location, project.getEncoding());
			FeatureContext context = new FeatureContext();
			context.setName(DEFAULT_CONTEXT_NAME);
			context.setLocationContexts(Arrays.asList(locationContext));
			return Arrays.asList(context);
		} else {
			return new ArrayList<FeatureContext>();
		}
	}

	protected List<FeatureContext> getFeatureContexts(Project project, Feature feature) {
		if (PropertyUtils.isEmpty(feature.getProperties())) {
			return getDefaultFeatureContexts(project, feature);
		}
		Properties properties = feature.getProperties();
		String csv = properties.getProperty(CONTEXTS_KEY);
		List<String> contextNames = CollectionUtils.getTrimmedListFromCSV(csv);

		List<FeatureContext> contexts = new ArrayList<FeatureContext>();
		for (String contextName : contextNames) {
			FeatureContext context = new FeatureContext();
			context.setName(contextName);
			contexts.add(context);
		}

		return contexts;
	}

	protected FeatureContext getFeatureContext(Project project, String contextName, Properties properties) {
		String key = contextName + ".locations";
		String csv = properties.getProperty(key);
		List<String> locationKeys = CollectionUtils.getTrimmedListFromCSV(csv);
		List<LocationContext> locationContexts = getLocationContexts(project, locationKeys, properties);
		FeatureContext context = new FeatureContext();
		context.setName(contextName);
		context.setLocationContexts(locationContexts);
		return context;
	}

	protected List<LocationContext> getLocationContexts(Project project, List<String> locationKeys, Properties properties) {
		List<LocationContext> locationContexts = new ArrayList<LocationContext>();
		for (String locationKey : locationKeys) {
			String modeKey = locationKey + ".mode";
			String encodingKey = locationKey + ".encoding";

			String location = properties.getProperty(locationKey);
			Assert.hasText(location, "[" + locationKey + "] is not set");
			Assert.exists(location);

			String encoding = PropertyUtils.getProperty(properties, encodingKey, project.getEncoding());

			String modeValue = properties.getProperty(modeKey);

			Mode missingLocationMode = LocationContext.DEFAULT_MISSING_MODE;
			if (!StringUtils.isBlank(modeValue)) {
				missingLocationMode = Mode.valueOf(StringUtils.upperCase(modeValue));
			}
			LocationContext locationContext = new LocationContext(location, encoding, missingLocationMode);
			locationContexts.add(locationContext);
		}
		return locationContexts;
	}
}
