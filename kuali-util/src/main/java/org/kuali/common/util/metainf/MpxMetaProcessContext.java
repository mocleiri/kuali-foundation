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

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectConstants;
import org.kuali.common.util.ProjectUtils;

/**
 * This class represents a property context for the process of producing META-INF resource files specific to the standard Kuali structure for .mpx resources
 */
public class MpxMetaProcessContext extends DefaultProjectContext {

	public MpxMetaProcessContext() {
		super(ProjectConstants.UTIL_ARTIFACT_ID, getLocations());
	}

	protected static List<String> getLocations() {
		String prefix = ProjectUtils.getCommonClassPathPrefix(ProjectConstants.UTIL_ARTIFACT_ID) + "/" + MetaInfContext.METAINF;
		List<String> locations = new ArrayList<String>();
		locations.add(prefix + "/common.properties");
		locations.add(prefix + "/mpx.properties");
		return locations;
	}

}
