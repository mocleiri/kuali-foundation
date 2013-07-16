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

public class ConfigRequest {

	String groupId;
	String artifactId;
	String contextId;

	public ConfigRequest() {
		this(null, null);
	}

	public ConfigRequest(ConfigRequest request) {
		super();
		this.groupId = request.getGroupId();
		this.artifactId = request.getArtifactId();
		this.contextId = request.getContextId();
	}

	public ConfigRequest(String id) {
		this(ConfigUtils.getConfigRequest(id));
	}

	public ConfigRequest(String groupId, String artifactId) {
		this(groupId, artifactId, null);
	}

	public ConfigRequest(String groupId, String artifactId, String contextId) {
		super();
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.contextId = contextId;
	}

	public String getId() {
		return ConfigUtils.getConfigId(this);
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getContextId() {
		return contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	@Override
	public String toString() {
		return getId();
	}

}
