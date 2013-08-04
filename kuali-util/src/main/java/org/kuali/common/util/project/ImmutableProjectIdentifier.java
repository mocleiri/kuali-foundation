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
	private final String combined;
	private final int hashCode;

	public ImmutableProjectIdentifier(String groupId, String artifactId) {
		Assert.notBlank(groupId, artifactId, "groupId and artifactId are required");
		this.groupId = groupId;
		this.artifactId = artifactId;

		// Cache a reference to the groupId + artifactId combination
		this.combined = groupId + ":" + artifactId;

		// Cache the hashcode of the combined string
		this.hashCode = combined.hashCode();
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
		return combined;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object object) {
		// They are the exact same physical object
		if (this == object) {
			return true;
		}

		// Make sure other isn't null and is the exact same runtime type
		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		// Cast to an immutable project identifier
		ImmutableProjectIdentifier other = (ImmutableProjectIdentifier) object;

		// The hashcodes being the same AND the combined strings being the same, constitutes equality
		return hashCode == other.hashCode && combined.equals(other.combined);
	}

}
