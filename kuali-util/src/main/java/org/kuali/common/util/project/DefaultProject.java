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
package org.kuali.common.util.project;

import java.util.Properties;

public final class DefaultProject implements Project {

	String groupId;
	String artifactId;
	String version;
	Properties properties;

	public DefaultProject() {
		this(null, null, null, null);
	}

	public DefaultProject(String groupId, String artifactId, String version, Properties properties) {
		super();
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		this.properties = properties;
	}

	@Override
	public String toString() {
		return groupId + ":" + artifactId + ":" + version;
	}

	@Override
	public String getGroupId() {
		return groupId;
	}

	@Override
	public String getArtifactId() {
		return artifactId;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
