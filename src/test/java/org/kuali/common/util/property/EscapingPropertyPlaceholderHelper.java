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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *******************************************************************************************************************
 * WARNING
 *******************************************************************************************************************
 * This class needs extensive testing. It does not work right. Do not use this in any real sort of way.
 *******************************************************************************************************************
 *
 * Support the equivalent of <code>escapeString</code> from the maven-resources-plugin.
 *
 * This allows you to place <code>\</code> in front of placeholders you don't want replaced.
 *
 * The string <code>The sky over ${city} is \${color}</code> is resolved to <code>The sky over Phoenix is ${color}</code>
 *
 * You can also have default values that are themselves placeholders - <code>${foo:${bar}}</code>
 */
public class EscapingPropertyPlaceholderHelper extends org.springframework.util.PropertyPlaceholderHelper {

	private static final Logger logger = LoggerFactory.getLogger(EscapingPropertyPlaceholderHelper.class);
	private static final Map<String, String> wellKnownSimplePrefixes = new HashMap<String, String>(4);

	static {
		wellKnownSimplePrefixes.put("}", "{");
		wellKnownSimplePrefixes.put("]", "[");
		wellKnownSimplePrefixes.put(")", "(");
	}

	String placeholderPrefix;
	String placeholderSuffix;
	String valueSeparator;
	boolean ignoreUnresolvablePlaceholders;
	String simplePrefix;
	String escapeString = Constants.DEFAULT_ESCAPE_STRING;
	String skipString;

	public EscapingPropertyPlaceholderHelper(String placeholderPrefix, String placeholderSuffix) {
		this(placeholderPrefix, placeholderSuffix, Constants.DEFAULT_VALUE_SEPARATOR, Constants.DEFAULT_ESCAPE_STRING, Constants.DEFAULT_IGNORE_UNRESOLVABLE_PLACEHOLDERS);
	}

	public EscapingPropertyPlaceholderHelper(String placeholderPrefix, String placeholderSuffix, String valueSeparator, String escapeString, boolean ignoreUnresolvablePlaceholders) {
		super(placeholderPrefix, placeholderSuffix, valueSeparator, ignoreUnresolvablePlaceholders);
		this.placeholderPrefix = placeholderPrefix;
		this.placeholderSuffix = placeholderSuffix;
		this.valueSeparator = valueSeparator;
		this.ignoreUnresolvablePlaceholders = ignoreUnresolvablePlaceholders;
		String simplePrefixForSuffix = wellKnownSimplePrefixes.get(this.placeholderSuffix);
		if (simplePrefixForSuffix != null && this.placeholderPrefix.endsWith(simplePrefixForSuffix)) {
			this.simplePrefix = simplePrefixForSuffix;
		} else {
			this.simplePrefix = this.placeholderPrefix;
		}
		this.escapeString = StringUtils.trimToNull(escapeString);
		if (this.escapeString != null) {
			this.skipString = this.escapeString + this.placeholderPrefix;
		}
	}

	// ::JC:: New method
	protected int getStartIndex(String s, String prefix, String skipString, int fromIndex) {
		if (skipString == null) {
			return StringUtils.indexOf(s, prefix, fromIndex);
		}
		int pos = StringUtils.indexOf(s, skipString, fromIndex);
		int len = StringUtils.length(s);
		fromIndex = getNewFromIndex(fromIndex, pos, skipString);
		while (pos != -1 && fromIndex < len) {
			pos = StringUtils.indexOf(s, skipString, fromIndex);
			fromIndex = getNewFromIndex(fromIndex, pos, skipString);
		}
		if (fromIndex >= len) {
			return -1;
		} else {
			return StringUtils.indexOf(s, prefix, fromIndex);
		}
	}

	// ::JC:: New method
	protected int getNewFromIndex(int fromIndex, int pos, String s) {
		return pos == -1 ? fromIndex : pos + s.length();
	}

