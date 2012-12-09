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
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.processor.GroupCodeProcessor;
import org.kuali.common.util.property.processor.PathProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DefaultPropertyLoadContext extends DefaultPropertyContext implements PropertyLoadContext {
	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyLoadContext.class);

	List<String> locations;
	String missingLocationsMode = Mode.INFORM.name();
	String organizationGroupIdProperty = "organization.groupId";
	String groupIdProperty = "project.groupId";
	String versionProperty = "project.version";
	String encodingProperty = "project.build.sourceEncoding";

	private Properties internalProperties;

	@Override
	public void init() {
		Assert.notNull(helper, "helper is null");
		locations = Collections.<String> emptyList();
		internalProperties = getInternalProperties(properties);
		globalPropertiesOverrideMode = resolve(globalPropertiesOverrideMode, internalProperties);
		missingLocationsMode = resolve(missingLocationsMode, internalProperties);

		logger.info("Internal properties size - " + internalProperties.size());
		logger.info("Global properties override mode - " + globalPropertiesOverrideMode);
		logger.info("Missing locations mode - " + missingLocationsMode);

		validate();
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
			Properties duplicate = PropertyUtils.duplicate(properties);
			List<PropertyProcessor> processors = getLocationProcessors();
			for (PropertyProcessor processor : processors) {
				processor.process(duplicate);
			}
			return helper.replacePlaceholders(location, properties);
		} else {
			return location;
		}
	}

	private Properties getInternalProperties(Properties properties) {
		if (properties == null) {
			return PropertyUtils.getGlobalProperties();
		} else {
			return PropertyUtils.getGlobalProperties(properties);
		}
	}

	protected List<PropertyProcessor> getLocationProcessors() {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();
		GlobalPropertiesMode gpm = GlobalPropertiesMode.valueOf(globalPropertiesOverrideMode);
		processors.addAll(PropertyUtils.getPropertyProcessors(gpm));
		processors.addAll(getGavProcessors());
		return processors;
	}

	protected List<PropertyProcessor> getGavProcessors() {
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

}
