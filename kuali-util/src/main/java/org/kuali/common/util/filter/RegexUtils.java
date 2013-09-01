package org.kuali.common.util.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegexUtils {

	/**
	 * Convert a {@code List<String>} into {@code List<Pattern>}
	 */
	public static List<Pattern> getPatterns(List<String> patterns) {
		List<Pattern> regexPatterns = new ArrayList<Pattern>();
		for (String pattern : patterns) {
			Pattern regexPattern = Pattern.compile(pattern);
			regexPatterns.add(regexPattern);
		}
		return regexPatterns;
	}

}
