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
package org.kuali.common.util.config.spring;

import java.util.Properties;

import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.property.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * Enhance the wired in Maven properties and return them
 */
@Configuration
public class MavenPropertySourceConfig extends AbstractPropertySourceConfig {

	@Autowired
	@Qualifier(Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Override
	protected Properties getProjectProperties() {

		// Make sure the maven properties got wired in correctly
		Assert.notNull(mavenProperties, "mavenProperties are null");

		// Add in org, group, home, and enhanced version properties
		MavenUtils.augmentProjectProperties(mavenProperties);

		// Return what we've got
		return mavenProperties;
	}

	@Override
	protected String getGroupId() {
		return mavenProperties.getProperty("project.groupId");
	}

	@Override
	protected String getArtifactId() {
		return mavenProperties.getProperty("project.artifactId");
	}

}
