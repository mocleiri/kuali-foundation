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
package org.kuali.common.util.property.processor;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.OrgUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.Str;
import org.kuali.common.util.project.KualiConstants;
import org.springframework.util.Assert;

public class ProjectProcessor implements PropertyProcessor {

	private static final String KS_GROUP_ID = KualiConstants.STUDENT_GROUP_ID;
	private static final String FS = File.separator;
	private static final String DOT = ".";

	@Override
	public void process(Properties properties) {
		Project p = getProject(properties);
		validate(p);
		String groupCode = OrgUtils.getGroupCode(p.getOrgId(), p.getGroupId());

		doGroupIdBase(p, properties);

		String userHome = System.getProperty("user.home");
		String orgHome = userHome + FS + DOT + p.getOrgCode();
		String groupHome = orgHome + FS + groupCode;
		properties.setProperty("project.groupId", p.getGroupId());
		properties.setProperty("project.groupId.code", groupCode);
		properties.setProperty("project.groupId.path", Str.getPath(p.getGroupId()));
		properties.setProperty("project.orgId.home", orgHome);
		properties.setProperty("project.groupId.home", groupHome);

		// Add the current milliseconds value as a project property
		properties.setProperty("project.build.timestamp.millis", Long.toString(System.currentTimeMillis()));

	}

	@Deprecated
	protected void doGroupIdBase(Project p, Properties properties) {
		p.setGroupIdBase(OrgUtils.getGroupBase(p.getOrgId(), p.getGroupId()));

		// This is to deal with KS using a god awful amount of groupIds instead of just "org.kuali.student"
		// For example, this shortens "org.kuali.student.deployments" to "org.kuali.student"
		// KS is changing their poms to just use "org.kuali.student" but they are not there yet
		fixFunkyGroupIds(p);

		properties.setProperty("project.groupId.base", p.getGroupIdBase());
		properties.setProperty("project.groupId.base.path", Str.getPath(p.getGroupIdBase()));
	}

	/**
	 * If <code>project</code> is a KS project where groupIdBase != groupId, update groupId to be groupIdBase
	 */
	@Deprecated
	protected static void fixFunkyGroupIds(Project project) {

		// Only muck with the KS groupId's
		if (!StringUtils.startsWith(project.getGroupId(), KS_GROUP_ID)) {
			return;
		}

		String groupId = project.getGroupId();
		String groupIdBase = project.getGroupIdBase();

		int groupIdLength = groupId.length();
		int groupIdBaseLength = groupIdBase.length();

		// If this isn't true something has gone haywire
		Assert.isTrue(groupIdLength >= groupIdBaseLength, "groupIdLength < groupIdBaseLength");

		// Update groupId to be groupIdBase if (and only if)
		// 1 - This is a KS project
		// 2 - This KS project is using more than one groupId
		if (!StringUtils.equalsIgnoreCase(groupIdBase, groupId)) {
			project.setGroupId(groupIdBase);
		}
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
