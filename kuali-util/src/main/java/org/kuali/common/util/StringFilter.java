package org.kuali.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * This class provides logic for filtering strings based on regular expressions. Given a list of includePatterns and excludePatterns the
 * filter method will remove strings from an Iterator<String> if there is no match on an inclusion pattern or there is a match on an
 * exclusion pattern
 */
public class StringFilter {

	private static final Logger logger = LoggerFactory.getLogger(StringFilter.class);

	/**
	 * List of regular expressions. A string must match one of these to be included
	 */
	List<String> includes;

	/**
	 * List of regular expressions. If there is a match on any of these the string is filtered out
	 */
	List<String> excludes;

	List<Pattern> includePatterns;
	List<Pattern> excludePatterns;

	protected Boolean compiled = Boolean.FALSE;

	public StringFilter() {
		this(null, null);
	}

	public StringFilter(List<String> includes, List<String> excludes) {
		super();
		this.includes = includes;
		this.excludes = excludes;
	}

	/**
	 * Return true if the string should be included.
	 */
	public boolean include(String s) {
		assertCompiled();
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
	protected boolean exclude(String s) {
		return !CollectionUtils.isEmpty(includePatterns) && isMatch(s, excludePatterns);
	}

	/**
	 * Return true if the string matches any of the patterns
	 */
	protected boolean isMatch(String s, List<Pattern> patterns) {
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

	protected synchronized void assertCompiled() {
		Assert.isTrue(compiled, "Regular expression patterns have not been compiled.  Invoke compilePatterns() before invoking isInclude() or isExclude()");
	}

	/**
	 * Compile the string patterns into Pattern objects
	 */
	public synchronized void compilePatterns() {
		this.includePatterns = getPatterns(includes);
		this.excludePatterns = getPatterns(excludes);
		this.compiled = true;
	}

	/**
	 * Convert a List<String> into List<Pattern>
	 */
	protected List<Pattern> getPatterns(List<String> patterns) {
		// If List<String> is empty return an empty List<Pattern>
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

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

}
