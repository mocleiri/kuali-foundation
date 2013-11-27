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
package org.kuali.common.util.nullify;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Optional;

public class NullUtils {

	public static final String NONE = "NONE";
	public static final String NULL = "NULL";

	/**
	 * Return true if:
	 * 
	 * <pre>
	 *  s == null
	 *  StringUtils.equalsIgnoreCase("null", s) == true
	 * </pre>
	 */
	public static final boolean isNull(String s) {
		return s == null || StringUtils.equalsIgnoreCase(NULL, s);
	}

	/**
	 * Return true if:
	 * 
	 * <pre>
	 * StringUtils.equalsIgnoreCase(&quot;none&quot;, s) == true
	 * </pre>
	 */
	public static final boolean isNone(String s) {
		return StringUtils.equalsIgnoreCase(NONE, s);
	}

	/**
	 * Return true if:
	 * 
	 * <pre>
	 *  s == null
	 *  StringUtils.equalsIgnoreCase("null", s) == true
	 *  StringUtils.equalsIgnoreCase("none", s) == true
	 * </pre>
	 */
	public static final boolean isNullOrNone(String s) {
		return isNull(s) || isNone(s);
	}

	/**
	 * Returns <code>null</code> if s is null, the empty string, pure whitespace, NONE, or NULL
	 */
	public static final Optional<String> toNull(String s) {
		return Optional.fromNullable(trimToNull(s));
	}

	/**
	 * Returns <code>null</code> if s is null, the empty string, pure whitespace, NONE, or NULL
	 */
	public static final String trimToNull(String s) {
		String trimmed = StringUtils.trimToNull(s);
		return isNullOrNone(trimmed) ? null : trimmed;
	}

	/**
	 * Returns <code>NONE</code> if s is null, the empty string, pure whitespace, NONE, or NULL
	 */
	public static final String trimToNone(String s) {
		if (trimToNull(s) == null) {
			return NONE;
		} else {
			return trimToNull(s);
		}
	}

}
