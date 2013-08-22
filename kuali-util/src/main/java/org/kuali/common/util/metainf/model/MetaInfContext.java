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
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.file.CanonicalFile;

public class MetaInfContext {

	private final CanonicalFile outputFile;
	private final File scanDir;
	private final boolean sort;
	private final String encoding;
	private final boolean includePropertiesFile;
	private final boolean includeFileSizes;
	private final boolean includeLineCounts;
	private final List<String> includes;
	private final List<String> excludes;
	private final boolean generateRelativePaths;
	private final CanonicalFile parent;
	private final String urlPrefix;

	public boolean isSort() {
		return sort;
	}

	public CanonicalFile getOutputFile() {
		return outputFile;
	}

	public String getEncoding() {
		return encoding;
	}

	public static class Builder {

		private final CanonicalFile outputFile;
		private final String encoding;

		private boolean sort = true;

		public Builder(File outputFile, String encoding, File scanDir) {
			this.outputFile = new CanonicalFile(outputFile);
			this.encoding = encoding;
			this.relativeContext = new RelativeContext(scanContext.getDirectory());
		}

		public Builder sort(boolean sort) {
			this.sort = sort;
			return this;
		}

		public Builder relative(RelativeContext context) {
			this.relativeContext = context;
			return this;
		}

		public Builder properties(PropertiesContext context) {
			this.propertiesContext = context;
			return this;
		}

		public MetaInfContext build() {
			Assert.noNulls(outputFile, scanContext, relativeContext, propertiesContext);
			Assert.noBlanks(encoding);
			return new MetaInfContext(this);
		}

	}

	private MetaInfContext(Builder builder) {
		this.sort = builder.sort;
		this.encoding = builder.encoding;
		this.propertiesContext = builder.propertiesContext;
		this.outputFile = builder.outputFile;
		this.scanContext = builder.scanContext;
		this.relativeContext = builder.relativeContext;
	}

}
