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
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.file.CanonicalFile;

public class MetaInfContext {

	public static final boolean DEFAULT_SORT = true;
	public static final boolean DEFAULT_GENERATE_RELATIVE_PATHS = true;

	private final boolean sort;
	private final String encoding;
	private final PropertiesContext propertiesContext;
	private final CanonicalFile outputFile;
	private final ScanContext scanContext;
	private final RelativeContext relativeContext;

	public MetaInfContext(File outputFile, String encoding, File scanDir, String includes) {
		this(outputFile, encoding, scanDir, CollectionUtils.singletonList(includes));
	}

	public MetaInfContext(File outputFile, String encoding, File scanDir, String includes, boolean generateRelativePaths) {
		this(outputFile, encoding, scanDir, CollectionUtils.singletonList(includes), generateRelativePaths);
	}

	public MetaInfContext(File outputFile, String encoding, File scanDir, String... includes) {
		this(outputFile, encoding, scanDir, Arrays.asList(includes));
	}

	public MetaInfContext(File outputFile, String encoding, File scanDir, List<String> includes) {
		this(outputFile, encoding, new ScanContext(scanDir, includes), new RelativeContext(scanDir));
	}

	public MetaInfContext(File outputFile, String encoding, File scanDir, ScanContext scanContext) {
		this(outputFile, encoding, scanContext, new RelativeContext(scanDir));
	}

	public MetaInfContext(File outputFile, String encoding, File scanDir, ScanContext scanContext, boolean generateRelativePaths) {
		this(outputFile, encoding, scanContext, new RelativeContext(scanDir, generateRelativePaths));
	}

	public MetaInfContext(File outputFile, String encoding, File scanDir, List<String> includes, boolean generateRelativePaths) {
		this(outputFile, encoding, new ScanContext(scanDir, includes), new RelativeContext(scanDir, generateRelativePaths));
	}

	public MetaInfContext(File outputFile, String encoding, ScanContext scanContext, RelativeContext relativeContext) {
		this(outputFile, encoding, scanContext, relativeContext, DEFAULT_SORT, PropertiesContext.DEFAULT_PROPERTIES_CONTEXT);
	}

	public MetaInfContext(File outputFile, String encoding, ScanContext scanContext, RelativeContext relativeContext, boolean sort, PropertiesContext propertiesContext) {
		Assert.noNulls(outputFile, scanContext, relativeContext, propertiesContext);
		Assert.noBlanks(encoding);
		this.outputFile = new CanonicalFile(outputFile);
		this.encoding = encoding;
		this.scanContext = scanContext;
		this.relativeContext = relativeContext;
		this.sort = sort;
		this.propertiesContext = propertiesContext;
	}

	public static class Builder {

		private final CanonicalFile outputFile;
		private final String encoding;
		private final ScanContext scanContext;

		private boolean sort = true;
		private PropertiesContext propertiesContext = PropertiesContext.DEFAULT_PROPERTIES_CONTEXT;
		private RelativeContext relativeContext;

		public Builder(File outputFile, String encoding, ScanContext scanContext) {
			this.outputFile = new CanonicalFile(outputFile);
			this.encoding = encoding;
			this.scanContext = scanContext;
		}

		public MetaInfContext build() {
			Assert.noNulls(outputFile, scanContext, propertiesContext);
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

	public boolean isSort() {
		return sort;
	}

	public CanonicalFile getOutputFile() {
		return outputFile;
	}

	public ScanContext getScanContext() {
		return scanContext;
	}

	public RelativeContext getRelativeContext() {
		return relativeContext;
	}

	public PropertiesContext getPropertiesContext() {
		return propertiesContext;
	}

	public String getEncoding() {
		return encoding;
	}

}
