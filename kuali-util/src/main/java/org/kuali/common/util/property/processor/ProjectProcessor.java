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
import org.kuali.common.util.Str;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectService;
import org.springframework.util.Assert;

/**
 * This processor is called *very* early in the Maven build lifecyle in order to augment the default set of Maven properties.
 */
public class ProjectProcessor implements PropertyProcessor {

	private static final String KS_GROUP_ID = KualiProjectConstants.STUDENT_GROUP_ID;
	private static final String FS = File.separator;
	private static final String DOT = ".";

	ProjectService service;

	@Override
	public void process(Properties properties) {

		// Make sure we are configured correctly
		Assert.notNull(properties, "properties are null");
		Assert.notNull(service, "service is null");

		// Make sure groupId, artifactId, orgId, and orgCode are present
		validate(properties);

		// Fix the funk in KS groupId's (if its a KS project)
		fixKSGroupIds(properties);

		// Use the service to get a Project object from the properties
		Project p = service.getProject(properties);

		// Extract org info
		String orgId = properties.getProperty(MavenConstants.ORG_ID_KEY);
		String orgCode = properties.getProperty(MavenConstants.ORG_ID_CODE_KEY);

		// Figure out the group code (eg "rice", "student", "ole", etc)
		String groupCode = OrgUtils.getGroupCode(orgId, p.getGroupId());

		// Setup some org and group paths based on user.home
		String userHome = System.getProperty("user.home");
		String orgHome = userHome + FS + DOT + orgCode;
		String groupHome = orgHome + FS + groupCode;

		// Store the org and group paths
		properties.setProperty("project.groupId.path", Str.getPath(p.getGroupId()));
		properties.setProperty("project.orgId.home", orgHome);
		properties.setProperty("project.groupId.home", groupHome);

		// Store the groupCode
		properties.setProperty("project.groupId.code", groupCode);

		// Add the current milliseconds value as a project property
		properties.setProperty("project.build.timestamp.millis", Long.toString(System.currentTimeMillis()));

	}

	// Make sure the properties hold basic project identifier info
	protected void validate(Properties properties) {
		Assert.notNull(properties.getProperty(MavenConstants.GROUP_ID_KEY), MavenConstants.GROUP_ID_KEY + " is null");
		Assert.notNull(properties.getProperty(MavenConstants.ARTIFACT_ID_KEY), MavenConstants.ARTIFACT_ID_KEY + " is null");
		Assert.notNull(properties.getProperty(MavenConstants.ORG_ID_KEY), MavenConstants.ORG_ID_KEY + " is null");
		Assert.notNull(properties.getProperty(MavenConstants.ORG_ID_CODE_KEY), MavenConstants.ORG_ID_CODE_KEY + " is null");
	}

	protected void fixKSGroupIds(Properties properties) {
		// Extract the groupId
		String groupId = properties.getProperty(MavenConstants.GROUP_ID_KEY);

		// If it isn't a KS project, don't do anything
		if (!StringUtils.startsWith(groupId, KS_GROUP_ID)) {
			return;
		}

		// Extract the groupId base property
		String groupIdBase = properties.getProperty("project.groupId.base");

		// If groupIdBase isn't set, we are done
		if (StringUtils.isBlank(groupIdBase)) {
			return;
		}

		// If we get here, we are in a KS project where the property "project.groupId.base" has been set
		// When set, the property "project.groupId.base" for a KS project must always be "org.kuali.student" without exception

		// If this method executes without throwing an exception, groupIdBase==org.kuali.student
		validateKSGroupIdInfo(groupId, groupIdBase);

		// Make sure the properties object holds the correct project.groupId
		properties.setProperty(MavenConstants.GROUP_ID_KEY, KualiProjectConstants.STUDENT_GROUP_ID);

		// Make sure the properties object holds the correct project.groupId.path
		properties.setProperty("project.groupId.path", Str.getPath(KualiProjectConstants.STUDENT_GROUP_ID));
	}

	protected void validateKSGroupIdInfo(String groupId, String groupIdBase) {

		// Double check that this is a KS project
		Assert.isTrue(StringUtils.startsWith(groupId, KS_GROUP_ID), "Group id does not start with [" + KS_GROUP_ID + "]");

		// Extract the lengths
		int groupIdLength = groupId.length();
		int groupIdBaseLength = groupIdBase.length();

		// If this isn't true something has gone haywire
		Assert.isTrue(groupIdLength >= groupIdBaseLength, "groupIdLength < groupIdBaseLength");

		// If this isn't true something has gone haywire
		Assert.isTrue(StringUtils.equals(groupIdBase, KualiProjectConstants.STUDENT_GROUP_ID));
	}

}