	@Override
	protected String parseStringValue(String strVal, PlaceholderResolver placeholderResolver, Set<String> visitedPlaceholders) {
		StringBuilder buf = new StringBuilder(strVal);

		// ::JC:: Replaced this line with the line below it
		// int startIndex = strVal.indexOf(this.placeholderPrefix);
		int startIndex = getStartIndex(strVal, placeholderPrefix, skipString, 0);
		while (startIndex != -1) {
			int endIndex = findPlaceholderEndIndex(buf, startIndex);
			if (endIndex != -1) {
				String placeholder = buf.substring(startIndex + this.placeholderPrefix.length(), endIndex);
				String originalPlaceholder = placeholder;
				if (!visitedPlaceholders.add(originalPlaceholder)) {
					throw new IllegalArgumentException("Circular placeholder reference '" + originalPlaceholder + "' in property definitions");
				}
				// Recursive invocation, parsing placeholders contained in the placeholder key.
				placeholder = parseStringValue(placeholder, placeholderResolver, visitedPlaceholders);
				// Now obtain the value for the fully resolved key...
				String propVal = placeholderResolver.resolvePlaceholder(placeholder);
				if (propVal == null && this.valueSeparator != null) {
					int separatorIndex = placeholder.indexOf(this.valueSeparator);
					if (separatorIndex != -1) {
						String actualPlaceholder = placeholder.substring(0, separatorIndex);
						String defaultValue = placeholder.substring(separatorIndex + this.valueSeparator.length());
						propVal = placeholderResolver.resolvePlaceholder(actualPlaceholder);
						if (propVal == null) {
							// Replaced this line with the line below it
							// propVal = defaultValue;
							propVal = parseStringValue(defaultValue, placeholderResolver, visitedPlaceholders);
						}
					}
				}
				if (propVal != null) {
					// Recursive invocation, parsing placeholders contained in the
					// previously resolved placeholder value.
					propVal = parseStringValue(propVal, placeholderResolver, visitedPlaceholders);
					buf.replace(startIndex, endIndex + this.placeholderSuffix.length(), propVal);
					if (logger.isTraceEnabled()) {
						logger.trace("Resolved placeholder '" + placeholder + "'");
					}
					// ::JC:: Replaced this line with the line below it
					// startIndex = buf.indexOf(this.placeholderPrefix, startIndex + propVal.length());
					int fromIndex = startIndex + propVal.length();
					startIndex = getStartIndex(buf.toString(), placeholderPrefix, skipString, fromIndex);
				} else if (this.ignoreUnresolvablePlaceholders) {
					// Proceed with unprocessed value.
					// ::JC:: Replaced this line with the line below it
					// startIndex = buf.indexOf(this.placeholderPrefix, endIndex + this.placeholderSuffix.length());
					int fromIndex = endIndex + placeholderSuffix.length();
					startIndex = getStartIndex(buf.toString(), placeholderPrefix, skipString, fromIndex);
				} else {
					throw new IllegalArgumentException("Could not resolve placeholder '" + placeholder + "'" + " in string value \"" + strVal + "\"");
				}
				visitedPlaceholders.remove(originalPlaceholder);
			} else {
				startIndex = -1;
			}
		}
		// ::JC:: Added this block
		String s = buf.toString();
		if (skipString != null) {
			// This isn't right, but I *think* it works unless a string contains the sequence "\${" without a matching "}"
			return StringUtils.replace(s, skipString, placeholderPrefix);
		} else {
			return s;
		}
	}

	private int findPlaceholderEndIndex(CharSequence buf, int startIndex) {
		int index = startIndex + this.placeholderPrefix.length();
		int withinNestedPlaceholder = 0;
		while (index < buf.length()) {
			if (org.springframework.util.StringUtils.substringMatch(buf, index, this.placeholderSuffix)) {
				if (withinNestedPlaceholder > 0) {
					withinNestedPlaceholder--;
					index = index + this.placeholderSuffix.length();
				} else {
					return index;
				}
			} else if (org.springframework.util.StringUtils.substringMatch(buf, index, this.simplePrefix)) {
				withinNestedPlaceholder++;
				index = index + this.simplePrefix.length();
			} else {
				index++;
			}
		}
		return -1;
	}

	public String getPlaceholderPrefix() {
		return placeholderPrefix;
	}

	public String getPlaceholderSuffix() {
		return placeholderSuffix;
	}

	public String getValueSeparator() {
		return valueSeparator;
	}

	public boolean isIgnoreUnresolvablePlaceholders() {
		return ignoreUnresolvablePlaceholders;
	}

	public String getSimplePrefix() {
		return simplePrefix;
	}

	public String getEscapeString() {
		return escapeString;
	}

	public String getSkipString() {
		return skipString;
	}

}
