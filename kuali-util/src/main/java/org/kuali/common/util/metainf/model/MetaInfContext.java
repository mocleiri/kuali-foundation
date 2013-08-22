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
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;
import org.springframework.util.ResourceUtils;

public class MetaInfContext {

	private final File outputFile;
	private final File scanDir;
	private final String encoding;
	private final boolean sort;
	private final boolean includePropertiesFile;
	private final boolean includeFileSizes;
	private final boolean includeLineCounts;
	private final List<String> includes;
	private final List<String> excludes;
	private final boolean generateRelativePaths;
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

	public boolean isGenerateRelativePaths() {
		return generateRelativePaths;
	}

	public File getRelativeDir() {
		return relativeDir;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public static class Builder {

		// Required
		private final File outputFile;
		private final File scanDir;
		private final String encoding;

		// Optional
		private boolean sort = true;
		private boolean includePropertiesFile = false;
		private boolean includeFileSizes = true;
		private boolean includeLineCounts = false;
		private List<String> includes = Collections.<String> emptyList();
		private List<String> excludes = Collections.<String> emptyList();
		private boolean relativePaths = true;
		private String urlPrefix = ResourceUtils.CLASSPATH_URL_PREFIX;

		// Defaults to scanDir
		private File relativeDir;

		public Builder(File outputFile, String encoding, File scanDir) {
			this.outputFile = outputFile;
			this.scanDir = scanDir;
			this.encoding = encoding;
			this.relativeDir = scanDir;
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

		public Builder relativePaths(boolean generateRelativePaths) {
			this.relativePaths = generateRelativePaths;
			return this;
		}

		public Builder propertiesFile(boolean includePropertiesFile) {
			this.includePropertiesFile = includePropertiesFile;
			return this;
		}

		public Builder fileSizes(boolean includeFileSizes) {
			this.includeFileSizes = includeFileSizes;
			return this;
		}

		public Builder lineCounts(boolean includeLineCounts) {
			this.includeLineCounts = includeLineCounts;
			return this;
		}

		public MetaInfContext build() {
			Assert.noNulls(outputFile, scanDir, includes, excludes, relativeDir);
			Assert.noBlanks(encoding, urlPrefix);
			this.includes = ListUtils.newImmutableArrayList(includes);
			this.excludes = ListUtils.newImmutableArrayList(excludes);
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
		this.generateRelativePaths = builder.relativePaths;
	}

}
