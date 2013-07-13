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
package org.kuali.common.util.metainf;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectUtils;

/**
 *
 */
public abstract class MetaInfProcessContext extends DefaultProjectContext {

	protected static final String ARTIFACT_ID = ProjectUtils.KUALI_UTIL_ARTIFACT_ID;
	protected static final String FEATURE_ID = "metainf";
	protected static final String PROPERTIES_LOCATION_PREFIX = ProjectUtils.getCommonClassPathPrefix(ARTIFACT_ID) + "/" + FEATURE_ID;
	protected static final String COMMON_PROPERTIES_LOCATION = PROPERTIES_LOCATION_PREFIX + "/common.properties";

	public MetaInfProcessContext(String propertyLocation) {
		this(Arrays.asList(propertyLocation));
	}

	public MetaInfProcessContext(List<String> propertyLocations) {
		super(ARTIFACT_ID, CollectionUtils.combine(COMMON_PROPERTIES_LOCATION, propertyLocations));
	}
}
