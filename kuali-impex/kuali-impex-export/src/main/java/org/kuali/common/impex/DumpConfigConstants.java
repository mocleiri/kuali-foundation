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
import org.kuali.common.util.Str;
import org.kuali.common.util.config.ConfigConstants;

public class DumpConfigConstants {

	// Shorthand for GroupId + ArtifactId
	private static final String GA = Str.getId(KualiProjectConstants.COMMON_GROUP_ID, ExportProjectConstants.ARTIFACT_ID);

	public static final String CONTEXT_ID = "dump";
	public static final String BUILD_CONTEXT_ID = Str.getId(CONTEXT_ID, ConfigConstants.BUILD);
	public static final String CONFIG_ID = Str.getId(GA, CONTEXT_ID);
	public static final String BUILD_CONFIG_ID = Str.getId(GA, BUILD_CONTEXT_ID);

	public static final List<String> CONFIG_IDS = getConfigIds();

	protected static List<String> getConfigIds() {
		List<String> configIds = new ArrayList<String>();
		configIds.addAll(JdbcConfigConstants.DEFAULT_CONFIG_IDS);
		configIds.add(CONFIG_ID);
		return Collections.unmodifiableList(configIds);
	}

}
