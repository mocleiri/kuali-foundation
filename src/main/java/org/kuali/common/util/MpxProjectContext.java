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

/**
 * @deprecated
 */
@Deprecated
public class MpxProjectContext extends DefaultProjectContext {

	@Deprecated
	private static final String ARTIFACT_ID = ProjectUtils.KUALI_UTIL_ARTIFACT_ID;
	private static final String LOCATION = "classpath:org/kuali/common/kuali-util/mpx/metainf.properties";

	public MpxProjectContext() {
		super(ARTIFACT_ID, Arrays.asList(LOCATION));
	}

}
