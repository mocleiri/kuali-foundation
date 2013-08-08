/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.config.ImpexExportProjectConstants;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.PropertiesLocationService;
import org.kuali.common.util.properties.spring.PropertiesLocationServiceConfig;
import org.kuali.common.util.properties.spring.PropertyLocationsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PropertiesLocationServiceConfig.class })
public class DumpPropertyLocationsConfig implements PropertyLocationsConfig {

	@Autowired
	PropertiesLocationService service;

	@Override
	@Bean
	public List<Location> propertyLocations() {
		return service.getLocations(ImpexExportProjectConstants.PROJECT_ID, getFilenames());
	}

	protected List<String> getFilenames() {
		List<String> filenames = new ArrayList<String>();
		filenames.add("common.properties");
		filenames.add("dump.properties");
		filenames.add("extract.properties");
		return filenames;
	}

}
