/**
 * Copyright 2010-2012 The Kuali Foundation
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

import org.kuali.common.util.Mode;
import org.springframework.util.PropertyPlaceholderHelper;

public final class Constants {

	public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
	public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";
	public static final String DEFAULT_ENCRYPTED_SUFFIX = ".encrypted";
	public static final String DEFAULT_PATH_SUFFIX = "path";
	public static final String DEFAULT_MAJOR_VERSION_SUFFIX = "major";
	public static final String DEFAULT_MINOR_VERSION_SUFFIX = "minor";
	public static final String DEFAULT_INCREMENTAL_VERSION_SUFFIX = "incremental";
	public static final String DEFAULT_QUALIFIER_VERSION_SUFFIX = "qualifier";
	public static final String DEFAULT_TRIMMED_VERSION_SUFFIX = "trimmed";
	public static final String DEFAULT_SNAPSHOT_VERSION_SUFFIX = "snapshot";
	public static final String DEFAULT_USER_HOME_KEY = "user.home";
	public static final String DEFAULT_CODE_SUFFIX = "code";
	public static final String DEFAULT_HOME_SUFFIX = "home";
	public static final String DEFAULT_GROUP_HOME_PREFIX = "project";
	public static final Mode DEFAULT_PROPERTY_OVERWRITE_MODE = Mode.INFORM;
	public static final PropertyPlaceholderHelper DEFAULT_PROPERTY_PLACEHOLDER_HELPER = new PropertyPlaceholderHelper(DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX);

}
