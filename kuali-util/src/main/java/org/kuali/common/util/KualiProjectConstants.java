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


@Deprecated
public abstract class KualiProjectConstants {

	// Kuali Group Id's
	public static final String COMMON_GROUP_ID = "org.kuali.common";
	public static final String RICE_GROUP_ID = "org.kuali.rice";
	public static final String STUDENT_GROUP_ID = "org.kuali.student";
	public static final String MOBILITY_GROUP_ID = "org.kuali.mobility";
	public static final String OLE_GROUP_ID = "org.kuali.ole";
	public static final String KPME_GROUP_ID = "org.kuali.kpme";

	// Common Artifact Id's
	public static final String SQL_ARTIFACT_ID = "kuali-sql";
	public static final String UTIL_ARTIFACT_ID = "kuali-util";
	public static final String JDBC_ARTIFACT_ID = "kuali-jdbc";

	public static final String UTIL_PROJECT_ID = COMMON_GROUP_ID + ":" + UTIL_ARTIFACT_ID;

}
