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

/**
 * @deprecated
 */
@Deprecated
public class DefaultProjectConfig implements ProjectConfig {

	String groupId;
	String artifactId;
	String contextId;

	public DefaultProjectConfig() {
		this(null, null);
	}

	public DefaultProjectConfig(ProjectConfig request) {
		super();
		this.groupId = request.getGroupId();
		this.artifactId = request.getArtifactId();
		this.contextId = request.getContextId();
	}

	public DefaultProjectConfig(String configId) {
		this(ConfigUtils.getProjectConfig(configId));
	}

	public DefaultProjectConfig(String groupId, String artifactId) {
		this(groupId, artifactId, null);
	}

	public DefaultProjectConfig(String groupId, String artifactId, String contextId) {
		super();
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.contextId = contextId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kuali.common.util.config.ConfigRequest#getId()
	 */
	@Override
	public String getConfigId() {
		return ConfigUtils.getConfigId(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kuali.common.util.config.ConfigRequest#getGroupId()
	 */
	@Override
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kuali.common.util.config.ConfigRequest#getArtifactId()
	 */
	@Override
	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kuali.common.util.config.ConfigRequest#getContextId()
	 */
	@Override
	public String getContextId() {
		return contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	@Override
	public String toString() {
		return getConfigId();
	}

}
