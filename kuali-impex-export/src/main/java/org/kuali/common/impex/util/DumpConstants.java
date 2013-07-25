/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.util;

import java.util.Arrays;
import java.util.List;

public class DumpConstants {

	/**
	 * Default value for matching regular expressions. All names are matched by default
	 */
	public static final String DEFAULT_REGEX_INCLUDE = ".*";

	/**
	 * Default exclude value for name matching. No names are excluded by default
	 */
	public static final String DEFAULT_REGEX_EXCLUDE = "";

	/**
	 * Default value for matching regular expressions. All names are matched by default
	 */
	public static final List<String> DEFAULT_REGEX_INCLUDES = Arrays.asList(DEFAULT_REGEX_INCLUDE);

	/**
	 * Default exclude value for name matching. No names are excluded by default
	 */
	public static final List<String> DEFAULT_REGEX_EXCLUDES = Arrays.asList(DEFAULT_REGEX_EXCLUDE);

	/**
	 * Default value for matching regular expressions. All names are matched by default
	 */
	public static final String DEFAULT_FILE_INCLUDE = "**/**";

	/**
	 * Default exclude value for name matching. No names are excluded by default
	 */
	public static final String DEFAULT_FILE_EXCLUDE = "";

	/**
	 * Default value for matching regular expressions. All names are matched by default
	 */
	public static final List<String> DEFAULT_FILE_INCLUDES = Arrays.asList(DEFAULT_FILE_INCLUDE);

	/**
	 * Default exclude value for name matching. No names are excluded by default
	 */
	public static final List<String> DEFAULT_FILE_EXCLUDES = Arrays.asList(DEFAULT_FILE_EXCLUDE);

}
