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
package org.kuali.common.util.execute;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Version;
import org.kuali.common.util.VersionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DbBranchQualifierExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(DbBranchQualifierExecutable.class);

	Properties mavenProperties;
	String version;
	boolean skip;

	@Override
	public void execute() {

		// Might be skipping execution altogether
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(mavenProperties, "mavenProperties is null");

		// Determine if the qualifier is already set
		String qualifier = mavenProperties.getProperty("db.branch.qualifier");
		boolean blank = StringUtils.isBlank(qualifier);
		if (!blank) {
			logger.info("Already set - [db.branch.qualifier={}]", qualifier);
			return;
		}

		// Parse the version string into a version pojo
		Version v = VersionUtils.getVersion(version);

		// If the version qualifier is blank there is nothing more to do
		blank = StringUtils.isBlank(v.getQualifier());
		if (blank) {
			logger.info("Version qualifier is blank - [db.branch.qualifier={}]", qualifier);
			return;
		}

		// Transform and append the qualifier based on the qualifier from the version
		qualifier = v.getQualifier();
		qualifier = StringUtils.upperCase(qualifier);
		qualifier = StringUtils.replace(qualifier, "-", "_");
		qualifier = StringUtils.replace(qualifier, ".", "_");
		qualifier = "_" + qualifier;

		// Log what we are up to
		logger.info("Setting qualifier - [db.branch.qualifier={}]", qualifier);
		mavenProperties.setProperty("db.branch.qualifier", qualifier);
	}

	public Properties getMavenProperties() {
		return mavenProperties;
	}

	public void setMavenProperties(Properties mavenProperties) {
		this.mavenProperties = mavenProperties;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
