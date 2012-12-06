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

import org.kuali.common.util.Mode;
import org.kuali.common.util.property.processor.AddEnvPropertiesProcessor;
import org.kuali.common.util.property.processor.AddSystemPropertiesProcessor;
import org.kuali.common.util.property.processor.HomeProcessor;
import org.kuali.common.util.property.processor.OrgProcessor;
import org.kuali.common.util.property.processor.PathProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.kuali.common.util.property.processor.VersionProcessor;

public class DefaultPropertyLoadContext extends DefaultPropertyContext implements PropertyLoadContext {

	List<String> locations;
	Mode missingLocationsMode = Mode.INFORM;
	List<PropertyProcessor> loadProcessors;

	@Override
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	@Override
	public List<PropertyProcessor> getLoadProcessors() {
		return loadProcessors;
	}

	public void setLoadProcessors(List<PropertyProcessor> loadProcessors) {
		this.loadProcessors = loadProcessors;
	}

	@Override
	public void initializeLoadProcessors() {
		if (loadProcessors == null) {
			loadProcessors = getDefaultLoadProcessors();
		} else {
			loadProcessors.addAll(0, getDefaultProcessors());
		}
	}

	protected List<PropertyProcessor> getDefaultLoadProcessors() {
		List<PropertyProcessor> processors = new ArrayList<PropertyProcessor>();

		processors.add(new AddEnvPropertiesProcessor());
		processors.add(new AddSystemPropertiesProcessor());

		if (pathProperties != null) {
			processors.add(new PathProcessor(pathProperties));
		}

		if (versionProperties != null) {
			processors.add(new VersionProcessor(versionProperties));
		}

		if (orgGroupIdKey != null && projectGroupIdKey != null) {
			processors.add(new OrgProcessor(orgGroupIdKey, projectGroupIdKey));
			processors.add(new HomeProcessor(orgGroupIdKey, projectGroupIdKey, globalPropertiesOverrideMode));
		}

		return processors;
	}

	@Override
	public Mode getMissingLocationsMode() {
		return missingLocationsMode;
	}

	public void setMissingLocationsMode(Mode missingLocationsMode) {
		this.missingLocationsMode = missingLocationsMode;
	}

}
