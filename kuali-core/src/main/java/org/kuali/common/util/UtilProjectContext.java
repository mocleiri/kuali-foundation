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

import java.util.Arrays;
import java.util.List;

/**
 * @deprecated
 */
@Deprecated
public class UtilProjectContext extends DefaultProjectContext {

	// The artifact id for Kuali's util project
	public static final String ARTIFACT_ID = "kuali-util";

	public UtilProjectContext() {
		this((List<String>) null);
	}

	public UtilProjectContext(String propertyLocation) {
		this(Arrays.asList(propertyLocation));
	}

	public UtilProjectContext(List<String> propertyLocations) {
		super(ARTIFACT_ID, propertyLocations);
	}
}