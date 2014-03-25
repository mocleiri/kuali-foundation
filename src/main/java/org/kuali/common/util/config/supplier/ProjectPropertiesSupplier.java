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
package org.kuali.common.util.config.supplier;

import java.util.Properties;

import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.model.Project;
import org.springframework.util.Assert;

/**
 * @deprecated
 */
@Deprecated
public class ProjectPropertiesSupplier implements PropertiesSupplier {

	ProjectService service;
	String groupId;
	String artifactId;

	@Override
	public Properties getProperties() {

		Assert.hasText(groupId, "groupId has no text");
		Assert.hasText(artifactId, "artifactId has no text");
		Assert.notNull(service, "service is null");

		Project project = service.getProject(groupId, artifactId);
		return project.getProperties();
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

	public ProjectService getService() {
		return service;
	}

	public void setService(ProjectService service) {
		this.service = service;
	}

}
