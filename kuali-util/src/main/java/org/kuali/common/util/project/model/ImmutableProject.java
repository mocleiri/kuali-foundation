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

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.Properties;

import org.kuali.common.util.ObjectUtils;
import org.kuali.common.util.property.ImmutableProperties;

public final class ImmutableProject implements Project {

	private final ProjectIdentifier identifier;
	private final String version;
	private final Properties properties;

	private final String id;
	private final int hashCode;

	public static ImmutableProject copyOf(Project project) {
		if (project instanceof ImmutableProject) {
			return (ImmutableProject) project;
		} else {
			return new ImmutableProject(project.getGroupId(), project.getArtifactId(), project.getVersion(), project.getProperties());
		}
	}

	public ImmutableProject(ProjectIdentifier identifier, String version, Properties properties) {
		checkNotNull(identifier, "identifier");
		checkNotNull(properties, "properties");
		checkNotBlank(version, "version");

		// Finish setting things up
		this.identifier = identifier;
		this.version = version;
		this.properties = ImmutableProperties.copyOf(properties);
		this.id = identifier.getIdentifier() + ":" + version;
		this.hashCode = identifier.hashCode();
	}

	public ImmutableProject(String groupId, String artifactId, String version, Properties properties) {
		this(new ProjectIdentifier(groupId, artifactId), version, properties);
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
	public String getVersion() {
		return version;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public String getIdentifier() {
		return id;
	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object other) {
		return ObjectUtils.equalsByToString(this, other);
	}

}
