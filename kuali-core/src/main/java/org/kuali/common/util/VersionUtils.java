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

import org.apache.commons.lang3.StringUtils;

public class VersionUtils {

	public static final String MAVEN_SNAPSHOT_TOKEN = "SNAPSHOT";
	private static final String[] DELIMITERS = new String[] { ".", "-" };
	private static final String SEPARATOR_CHARS = Str.toString(DELIMITERS);

	/**
	 * Return true if <code>version</code> ends with <code>-SNAPSHOT</code> or <code>.SNAPSHOT</code> (case insensitive).
	 */
	public static final boolean isSnapshot(String version) {
		for (String delimiter : DELIMITERS) {
			String suffix = delimiter + MAVEN_SNAPSHOT_TOKEN;
			if (StringUtils.endsWithIgnoreCase(version, suffix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return <code>version</code> with <code>.SNAPSHOT</code> or <code>-SNAPSHOT</code> removed from the end (if present)
	 * 
	 * <pre>
	 * 1.0.0-SNAPSHOT returns 1.0.0
	 * 1.0.0.SNAPSHOT returns 1.0.0
	 * 1.0.0          returns 1.0.0
	 * 1.0.0SNAPSHOT  returns 1.0.0SNAPSHOT
	 * </pre>
	 */
	public static final String trimSnapshot(String version) {
		if (isSnapshot(version)) {
			int length = MAVEN_SNAPSHOT_TOKEN.length() + 1;
			return StringUtils.left(version, version.length() - length);
		} else {
			return version;
		}
	}

	/**
	 * Parse a <code>Version</code> object from the <code>version</code> string. The logic here is crudely simple. First <code>SNAPSHOT</code> is trimmed off the end of the string
	 * (if it exists). Whatever remains is then split into tokens using <code>.</code> and <code>-</code> as delimiters. The first 3 tokens encountered are <code>major</code>,
	 * <code>minor</code>, and <code>incremental</code>, respectively. Anything after that is the <code>qualifier</code>
	 */
	public static final Version getVersion(String version) {
		boolean snapshot = isSnapshot(version);
		String trimmed = trimSnapshot(version);
		Version v = new Version();
		v.setTrimmed(trimmed);
		v.setSnapshot(snapshot);
		String[] tokens = StringUtils.split(trimmed, SEPARATOR_CHARS);
		if (tokens.length > 0) {
			v.setMajor(tokens[0]);
		}
		if (tokens.length > 1) {
			v.setMinor(tokens[1]);
		}
		if (tokens.length > 2) {
			v.setIncremental(tokens[2]);
		}
		String qualifier = getQualifier(trimmed, tokens);
		v.setQualifier(qualifier);
		return v;
	}

	/**
	 * 2.4.0-beta1-SNAPSHOT -> 240BETA1
	 */
	public static String asSanitizedString(Version version) {
		StringBuilder sb = new StringBuilder();
		sb.append(StringUtils.trimToEmpty(version.getMajor()));
		sb.append(StringUtils.trimToEmpty(version.getMinor()));
		sb.append(StringUtils.trimToEmpty(version.getIncremental()));
		sb.append(StringUtils.trimToEmpty(version.getQualifier()));
		return sanitize(sb.toString());
	}

	/**
	 * Convert dots and dashes to underscores and convert to uppercase
	 */
	protected static String sanitize(String s) {
		s = StringUtils.replace(s, ".", "_");
		s = StringUtils.replace(s, "-", "_");
		return StringUtils.upperCase(s);
	}

	/**
	 * Convert dots and dashes to underscores and convert to uppercase
	 */
	public static final String getSanitizedQualifier(String qualifier) {
		if (qualifier == null) {
			return null;
		} else {
			return sanitize(qualifier);
		}
	}

	protected static final String getQualifier(String trimmed, String[] tokens) {
		if (tokens.length < 4) {
			return null;
		}
		int pos = tokens[0].length() + 1 + tokens[1].length() + 1 + tokens[2].length() + 1;
		return trimmed.substring(pos);
	}

}
