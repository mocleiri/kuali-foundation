package org.kuali.common.util.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;

public class DefaultFeatureService implements FeatureService {

	protected static final String FEATURE_PROPERTIES_FILENAME = "feature.properties";
	protected static final String CLASSPATH_PREFIX = "classpath:";

	@Override
	public Feature getFeature(String groupId, String artifactId, String featureId) {
		Project project = ProjectUtils.loadProject(groupId, artifactId);
		String resourcePath = ProjectUtils.getResourcePath(project) + "/" + featureId;
		String location = CLASSPATH_PREFIX + resourcePath + "/" + FEATURE_PROPERTIES_FILENAME;

		Properties properties = PropertyUtils.loadSilently(location);

		DefaultFeature feature = new DefaultFeature();
		feature.setArtifactId(artifactId);
		feature.setGroupId(groupId);
		feature.setFeatureId(featureId);
		feature.setProperties(properties);
		return feature;
	}

	protected List<FeatureContext> getFeatureContexts(Feature feature) {
		Properties properties = feature.getProperties();
		String contextsKey = "feature." + feature.getFeatureId() + ".contexts";
		String csv = properties.getProperty(contextsKey);
		List<String> contextNames = CollectionUtils.getTrimmedListFromCSV(csv);

		List<FeatureContext> contexts = new ArrayList<FeatureContext>();
		for (String contextName : contextNames) {
			FeatureContext context = new FeatureContext();
			context.setName(contextName);
			contexts.add(context);
		}

		return contexts;
	}

	protected FeatureContext getFeatureContext(String featureId, String name, Properties properties) {
		String key = "feature." + featureId + "." + name + ".locations";
		String csvKeys = properties.getProperty(key);
		List<String> keys = CollectionUtils.getTrimmedListFromCSV(csvKeys);
		List<LocationContext> locationContexts = getLocationContexts(featureId, name, keys, properties);
		FeatureContext context = new FeatureContext();
		context.setName(name);
		context.setLocationContexts(locationContexts);
		return context;
	}

	protected List<LocationContext> getLocationContexts(String featureId, String name, List<String> keys, Properties properties) {
		List<LocationContext> locationContexts = new ArrayList<LocationContext>();
		for (String key : keys) {
			String modeKey = key + ".mode";
			String encodingKey = key + ".encoding";

			String location = properties.getProperty(key);
			Assert.hasText(location, "[" + key + "] is not set");
			Assert.exists(location);

			String modeValue = properties.getProperty(modeKey);
			String encodingValue = properties.getProperty(encodingKey);

			Mode missingLocationMode = LocationContext.DEFAULT_MISSING_MODE;
			if (!StringUtils.isBlank(modeValue)) {
				missingLocationMode = Mode.valueOf(modeValue);
			}
			String encoding = LocationContext.DEFAULT_ENCODING;
			if (!StringUtils.isBlank(encodingValue)) {
				encoding = encodingValue;
			}
			LocationContext locationContext = new LocationContext(location, encoding, missingLocationMode);
			locationContexts.add(locationContext);
		}
		return locationContexts;
	}
}
