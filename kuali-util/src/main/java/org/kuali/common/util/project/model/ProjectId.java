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

import org.kuali.common.util.Assert;
import org.kuali.common.util.ObjectUtils;

/**
 * The project identifier concept is based on two facts:
 * 
 * <p>
 * 1 - All Kuali projects produce only one artifact containing executable java code and associated resources.<br>
 * 2 - There is only one version of any given Kuali project in the java classpath.<br>
 * </p>
 * 
 * <p>
 * Thus, groupId + artifactId is a simple way to uniquely namespace project resources at runtime.
 * 
 * For example, files residing in the kuali-util project at the following locations:
 * 
 * <pre>
 *   src/main/resources/org/kuali/common/kuali-util/foo.txt
 *   src/main/resources/org/kuali/common/kuali-util/bar.txt
 * </pre>
 * 
 * Can be uniquely referenced at runtime as:
 * 
 * <pre>
 *   classpath:org/kuali/common/kuali-util/foo.txt
 *   classpath:org/kuali/common/kuali-util/bar.txt
 * </pre>
 * 
 * </p>
 */
public class ProjectId {

	private final String groupId;
	private final String artifactId;

	// This is used to simplify hashCode() and equals()
	private final String identifier;

	public ProjectId(String groupId, String artifactId) {
		// Make sure neither one is blank
		Assert.noBlanks("groupId and artifactId cannot be blank", groupId, artifactId);

		// Store groupId and artifactId
		this.groupId = groupId;
		this.artifactId = artifactId;

		// Changing this affects both hashCode() and equals(), be careful ...
		this.identifier = groupId + ":" + artifactId;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public String getArtifactId() {
		return this.artifactId;
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
	public boolean equals(Object object) {
		return ObjectUtils.equalsByToString(this, object);
	}

}
