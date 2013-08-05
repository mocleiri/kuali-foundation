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
package org.kuali.common.util.project.model;

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ObjectUtils;
import org.kuali.common.util.property.ImmutableProperties;

public final class ImmutableProject implements Project {

	private final String groupId;
	private final String artifactId;
	private final String version;
	private final Properties properties;

	private final String identifier;

	public ImmutableProject(String groupId, String artifactId, String version, Properties properties) {
		// Make sure we are being configured correctly
		Assert.noBlanks("GAV info is required", groupId, artifactId, version);
		Assert.notNull(properties, "properties can't be null");

		// Store the GAV info
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

		// Changing this affects both hashCode() and equals(), be careful ...
		this.identifier = groupId + ":" + artifactId + ":" + version;
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

	@Override
	public String toString() {
		// Changing this affects both hashCode() and equals(), be careful ...
		return identifier;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object other) {
		return ObjectUtils.equalsByToString(this, other);
	}

}
