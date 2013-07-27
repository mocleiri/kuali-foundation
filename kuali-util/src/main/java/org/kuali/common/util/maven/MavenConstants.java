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
package org.kuali.common.util.maven;

public abstract class MavenConstants {

	public static final String POM = "pom";
	public static final String PROJECT_BEAN_NAME = "mavenProject";
	public static final String PROPERTIES_BEAN_NAME = "mavenProperties";
	public static final String SPRING_PROFILE_NAME = "maven";
	public static final String GROUP_ID_KEY = "project.groupId";
	public static final String ARTIFACT_ID_KEY = "project.artifactId";
	public static final String VERSION_KEY = "project.version";
	public static final String ENCODING_KEY = "project.encoding";
	public static final String ORG_ID_KEY = "project.orgId";
	public static final String ORG_ID_CODE_KEY = "project.orgId.code";

	@Deprecated
	public static final String MAVEN_PROJECT_BEAN_NAME = PROJECT_BEAN_NAME;

	@Deprecated
	public static final String MAVEN_PROPERTIES_BEAN_NAME = PROPERTIES_BEAN_NAME;

}
