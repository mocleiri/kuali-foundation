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

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.ImmutableProperties;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

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
		this.overrides = PropertyUtils.toImmutable(overrides);
		this.overrideMode = overrideMode;
		this.includes = ImmutableList.copyOf(includes);
		this.excludes = ImmutableList.copyOf(excludes);
		this.indent = indent;
		Builder.validate(this);
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

	private OverridingProcessor(Builder builder) {
		this.overrideMode = builder.overrideMode;
		this.overrides = builder.overrides;
		this.includes = builder.includes;
		this.excludes = builder.excludes;
		this.indent = builder.indent;
	}

	public static Builder builder(Properties overrides) {
		return new Builder(overrides);
	}

	public static class Builder {

		private final Properties overrides;
		private Mode overrideMode = DEFAULT_OVERRIDE_MODE;
		private List<String> includes = ImmutableList.of();
		private List<String> excludes = ImmutableList.of();
		private int indent = DEFAULT_INDENT;

		public Builder(Properties overrides) {
			this.overrides = ImmutableProperties.of(overrides);
		}

		public Builder overrideMode(Mode overrideMode) {
			this.overrideMode = overrideMode;
			return this;
		}

		public Builder includes(List<String> includes) {
			this.includes = includes;
			return this;
		}

		public Builder excludes(List<String> excludes) {
			this.excludes = excludes;
			return this;
		}

		public Builder indent(int indent) {
			this.indent = indent;
			return this;
		}

		public OverridingProcessor build() {
			OverridingProcessor instance = new OverridingProcessor(this);
			validate(instance);
			return instance;
		}

		private static void validate(OverridingProcessor instance) {
			Preconditions.checkNotNull(instance.overrides, "overrides cannot be null");
			Preconditions.checkArgument(instance.indent >= 0, "indent cannot be negative");
			Preconditions.checkNotNull(instance.includes, "includes cannot be null");
			Preconditions.checkNotNull(instance.excludes, "excludes cannot be null");
			Preconditions.checkNotNull(instance.overrideMode, "overrideMode cannot be null");
		}
	}
}
