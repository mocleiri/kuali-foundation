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
package org.kuali.common.util.property;

import org.kuali.common.util.Encodings;
import org.kuali.common.util.Mode;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.property.processor.NoOpProcessor;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.springframework.util.PropertyPlaceholderHelper;

public final class Constants {

	@Deprecated
	public static final String UTF8 = Encodings.UTF8;
	@Deprecated
	public static final String DEFAULT_ENCODING = UTF8;
	@Deprecated
	public static final String DEFAULT_MAVEN_PROPERTIES_BEAN_NAME = MavenConstants.PROPERTIES_BEAN_NAME;
	public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
	public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";
	public static final String DEFAULT_VALUE_SEPARATOR = ":";
	public static final String DEFAULT_ESCAPE_STRING = "\\";
	public static final boolean DEFAULT_IGNORE_UNRESOLVABLE_PLACEHOLDERS = true;
	public static final boolean DEFAULT_RESOLVE_PLACEHOLDERS = true;
	public static final String DEFAULT_ENCRYPTED_SUFFIX = ".encrypted";
	public static final String DEFAULT_PATH_SUFFIX = "path";
	public static final String DEFAULT_MAJOR_VERSION_SUFFIX = "major";
	public static final String DEFAULT_MINOR_VERSION_SUFFIX = "minor";
	public static final String DEFAULT_INCREMENTAL_VERSION_SUFFIX = "incremental";
	public static final String DEFAULT_QUALIFIER_VERSION_SUFFIX = "qualifier";
	public static final String DEFAULT_TRIMMED_VERSION_SUFFIX = "trimmed";
	public static final String DEFAULT_SNAPSHOT_VERSION_SUFFIX = "snapshot";
	public static final String DEFAULT_CODE_SUFFIX = "code";
	public static final String DEFAULT_HOME_SUFFIX = "home";
	@Deprecated
	public static final String DEFAULT_GROUP_ID_PROPERTY = MavenConstants.GROUP_ID_KEY;
	@Deprecated
	public static final String DEFAULT_ARTIFACT_ID_PROPERTY = MavenConstants.ARTIFACT_ID_KEY;
	@Deprecated
	public static final String DEFAULT_VERSION_PROPERTY = MavenConstants.VERSION_KEY;
	public static final String DEFAULT_USER_HOME_PROPERTY = "user.home";
	public static final Mode DEFAULT_PROPERTY_OVERWRITE_MODE = Mode.INFORM;
	public static final GlobalPropertiesMode DEFAULT_GLOBAL_PROPERTIES_MODE = GlobalPropertiesMode.BOTH;
	public static final PropertyPlaceholderHelper DEFAULT_PROPERTY_PLACEHOLDER_HELPER = getDefaultHelper();
	public static final PropertyProcessor NO_OP_PROCESSOR = new NoOpProcessor();
	public static final String GROUP_ID = "groupId";
	public static final String GROUP = "group";
	@Deprecated
	public static final String NONE = "NONE";
	@Deprecated
	public static final String NULL = "NULL";
	public static final String WILDCARD = "*";

	@Deprecated
	public static final String ENCRYPTION_PREFIX = "ENC(";
	@Deprecated
	public static final String ENCRYPTION_SUFFIX = ")";

	// KS uses a bunch of groupId's. This was used to shorten everything thing to "org.kuali.student"
	// The CM 2.0 branch is the only software at Kuali that has that characteristic and has yet to upgrade to the 3.3.x Kuali pom
	@Deprecated
	public static final String GROUP_ID_BASE_PATH_KEY = "project.groupId.base.path";
	public static final String ARTIFACT_ID_KEY = MavenConstants.ARTIFACT_ID_KEY;
	public static final String GROUP_ID_PATH_KEY = MavenConstants.GROUP_ID_PATH_KEY;
	public static final String PROJECT_PROPERTIES_BASE = "META-INF/${" + GROUP_ID_PATH_KEY + "}/${" + ARTIFACT_ID_KEY + "}";
	public static final String PROJECT_PROPERTIES_FRAGMENT = PROJECT_PROPERTIES_BASE + "/project.properties";
	public static final String PROJECT_PROPERTIES_OUTPUTFILE = "${project.build.outputDirectory}/" + PROJECT_PROPERTIES_FRAGMENT;
	public static final String PROJECT_PROPERTIES_LOCATION = "classpath:" + PROJECT_PROPERTIES_FRAGMENT;

	public static final String RICE_PROPERTIES_SUFFIX = "rice-properties.xml";
	public static final String RICE_PROJECT_PROPERTIES_FRAGMENT = PROJECT_PROPERTIES_BASE + "/project-" + RICE_PROPERTIES_SUFFIX;
	public static final String RICE_PROJECT_PROPERTIES_OUTPUTFILE = "${project.build.outputDirectory}/" + RICE_PROJECT_PROPERTIES_FRAGMENT;
	public static final String RICE_PROJECT_PROPERTIES_LOCATION = "classpath:" + RICE_PROJECT_PROPERTIES_FRAGMENT;

	private static final PropertyPlaceholderHelper getDefaultHelper() {
		return new PropertyPlaceholderHelper(DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX, DEFAULT_VALUE_SEPARATOR, DEFAULT_IGNORE_UNRESOLVABLE_PLACEHOLDERS);
	}

}
