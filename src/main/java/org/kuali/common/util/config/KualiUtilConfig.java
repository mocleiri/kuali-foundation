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
package org.kuali.common.util.config;

import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;

/**
 * @deprecated
 */
@Deprecated
public enum KualiUtilConfig implements ProjectConfig {

	SCM("scm"), // Config for SCM related process
	METAINF_SQL("metainf:sql"), // Config for META-INF processing for SQL resources
	METAINF_MPX("metainf:mpx"), // Config for META-INF processing for MPX resources
	METAINF_SQL_BUILD("metainf:sql:build"), // Config for META-INF processing for SQL files, only available during a build
	METAINF_MPX_BUILD("metainf:mpx:build"); // Config for META-INF processing for MPX files, only available during a build

	private final ProjectIdentifier identifier = KualiUtilProjectConstants.PROJECT_ID;
	private final String contextId;
	private final String configId;

	private KualiUtilConfig(String contextId) {
		this.contextId = contextId;
		this.configId = ConfigUtils.getConfigId(this);
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
