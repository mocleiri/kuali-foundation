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
package org.kuali.common.util.spring;

import java.util.List;

import org.kuali.common.util.LocationUtils;
import org.springframework.beans.factory.FactoryBean;

public class GetLocationListingsFactoryBean implements FactoryBean<List<String>> {

	List<String> locationListings;

	@Override
	public List<String> getObject() throws Exception {
		return LocationUtils.getLocations(locationListings);
	}

	@Override
	public Class<?> getObjectType() {
		return List.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public List<String> getLocationListings() {
		return locationListings;
	}

	public void setLocationListings(List<String> locationListings) {
		this.locationListings = locationListings;
	}

}
