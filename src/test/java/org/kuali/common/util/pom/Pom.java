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
package org.kuali.common.util.pom;

public class Pom {

	Line artifactId;
	Line description;
	Line name;
	Line version;
	Line packaging;
	Line endParentTag;

	public Line getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(Line artifactId) {
		this.artifactId = artifactId;
	}

	public Line getDescription() {
		return description;
	}

	public void setDescription(Line description) {
		this.description = description;
	}

	public Line getName() {
		return name;
	}

	public void setName(Line name) {
		this.name = name;
	}

	public Line getVersion() {
		return version;
	}

	public void setVersion(Line version) {
		this.version = version;
	}

	public Line getPackaging() {
		return packaging;
	}

	public void setPackaging(Line packaging) {
		this.packaging = packaging;
	}

	public Line getEndParentTag() {
		return endParentTag;
	}

	public void setEndParentTag(Line endParentTag) {
		this.endParentTag = endParentTag;
	}

}
