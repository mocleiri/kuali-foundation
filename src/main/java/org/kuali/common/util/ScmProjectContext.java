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
package org.kuali.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated
 */
@Deprecated
public class ScmProjectContext extends DefaultProjectContext {

	public ScmProjectContext() {
		super(KualiProjectConstants.UTIL_ARTIFACT_ID, getLocations());
	}

	protected static List<String> getLocations() {
		String prefix = ProjectUtils.getCommonClassPathPrefix(KualiProjectConstants.UTIL_ARTIFACT_ID);
		List<String> locations = new ArrayList<String>();
		locations.add(prefix + "/scm.properties");
		return locations;
	}

}
