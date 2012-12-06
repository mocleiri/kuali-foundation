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
package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ModeUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class LoadProcessor implements PropertyProcessor {
	private static final Logger logger = LoggerFactory.getLogger(LoadProcessor.class);

	List<String> locations;
	GlobalPropertiesMode globalPropertiesOverrideMode = GlobalPropertiesMode.BOTH;
	String encoding;
	Mode missingLocationsMode = Mode.INFORM;

	public LoadProcessor() {
		this(null, GlobalPropertiesMode.BOTH, null, Mode.INFORM);
	}

	public LoadProcessor(List<String> locations, GlobalPropertiesMode globalPropertiesOverrideMode, String encoding, Mode missingLocationsMode) {
		super();
		this.locations = locations;
		this.globalPropertiesOverrideMode = globalPropertiesOverrideMode;
		this.encoding = encoding;
		this.missingLocationsMode = missingLocationsMode;
	}

	@Override
	public void process(Properties properties) {
		PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		for (String location : locations) {
			Properties duplicate = PropertyUtils.getProperties(properties, globalPropertiesOverrideMode);
			String resolvedLocation = helper.replacePlaceholders(location, duplicate);
			if (!location.equals(resolvedLocation)) {
				logger.debug("Resolved location [{}] -> [{}]", location, resolvedLocation);
			}
			if (LocationUtils.exists(resolvedLocation)) {
				Properties newProperties = PropertyUtils.load(resolvedLocation, encoding);
				properties.putAll(newProperties);
			} else {
				ModeUtils.validate(missingLocationsMode, "Skipping non-existent location - [{}]", resolvedLocation, "Could not locate [" + resolvedLocation + "]");
			}
		}
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public GlobalPropertiesMode getGlobalPropertiesOverrideMode() {
		return globalPropertiesOverrideMode;
	}

	public void setGlobalPropertiesOverrideMode(GlobalPropertiesMode globalPropertiesOverrideMode) {
		this.globalPropertiesOverrideMode = globalPropertiesOverrideMode;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Mode getMissingLocationsMode() {
		return missingLocationsMode;
	}

	public void setMissingLocationsMode(Mode missingLocationsMode) {
		this.missingLocationsMode = missingLocationsMode;
	}

}
