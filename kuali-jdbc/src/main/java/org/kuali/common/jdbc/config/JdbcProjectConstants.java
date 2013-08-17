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
package org.kuali.common.jdbc.config;

import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;

/**
 * @deprecated
 */
@Deprecated
public class JdbcProjectConstants {

	private static final String GID = KualiProjectConstants.COMMON_GROUP_ID;
	private static final String AID = "kuali-jdbc";
	private static final String KUALI_SQL_AID = "kuali-sql";

	public static final ProjectIdentifier PROJECT_IDENTIFIER = new ProjectIdentifier(GID, AID);

	public static final ProjectIdentifier KUALI_SQL_PROJECT_IDENTIFIER = new ProjectIdentifier(GID, KUALI_SQL_AID);

	@Deprecated
	public static final String GROUP_ID = GID;

	@Deprecated
	public static final String ARTIFACT_ID = AID;

	@Deprecated
	public static final String PROJECT_ID = GROUP_ID + ":" + ARTIFACT_ID;

	@Deprecated
	public static final String SQL_ARTIFACT_ID = KUALI_SQL_AID;

	@Deprecated
	public static final String SQL_PROJECT_ID = GROUP_ID + ":" + SQL_ARTIFACT_ID;

}
