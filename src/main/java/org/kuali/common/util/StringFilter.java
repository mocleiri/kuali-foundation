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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides logic for filtering strings based on regular expressions.
 * 
 * @deprecated Use org.kuali.common.util.filter.StringFilter instead
 */
@Deprecated
public class StringFilter {

	private static final Logger logger = LoggerFactory.getLogger(StringFilter.class);

	/**
	 * List of include regular expressions.
	 */
	final List<String> includes;

	/**
	 * List of exclude regular expressions.
	 */
	final List<String> excludes;

	protected List<Pattern> includePatterns;
	protected List<Pattern> excludePatterns;

	protected StringFilter(List<String> includes, List<String> excludes) {
		this.includes = includes;
		this.excludes = excludes;
	}

	public static final StringFilter getInstance(List<String> includes, List<String> excludes) {
		StringFilter filter = new StringFilter(includes, excludes);
		filter.compilePatterns();
		return filter;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	/**
	 * Return true if the string should be included.
	 */
	public boolean include(String s) {
		if (exclude(s)) {
			logger.debug("Excluding {}", s);
			return false;
		} else {
			return CollectionUtils.isEmpty(includePatterns) || isMatch(s, includePatterns);
		}
	}

	/**
	 * Return true if the string should be excluded.
	 */
	private boolean exclude(String s) {
		return !CollectionUtils.isEmpty(excludePatterns) && isMatch(s, excludePatterns);
	}

	/**
	 * Return true if the string matches any of the patterns
	 */
	private boolean isMatch(String s, List<Pattern> patterns) {
		// Loop through the patterns looking for a match
		for (Pattern pattern : patterns) {
			Matcher matcher = pattern.matcher(s);
			// We found a match, return true
			if (matcher.matches()) {
				return true;
			}
		}
		// We cycled through all of the patterns without finding a match
		return false;
	}

	/**
	 * Compile the string patterns into Pattern objects
	 */
	protected void compilePatterns() {
		this.includePatterns = getPatterns(includes);
		this.excludePatterns = getPatterns(excludes);
	}

	/**
	 * Convert a {@code List<String>} into {@code List<Pattern>}
	 */
	protected List<Pattern> getPatterns(List<String> patterns) {
		if (CollectionUtils.isEmpty(patterns)) {
			return Collections.<Pattern> emptyList();
		}
		List<Pattern> regexPatterns = new ArrayList<Pattern>();
		for (String pattern : patterns) {
			Pattern regexPattern = Pattern.compile(pattern);
			regexPatterns.add(regexPattern);
		}
		return regexPatterns;
	}
}
