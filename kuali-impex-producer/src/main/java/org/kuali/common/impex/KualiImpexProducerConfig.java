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

import org.kuali.common.util.config.ConfigUtils;
import org.kuali.common.util.config.ProjectConfig;
import org.kuali.common.util.project.KualiConstants;

public enum KualiImpexProducerConfig implements ProjectConfig {

	SCHEMA_SQL("sql:schema"), //
	MPX_SQL("sql:mpx");

	public static final String ARTIFACT_ID = "kuali-impex-producer";

	private final String contextId;
	private final String configId;

	private KualiImpexProducerConfig(String contextId) {
		this.contextId = contextId;
		this.configId = ConfigUtils.getConfigId(this);
	}

	@Override
	public String getGroupId() {
		return KualiConstants.COMMON_GROUP_ID;
	}

	@Override
	public String getArtifactId() {
		return ARTIFACT_ID;
	}

	@Override
	public String getContextId() {
		return contextId;
	}

	@Override
	public String getConfigId() {
		return configId;
	}
}
