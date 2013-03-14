/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util.property.processor;

import java.util.Properties;

import org.kuali.common.util.OrgUtils;
import org.kuali.common.util.Project;
import org.springframework.util.Assert;

public class ProjectProcessor implements PropertyProcessor {

	@Override
	public void process(Properties properties) {
		Project project = getProject(properties);
		validate(project);
		String base = OrgUtils.getGroupIdBase(project.getOrgId(), project.getGroupId());
	}

	protected void validate(Project project) {
		Assert.notNull(project.getOrgId(), "orgId is null");
		Assert.notNull(project.getOrgCode(), "orgCode is null");
		Assert.notNull(project.getOrgPath(), "orgPath is null");
		Assert.notNull(project.getGroupId(), "groupId is null");
		Assert.notNull(project.getArtifactId(), "artifactId is null");
		Assert.notNull(project.getVersion(), "version is null");
	}

	protected Project getProject(Properties properties) {
		Project project = new Project();
		project.setOrgId(properties.getProperty("project.orgId"));
		project.setOrgCode(properties.getProperty("project.orgId.code"));
		project.setOrgPath(properties.getProperty("project.orgId.path"));
		project.setGroupId(properties.getProperty("project.groupId"));
		project.setArtifactId(properties.getProperty("project.artifactId"));
		project.setVersion(properties.getProperty("project.version"));
		project.setVersion(properties.getProperty("project.classifier"));
		return project;
	}

}
