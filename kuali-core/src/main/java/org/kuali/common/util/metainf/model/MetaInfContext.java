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
package org.kuali.common.util.metainf.model;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.kuali.common.util.Assert;
import org.springframework.util.ResourceUtils;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class MetaInfContext {

	private final File outputFile;
	private final File scanDir;
	private final String encoding;
	private final boolean sort;
	private final boolean includePropertiesFile;
	private final Optional<Comparator<MetaInfResource>> comparator;
	private final boolean includeFileSizes;
	private final boolean includeLineCounts;
	private final List<String> includes;
	private final List<String> excludes;
	private final boolean relativePaths;
	private final File relativeDir;
	private final String urlPrefix;

	public File getOutputFile() {
		return outputFile;
	}

	public File getScanDir() {
		return scanDir;
	}

	public String getEncoding() {
		return encoding;
	}

	public boolean isSort() {
		return sort;
	}

	public boolean isIncludePropertiesFile() {
		return includePropertiesFile;
	}

	public boolean isIncludeFileSizes() {
		return includeFileSizes;
	}

	public boolean isIncludeLineCounts() {
		return includeLineCounts;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public boolean isRelativePaths() {
		return relativePaths;
	}

	public File getRelativeDir() {
		return relativeDir;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public Optional<Comparator<MetaInfResource>> getComparator() {
		return comparator;
	}

	public static Builder builder(File outputFile, String encoding, File scanDir) {
		return new Builder(outputFile, encoding, scanDir);
	}

	public static class Builder {

		// Required
		private final File outputFile;
		private final File scanDir;
		private final String encoding; // Encoding is required both for reading line counts and for creating the .resources file

		// Optional
		private boolean sort = true;
		private boolean includePropertiesFile = false;
		private boolean includeFileSizes = true;
		private boolean includeLineCounts = false; // Make them explicitly set this to true, since it can be quite expensive
		private List<String> includes = Collections.emptyList();
		private List<String> excludes = Collections.emptyList();
		private boolean relativePaths = true;
		private String urlPrefix = ResourceUtils.CLASSPATH_URL_PREFIX;
		private Optional<Comparator<MetaInfResource>> comparator = Optional.absent();

		// Defaults to scanDir
		private File relativeDir;

		public Builder(File outputFile, String encoding, File scanDir) {
			this.outputFile = outputFile;
			this.encoding = encoding;
			this.scanDir = scanDir;
			this.relativeDir = scanDir; // Paths are generated relative to the scanDir by default
		}

		public Builder comparator(Comparator<MetaInfResource> comparator) {
			this.comparator = Optional.of(comparator);
			return this;
		}

		public Builder sort(boolean sort) {
			this.sort = sort;
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

		public Builder relativePaths(boolean relativePaths) {
			this.relativePaths = relativePaths;
			return this;
		}

		public Builder includePropertiesFile(boolean includePropertiesFile) {
			this.includePropertiesFile = includePropertiesFile;
			return this;
		}

		public Builder includeFileSizes(boolean includeFileSizes) {
			this.includeFileSizes = includeFileSizes;
			return this;
		}

		public Builder includeLineCounts(boolean includeLineCounts) {
			this.includeLineCounts = includeLineCounts;
			return this;
		}

		public MetaInfContext build() {
			Assert.noNulls(outputFile, scanDir, includes, excludes, relativeDir, comparator);
			Assert.noBlanks(encoding, urlPrefix);
			this.includes = ImmutableList.copyOf(includes);
			this.excludes = ImmutableList.copyOf(excludes);
			return new MetaInfContext(this);
		}

	}

	private MetaInfContext(Builder builder) {
		this.sort = builder.sort;
		this.encoding = builder.encoding;
		this.urlPrefix = builder.urlPrefix;
		this.includeFileSizes = builder.includeFileSizes;
		this.includeLineCounts = builder.includeLineCounts;
		this.includePropertiesFile = builder.includePropertiesFile;
		this.includes = builder.includes;
		this.excludes = builder.excludes;
		this.scanDir = builder.scanDir;
		this.relativeDir = builder.relativeDir;
		this.outputFile = builder.outputFile;
		this.relativePaths = builder.relativePaths;
		this.comparator = builder.comparator;
	}

}
