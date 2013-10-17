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

import org.kuali.common.util.project.model.ProjectIdentifier;

/**
 * @deprecated
 */
@Deprecated
public enum KualiJdbcConfig implements org.kuali.common.util.config.ProjectConfig {

	DEFAULT(); // Provides the default set of configuration for JDBC related processes

	private final ProjectIdentifier identifier = JdbcProjectConstants.PROJECT_IDENTIFIER;
	private final String contextId;
	private final String configId;

	private KualiJdbcConfig() {
		this(null);
	}

	private KualiJdbcConfig(String contextId) {
		this.contextId = contextId;
		this.configId = org.kuali.common.util.config.ConfigUtils.getConfigId(this);
	}

	@Override
	public String getGroupId() {
		return identifier.getGroupId();
	}

	@Override
	public String getArtifactId() {
		return identifier.getArtifactId();
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
