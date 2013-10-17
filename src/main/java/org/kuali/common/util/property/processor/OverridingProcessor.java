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
package org.kuali.common.util.property.processor;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;

public final class OverridingProcessor implements PropertyProcessor {

	public static final int DEFAULT_INDENT = 2;
	public static final Mode DEFAULT_OVERRIDE_MODE = Mode.INFORM;
	public static final List<String> DEFAULT_INCLUDES = Collections.<String> emptyList();
	public static final List<String> DEFAULT_EXCLUDES = Collections.<String> emptyList();

	private final Mode overrideMode;
	private final Properties overrides;
	private final List<String> includes;
	private final List<String> excludes;
	private final int indent;

	public OverridingProcessor(Properties overrides) {
		this(overrides, DEFAULT_OVERRIDE_MODE, DEFAULT_INCLUDES, DEFAULT_EXCLUDES, DEFAULT_INDENT);
	}

	public OverridingProcessor(Properties overrides, List<String> includes) {
		this(overrides, DEFAULT_OVERRIDE_MODE, includes, DEFAULT_EXCLUDES, DEFAULT_INDENT);
	}

	public OverridingProcessor(Properties overrides, List<String> includes, List<String> excludes) {
		this(overrides, DEFAULT_OVERRIDE_MODE, includes, excludes, DEFAULT_INDENT);
	}

	public OverridingProcessor(Properties overrides, Mode overrideMode, List<String> includes, List<String> excludes, int indent) {
		Assert.noNulls(overrides, overrideMode, includes, excludes);
		Assert.isTrue(indent >= 0, "indent is negative");
		this.overrides = PropertyUtils.toImmutable(overrides);
		this.overrideMode = overrideMode;
		this.includes = ListUtils.newImmutableArrayList(includes);
		this.excludes = ListUtils.newImmutableArrayList(excludes);
		this.indent = indent;
	}

	@Override
	public void process(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(overrides, includes, excludes);
		for (String key : keys) {
			String newValue = overrides.getProperty(key);
			PropertyUtils.addOrOverrideProperty(properties, key, newValue, overrideMode, indent);
		}
	}

	public Mode getOverrideMode() {
		return overrideMode;
	}

	public Properties getOverrides() {
		return overrides;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public int getIndent() {
		return indent;
	}
}
