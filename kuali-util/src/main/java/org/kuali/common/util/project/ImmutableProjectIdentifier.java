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

	public ImmutableProjectIdentifier(String groupId, String artifactId) {
		Assert.notBlank(groupId, artifactId, "groupId + artifactId cannot be blank");
		this.groupId = groupId;
		this.artifactId = artifactId;
	}

	@Override
	public String getGroupId() {
		return this.groupId;
	}

	@Override
	public String getArtifactId() {
		return this.artifactId;
	}

}
