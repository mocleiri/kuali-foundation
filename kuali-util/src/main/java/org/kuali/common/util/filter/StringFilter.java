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
package org.kuali.common.util.filter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

/**
 * This class provides logic for filtering strings based on regular expressions.
 */
public final class StringFilter {

	/**
	 * List of include regular expressions.
	 */
	private final List<String> includes;

	/**
	 * List of exclude regular expressions.
	 */
	private final List<String> excludes;

	private final List<Pattern> includePatterns;
	private final List<Pattern> excludePatterns;

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
		if (isMatch(s, excludePatterns)) {
			// An exclude pattern match always "wins"
			return false;
		} else {
			// If there are no include patterns always return true
			// Otherwise only return true if the string matches one of the provided patterns
			return includePatterns.size() == 0 || isMatch(s, includePatterns);
		}
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

	public static class Builder {

		private final List<String> includes;
		private final List<String> excludes;
		private final List<Pattern> includePatterns;
		private final List<Pattern> excludePatterns;

		public Builder() {
			this(ImmutableList.<String> of(), ImmutableList.<String> of());
		}

		public Builder(List<String> includes) {
			this(includes, ImmutableList.<String> of());
		}

		public Builder(List<String> includes, List<String> excludes) {
			this.includes = ImmutableList.copyOf(includes);
			this.excludes = ImmutableList.copyOf(excludes);
			this.includePatterns = ImmutableList.copyOf(RegexUtils.getPatterns(includes));
			this.excludePatterns = ImmutableList.copyOf(RegexUtils.getPatterns(excludes));
		}

		public StringFilter build() {
			Assert.noNulls(includes, excludes, includePatterns, excludePatterns);
			return new StringFilter(this);
		}

	}

	private StringFilter(Builder builder) {
		this.includes = builder.includes;
		this.excludes = builder.excludes;
		this.includePatterns = builder.includePatterns;
		this.excludePatterns = builder.excludePatterns;
	}
}
