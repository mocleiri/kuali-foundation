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

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.OrgUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.Str;
import org.springframework.util.Assert;

public class ProjectProcessor implements PropertyProcessor {

	private static final String FS = File.separator;
	private static final String DOT = ".";

	@Override
	public void process(Properties properties) {
		Project p = getProject(properties);
		validate(p);
		String groupCode = OrgUtils.getGroupCode(p.getOrgId(), p.getGroupId());
		String groupBase = OrgUtils.getGroupBase(p.getOrgId(), p.getGroupId());
		String userHome = System.getProperty("user.home");
		String orgHome = userHome + FS + DOT + p.getOrgCode();
		String groupHome = orgHome + FS + groupCode;
		properties.setProperty("project.groupId.code", groupCode);
		properties.setProperty("project.groupId.base", groupBase);
		properties.setProperty("project.groupId.base.path", Str.getPath(groupBase));
		properties.setProperty("project.orgId.home", orgHome);
		properties.setProperty("project.groupId.home", groupHome);
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
		return project;
	}

}
