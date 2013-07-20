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

import java.util.Properties;

import org.kuali.common.util.service.SpringContext;

/**
 * Maven utilities that don't depend on Maven libraries
 */
@Deprecated
public class MavenUtils {

	public static final String POM = org.kuali.common.util.maven.MavenUtils.POM;
	public static final String PROJECT_VERSION_KEY = org.kuali.common.util.maven.MavenUtils.PROJECT_VERSION_KEY;
	public static final String PROJECT_ENCODING_KEY = org.kuali.common.util.maven.MavenUtils.PROJECT_ENCODING_KEY;

	public static SpringContext getMavenizedSpringContext(Class<?> propertySourceConfig) {
		return org.kuali.common.util.maven.MavenUtils.getMavenizedSpringContext(propertySourceConfig);
	}

	/**
	 * Return a SpringContext that resolves placeholders using the single <code>PropertySource</code> registered with <code>propertySourceConfig</code>
	 */
	public static SpringContext getMavenizedSpringContext(Properties mavenProperties, Class<?> propertySourceConfig) {
		return org.kuali.common.util.maven.MavenUtils.getMavenizedSpringContext(mavenProperties, propertySourceConfig);
	}

	/**
	 * Add organization, group, and path properties and tokenize the version number adding properties for each token along with a boolean property indicating if this is a SNAPSHOT
	 * build
	 */
	public static void augmentProjectProperties(Properties mavenProperties) {
		org.kuali.common.util.maven.MavenUtils.augmentProjectProperties(mavenProperties);
	}

	public static org.kuali.common.util.property.ProjectProperties getMavenProjectProperties(Properties mavenProperties) {
		return org.kuali.common.util.maven.MavenUtils.getMavenProjectProperties(mavenProperties);
	}

	/**
	 * Always return false if <code>forceMojoExecution</code> is true, otherwise return true only if <code>skip</code> is true or <code>packaging</code> is pom.
	 */
	public static final boolean skip(boolean forceMojoExecution, boolean skip, String packaging) {
		return org.kuali.common.util.maven.MavenUtils.skip(forceMojoExecution, skip, packaging);
	}

}
