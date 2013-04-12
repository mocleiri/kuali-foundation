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
package org.kuali.common.util;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

public class MavenUtils {

	private static final Logger logger = LoggerFactory.getLogger(MavenUtils.class);

	public static final String POM = "pom";

	public static ProjectProperties getMavenProjectProperties(Environment env, Properties mavenProperties) {
		Project project = ProjectUtils.getProject(mavenProperties);

		List<String> excludes = getList(env, "properties.maven.exclude");
		PropertyUtils.trim(mavenProperties, null, excludes);

		PropertiesContext pc = new PropertiesContext();
		pc.setProperties(mavenProperties);

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setPropertiesContext(pc);
		return pp;
	}

	protected static List<String> getList(Environment env, String key) {
		String csv = env.getProperty(key);
		return CollectionUtils.getTrimmedListFromCSV(csv);
	}

	/**
	 * Always return false if <code>forceMojoExecution</code> is true, otherwise return true only if <code>skip</code> is true or <code>packaging</code> is pom.
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

}
