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
package org.kuali.common.util.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.property.processor.GlobalOverrideProcessor;
import org.kuali.common.util.property.processor.HomeProcessor;
import org.kuali.common.util.property.processor.OrgProcessor;
import org.kuali.common.util.property.processor.PathProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @deprecated
 */
@Deprecated
public class DefaultPropertyLoadContext extends DefaultPropertyContext implements PropertyLoadContext {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyLoadContext.class);

	List<String> locations;
	String encoding = "UTF-8";
	String missingLocationsMode = Mode.INFORM.name();
	Properties locationHelperProperties;
	String locationHelperInclude;
	String locationHelperExclude;
	List<String> locationHelperIncludes;
	List<String> locationHelperExcludes;
	String organizationGroupId;
	String groupIdProperty = MavenConstants.GROUP_ID_KEY;
	String versionProperty = MavenConstants.VERSION_KEY;

	@Override
	public Properties init() {
		Assert.notNull(helper, "helper is null");
		Properties global = getGlobalProperties(locationHelperProperties);
		this.globalPropertiesMode = resolve(globalPropertiesMode, global);
		this.missingLocationsMode = resolve(missingLocationsMode, global);
		logger.info("Global properties mode - " + globalPropertiesMode);
		logger.info("Missing locations mode - " + missingLocationsMode);
		this.encoding = resolve(encoding, global);
		this.organizationGroupId = resolve(organizationGroupId, global);
		validateGlobalPropertiesMode(globalPropertiesMode);
		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesMode);
		this.locationHelperProperties = getLocationHelperProperties(locationHelperProperties, gpm);
		logger.info("Property file encoding - " + encoding);
		logger.info("Using " + locationHelperProperties.size() + " properties to assist with property loading.");
		validate();
		if (logger.isDebugEnabled()) {
			PropertyUtils.debug(locationHelperProperties);
		}
		Properties p = new Properties();
		p.putAll(PropertyUtils.toEmpty(properties));
		p.putAll(PropertyUtils.toEmpty(locationHelperProperties));
		return p;
	}

	protected void validateGlobalPropertiesMode(String globalPropertiesMode) {
		validateResolved(globalPropertiesMode);
		GlobalPropertiesMode.valueOf(globalPropertiesMode);
	}

	@Override
	protected void validate() {
		validateGlobalPropertiesMode(globalPropertiesMode);
		validateResolved(encoding);
		validateResolved(missingLocationsMode);
		Mode.valueOf(missingLocationsMode);
	}

	@Override
	public String getLocation(String location, Properties properties) {
		String resolvedLocation = getResolvedLocation(location, properties);
		return getValidatedLocation(resolvedLocation, Mode.valueOf(missingLocationsMode));
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

	protected void validateResolved(String string) {
		if (PropertyUtils.containsUnresolvedPlaceholder(string)) {
			throw new IllegalArgumentException("Unable to resolve [" + string + "]");
		}
	}

	protected String getResolvedLocation(String location, Properties properties) {
		boolean resolve = PropertyUtils.containsUnresolvedPlaceholder(location);
		if (resolve) {
			GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesMode);
			Properties duplicate = PropertyUtils.getProperties(properties, gpm);
			List<PropertyProcessor> processors = getLocationProcessors();
			for (PropertyProcessor processor : processors) {
				processor.process(duplicate);
			}
			return helper.replacePlaceholders(location, duplicate);
		} else {
			return location;
		}
	}

	protected Properties getGlobalProperties(Properties properties) {
		return PropertyUtils.getGlobalProperties(PropertyUtils.toEmpty(properties));
	}

	protected Properties getLocationHelperProperties(Properties properties, GlobalPropertiesMode mode) {
		Properties locationHelperProperties = PropertyUtils.duplicate(PropertyUtils.toEmpty(properties));
		List<PropertyProcessor> processors = getLocationHelperProcessors();
		for (PropertyProcessor processor : processors) {
			processor.process(locationHelperProperties);
		}
		return locationHelperProperties;
	}

	protected List<PropertyProcessor> getLocationProcessors() {
		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesMode);
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();
		processors.add(new GlobalOverrideProcessor(gpm));
		processors.add(new org.kuali.common.util.property.processor.ResolvePlaceholdersProcessor(helper, gpm));
		return processors;
	}

	protected String getGroupId() {
		if (groupIdProperty == null) {
			return null;
		} else if (locationHelperProperties == null) {
			return null;
		} else {
			return locationHelperProperties.getProperty(groupIdProperty);
		}
	}

	protected List<PropertyProcessor> getLocationHelperProcessors() {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();

		this.locationHelperIncludes = CollectionUtils.sortedMerge(locationHelperIncludes, locationHelperInclude);
		this.locationHelperExcludes = CollectionUtils.sortedMerge(locationHelperExcludes, locationHelperExclude);
		processors.add(new org.kuali.common.util.property.processor.TrimProcessor(locationHelperIncludes, locationHelperExcludes));

		String groupId = getGroupId();
		if (organizationGroupId != null && groupId != null) {
			processors.add(new OrgProcessor(organizationGroupId, groupId));
			processors.add(new HomeProcessor(organizationGroupId, groupId));
		}

		if (groupIdProperty != null) {
			processors.add(new PathProcessor(groupIdProperty));
		}

		if (versionProperty != null) {
			processors.add(new VersionProcessor(versionProperty));
		}

		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesMode);
		processors.add(new GlobalOverrideProcessor(gpm));
		processors.add(new org.kuali.common.util.property.processor.ResolvePlaceholdersProcessor(helper, gpm));

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

	public Properties getLocationHelperProperties() {
		return locationHelperProperties;
	}

	public void setLocationHelperProperties(Properties locationHelperProperties) {
		this.locationHelperProperties = locationHelperProperties;
	}

	@Override
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getOrganizationGroupId() {
		return organizationGroupId;
	}

	public void setOrganizationGroupId(String organizationGroupId) {
		this.organizationGroupId = organizationGroupId;
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

	public List<String> getLocationHelperIncludes() {
		return locationHelperIncludes;
	}

	public void setLocationHelperIncludes(List<String> locationHelperIncludes) {
		this.locationHelperIncludes = locationHelperIncludes;
	}

	public List<String> getLocationHelperExcludes() {
		return locationHelperExcludes;
	}

	public void setLocationHelperExcludes(List<String> locationHelperExcludes) {
		this.locationHelperExcludes = locationHelperExcludes;
	}

	public String getLocationHelperInclude() {
		return locationHelperInclude;
	}

	public void setLocationHelperInclude(String locationHelperInclude) {
		this.locationHelperInclude = locationHelperInclude;
	}

	public String getLocationHelperExclude() {
		return locationHelperExclude;
	}

	public void setLocationHelperExclude(String locationHelperExclude) {
		this.locationHelperExclude = locationHelperExclude;
	}

}
