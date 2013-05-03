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

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class SetNexusRepositoryIdExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SetNexusRepositoryIdExecutable.class);

	Properties mavenProperties;
	File buildDirectory;
	boolean skip;

	@Override
	public void execute() {
		// Might be skipping execution altogether
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		Assert.notNull(mavenProperties, "mavenProperties are null");
		Assert.notNull(buildDirectory, "buildDirectory is null");
		Assert.isTrue(buildDirectory.exists(), "buildDirectory does not exist");

		logger.info(LocationUtils.getCanonicalPath(buildDirectory));

	}

	public Properties getMavenProperties() {
		return mavenProperties;
	}

	public void setMavenProperties(Properties mavenProperties) {
		this.mavenProperties = mavenProperties;
	}

	public File getBuildDirectory() {
		return buildDirectory;
	}

	public void setBuildDirectory(File buildDirectory) {
		this.buildDirectory = buildDirectory;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
