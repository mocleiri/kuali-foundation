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
package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.processor.CopyStringProcessor;
import org.kuali.common.util.property.processor.GlobalOverrideProcessor;
import org.kuali.common.util.property.processor.GroupCodeProcessor;
import org.kuali.common.util.property.processor.PathProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.ResolvePlaceholdersProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DefaultPropertyLoadContext extends DefaultPropertyContext implements PropertyLoadContext {
	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyLoadContext.class);

	List<String> locations;
	String missingLocationsMode = Mode.INFORM.name();
	String organizationGroupIdProperty;
	String groupIdProperty = "project.groupId";
	String versionProperty = "project.version";
	String encodingProperty = "project.build.sourceEncoding";
	private Properties internalProperties;

	@Override
	public void init() {
		Assert.notNull(helper, "helper is null");
		this.locations = CollectionUtils.toEmpty(locations);
		Properties global = getGlobalProperties(properties);
		this.globalPropertiesOverrideMode = resolve(globalPropertiesOverrideMode, global);
		this.missingLocationsMode = resolve(missingLocationsMode, global);
		logger.info("Global properties override mode - " + globalPropertiesOverrideMode);
		logger.info("Missing locations mode - " + missingLocationsMode);
		validateGlobalPropertiesOverrideMode(globalPropertiesOverrideMode);
		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesOverrideMode);
		this.internalProperties = getInternalProperties(properties, gpm);
		logger.info("Internal properties size - " + internalProperties.size());
		logger.info("Encoding - " + encoding);
		PropertyUtils.show(internalProperties);
		validate();
	}

	protected void validateGlobalPropertiesOverrideMode(String globalPropertiesOverrideMode) {
		validateResolved(globalPropertiesOverrideMode);
		GlobalPropertiesMode.valueOf(globalPropertiesOverrideMode);
	}

	protected void validate() {
		validateResolved(globalPropertiesOverrideMode);
		validateResolved(missingLocationsMode);
		validateResolved(encoding);
		GlobalPropertiesMode.valueOf(globalPropertiesOverrideMode);
		Mode.valueOf(missingLocationsMode);
	}

	protected String resolve(String string, Properties properties) {
		if (string == null) {
			return null;
		} else {
			return helper.replacePlaceholders(string, properties);
		}
	}

	@Override
	public String getLocation(String location, Properties properties) {
		String resolvedLocation = getResolvedLocation(location, properties);
		return getValidatedLocation(resolvedLocation, Mode.valueOf(missingLocationsMode));
	}

	protected void validateResolved(String string) {
		if (PropertyUtils.containsUnresolvedPlaceholder(string)) {
			throw new IllegalArgumentException("Unable to resolve [" + string + "]");
		}
	}

	protected String getValidatedLocation(String location, Mode missingLocationsMode) {
		validateResolved(location);
		if (LocationUtils.exists(location)) {
			return location;
		} else {
			ModeUtils.validate(missingLocationsMode, "Skipping non-existent location - [{}]", location, "Could not locate [" + location + "]");
			return null;
		}
	}

	protected String getResolvedLocation(String location, Properties properties) {
		boolean resolve = PropertyUtils.containsUnresolvedPlaceholder(location);
		if (resolve) {
			Properties duplicate = PropertyUtils.duplicate(internalProperties);
			duplicate.putAll(properties);
			List<PropertyProcessor> processors = getLocationProcessors();
			for (PropertyProcessor processor : processors) {
				processor.process(duplicate);
			}
			return helper.replacePlaceholders(location, properties);
		} else {
			return location;
		}
	}

	private Properties getGlobalProperties(Properties properties) {
		return PropertyUtils.getGlobalProperties(PropertyUtils.toEmpty(properties));
	}

	private Properties getInternalProperties(Properties properties, GlobalPropertiesMode mode) {
		Properties internalProperties = PropertyUtils.duplicate(PropertyUtils.toEmpty(properties));
		List<PropertyProcessor> processors = getInternalProcessors();
		for (PropertyProcessor processor : processors) {
			processor.process(internalProperties);
		}
		return internalProperties;
	}

	protected List<PropertyProcessor> getLocationProcessors() {
		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesOverrideMode);
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();
		processors.add(new GlobalOverrideProcessor(gpm));
		processors.add(new ResolvePlaceholdersProcessor(helper, gpm));
		return processors;
	}

	protected List<PropertyProcessor> getInternalProcessors() {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();

		if (organizationGroupIdProperty != null && groupIdProperty != null) {
			processors.add(new GroupCodeProcessor(organizationGroupIdProperty, groupIdProperty));
		}

		if (groupIdProperty != null) {
			processors.add(new PathProcessor(groupIdProperty));
		}

		if (versionProperty != null) {
			processors.add(new VersionProcessor(versionProperty));
		}

		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesOverrideMode);
		processors.add(new GlobalOverrideProcessor(gpm));
		processors.add(new ResolvePlaceholdersProcessor(helper, gpm));

		if (encodingProperty != null && encoding == null) {
			processors.add(new CopyStringProcessor(this, "encoding", encodingProperty));
		}

		return processors;
	}

	public String getMissingLocationsMode() {
		return missingLocationsMode;
	}

	public void setMissingLocationsMode(String missingLocationsMode) {
		this.missingLocationsMode = missingLocationsMode;
	}

	@Override
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public String getOrganizationGroupIdProperty() {
		return organizationGroupIdProperty;
	}

	public void setOrganizationGroupIdProperty(String organizationGroupIdProperty) {
		this.organizationGroupIdProperty = organizationGroupIdProperty;
	}

	public String getGroupIdProperty() {
		return groupIdProperty;
	}

	public void setGroupIdProperty(String groupIdProperty) {
		this.groupIdProperty = groupIdProperty;
	}

	public String getVersionProperty() {
		return versionProperty;
	}

	public void setVersionProperty(String versionProperty) {
		this.versionProperty = versionProperty;
	}

	public String getEncodingProperty() {
		return encodingProperty;
	}

	public void setEncodingProperty(String encodingProperty) {
		this.encodingProperty = encodingProperty;
	}

}
