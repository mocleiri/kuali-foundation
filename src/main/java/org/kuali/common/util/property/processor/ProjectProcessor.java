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
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * This processor is called *very* early in the Maven build lifecyle in order to augment the default set of Maven properties.
 */
public class ProjectProcessor implements PropertyProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ProjectProcessor.class);

	private static final String KS_GROUP_ID = KualiProjectConstants.STUDENT_GROUP_ID;
	private static final String FS = File.separator;
	private static final String DOT = ".";
	private static final String PROJECT_GROUP_ID_PATH = "project.groupId.path";

	@Override
	public void process(Properties properties) {

		// Make sure we are configured correctly
		Assert.notNull(properties, "properties are null");

		// Make sure groupId, artifactId, orgId, and orgCode are present
		validate(properties);

		// Fix the funk in KS groupId's (if its a KS project)
		fixKSGroupIds(properties);

		// Now that the groupId is fixed, it is safe to use the properties to get a project object
		Project p = ProjectUtils.getProject(properties);

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
		properties.setProperty(PROJECT_GROUP_ID_PATH, Str.getPath(p.getGroupId()));
		properties.setProperty("project.orgId.home", orgHome);
		properties.setProperty("project.groupId.home", groupHome);
		properties.setProperty("project.home", groupHome);

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

		// Only muck with KS projects
		if (!StringUtils.startsWith(groupId, KS_GROUP_ID)) {
			return;
		}

		// All KS projects should have a groupId of "org.kuali.student" no matter what
		properties.setProperty(MavenConstants.GROUP_ID_KEY, KualiProjectConstants.STUDENT_GROUP_ID);

		// If this KS project is using some other group id for some reason
		// Store it in project.properties just for posterity
		if (!StringUtils.equals(groupId, KualiProjectConstants.STUDENT_GROUP_ID)) {
			logger.debug("original={}", groupId);
			properties.setProperty(MavenConstants.GROUP_ID_ORIGINAL_KEY, groupId);
		}
	}

}
