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

import org.kuali.common.util.Assert;
import org.kuali.common.util.property.ImmutableProperties;

public final class FullImmutableProject implements Project {

	final String groupId;
	final String artifactId;
	final String version;
	final Properties properties;

	public FullImmutableProject(String groupId, String artifactId, String version, Properties properties) {
		super();
		Assert.notBlank(groupId, artifactId, version, "GAV info can't be blank");
		Assert.notNull(properties, "properties can't be null");
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		if (properties instanceof ImmutableProperties) {
			// If they are already immutable, just reference them
			this.properties = properties;
		} else {
			// Otherwise create an immutable copy of them
			this.properties = new ImmutableProperties(properties);
		}
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

}
