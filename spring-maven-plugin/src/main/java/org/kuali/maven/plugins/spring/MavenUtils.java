/**
 * Copyright 2011-2013 The Kuali Foundation
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
package org.kuali.maven.plugins.spring;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MavenUtils {

	private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

	public static final String POM = "pom";

	/**
	 * Always return <code>false</code> if <code>forceMojoExecution</code> is <code>false</code>, otherwise return <code>true</code> only if
	 * <code>skip</code> is <code>true</code> or <code>packaging</code> is <code>pom</code>.
	 */
	public static final boolean skip(boolean forceMojoExecution, boolean skip, String packaging) {
		if (forceMojoExecution) {
			logger.info("Forced mojo execution");
			return false;
		}
		if (skip) {
			logger.info("Skipping mojo execution");
			return true;
		}
		if (StringUtils.equalsIgnoreCase(packaging, POM)) {
			logger.info("Skipping mojo execution for project with packaging type '{}'", POM);
			return true;
		} else {
			return false;
		}
	}

	public static final Properties getInternalProperties(MavenProject project) {
		Properties properties = new Properties();
		properties.setProperty("project.id", project.getId());
		properties.setProperty("project.groupId", project.getGroupId());
		properties.setProperty("project.artifactId", project.getArtifactId());
		properties.setProperty("project.version", project.getVersion());
		properties.setProperty("project.packaging", project.getPackaging());
		properties.setProperty("project.name", project.getName());
		properties.setProperty("project.description", project.getDescription());
		properties.setProperty("project.inceptionYear", project.getInceptionYear());
		properties.setProperty("project.ciManagement.system", project.getCiManagement().getSystem());
		properties.setProperty("project.ciManagement.url", project.getCiManagement().getUrl());
		properties.setProperty("project.issueManagement.system", project.getIssueManagement().getSystem());
		properties.setProperty("project.issueManagement.url", project.getIssueManagement().getUrl());
		properties.setProperty("project.basedir", LocationUtils.getCanonicalPath(project.getBasedir()));
		properties.setProperty("project.build.directory", project.getBuild().getDirectory());
		properties.setProperty("project.build.outputDirectory", project.getBuild().getOutputDirectory());
		properties.setProperty("project.build.testOutputDirectory", project.getBuild().getTestOutputDirectory());
		properties.setProperty("project.build.sourceDirectory", project.getBuild().getSourceDirectory());
		properties.setProperty("project.build.scriptSourceDirectory", project.getBuild().getScriptSourceDirectory());
		properties.setProperty("project.build.testSourceDirectory", project.getBuild().getTestSourceDirectory());
		return properties;
	}
}
