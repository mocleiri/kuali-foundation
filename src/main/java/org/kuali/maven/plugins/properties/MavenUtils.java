/**
 * Copyright 2009-2013 The Kuali Foundation
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
package org.kuali.maven.plugins.properties;

import java.util.Properties;

import org.apache.maven.project.MavenProject;

public class MavenUtils {

	/**
	 * Return properties Maven uses internally
	 */
	public static final Properties getInternalMavenProperties(MavenProject project) {
		Properties properties = new Properties();
		properties.setProperty("project.groupId", project.getGroupId());
		properties.setProperty("project.artifactId", project.getArtifactId());
		properties.setProperty("project.version", project.getVersion());
		properties.setProperty("project.basedir", project.getBasedir().getAbsolutePath());
		properties.setProperty("project.build.directory", project.getBuild().getDirectory());
		return properties;
	}

}
