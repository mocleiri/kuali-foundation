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

package org.kuali.common.impex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.jdbc.config.JdbcConfigConstants;
import org.kuali.common.util.KualiProjectConstants;

public class ExportConfigConstants {

	// GroupId + ArtifactId
	private static final String GA = KualiProjectConstants.COMMON_GROUP_ID + ":" + ExportProjectConstants.ARTIFACT_ID;

	public static final String DUMP_CONTEXT_ID = "dump";
	public static final String MAVEN_CONTEXT_ID = "maven";
	public static final String DUMP_CONFIG_ID = GA + ":" + DUMP_CONTEXT_ID;
	public static final String MAVEN_CONFIG_ID = GA + ":" + MAVEN_CONTEXT_ID;

	public static final List<String> DUMP_CONFIG_IDS = Collections.unmodifiableList(getDumpConfigIds());

	protected static List<String> getDumpConfigIds() {
		List<String> configIds = new ArrayList<String>();
		configIds.addAll(JdbcConfigConstants.CONFIG_IDS);
		configIds.add(DUMP_CONFIG_ID);
		return configIds;
	}

}
