/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.common.maven.spring;

import java.util.Properties;

import org.apache.maven.project.MavenProject;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.maven.MavenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class AugmentMavenPropertiesExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(AugmentMavenPropertiesExecutable.class);

	MavenProject mavenProject;
	boolean skip;

	@Override
	public void execute() {

		// Are we skipping execution altogether?
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		// This is the original Maven project object created by Maven and injected into the context by spring-maven-plugin
		Assert.notNull(mavenProject, "mavenProject is null");

		// Extract the Properties object Maven is using
		Properties mavenProperties = mavenProject.getProperties();

		// Retain the original size of the native Maven properties
		int originalSize = mavenProperties.size();

		// Create a new properties object that aggregates important information from the Maven model
		// eg project.getGroupId() gets inserted into the properties object as project.groupId
		Properties internal = MavenAwareUtils.getInternalProperties(mavenProject);

		// Add the internal properties to the properties object Maven is using
		mavenProperties.putAll(internal);

		// Add organization, group, and path properties and tokenize the version number adding properties for each token along with
		// a boolean property indicating if this is a SNAPSHOT build
		MavenUtils.augmentProjectProperties(mavenProperties);

		// Print something useful if we are in debug mode
		logger.debug("Added {} properties", FormatUtils.getCount(mavenProperties.size() - originalSize));

	}

	public MavenProject getMavenProject() {
		return mavenProject;
	}

	public void setMavenProject(MavenProject mavenProject) {
		this.mavenProject = mavenProject;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
