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

import org.kuali.common.util.Assert;

public final class ImmutableProjectIdentifier implements ProjectIdentifier {

	private final String groupId;
	private final String artifactId;
	private final int hashCode;
	private final String id;

	public ImmutableProjectIdentifier(String groupId, String artifactId) {
		Assert.notBlank(groupId, artifactId, "groupId and artifactId are required");
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.id = groupId + ":" + artifactId;
		this.hashCode = id.hashCode();
	}

	@Override
	public String getGroupId() {
		return this.groupId;
	}

	@Override
	public String getArtifactId() {
		return this.artifactId;
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
		// Other is us (and we are other)
		// ie, we are comparing the exact same 2 objects
		if (this == other) {
			return true;
		}

		// We can't be equal to other if it is null
		if (other == null) {
			return false;
		}

		// If other is a different runtime class type than us, it cannot be equal to us
		if (getClass() != other.getClass()) {
			return false;
		}

		// We now know 2 things:
		// 1 - other is not null
		// 2 - other is an ImmutableProjectIdentifier

		// Cast other to an ImmutableProjectIdentifier
		ImmutableProjectIdentifier identifier = (ImmutableProjectIdentifier) other;

		// The two id strings being equal constitutes equality
		return this.id.equals(identifier.id);
	}

}
