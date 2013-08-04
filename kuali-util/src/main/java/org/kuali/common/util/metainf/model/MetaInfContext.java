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

import org.kuali.common.util.Assert;
import org.kuali.common.util.file.CanonicalFile;

public class MetaInfContext {

	public static final boolean DEFAULT_SORT = true;

	private final boolean sort;
	private final String encoding;
	private final PropertiesContext propertiesContext;
	private final File outputFile;
	private final ScanContext scanContext;
	private final RelativeContext relativeContext;

	public MetaInfContext(File outputFile, String encoding, File directory, String includes) {
		this(outputFile, encoding, new ScanContext(directory, includes), new RelativeContext(directory));
	}

	public MetaInfContext(File outputFile, String encoding, ScanContext scanContext, RelativeContext relativeContext) {
		this(outputFile, encoding, scanContext, relativeContext, DEFAULT_SORT, PropertiesContext.DEFAULT_PROPERTIES_CONTEXT);
	}

	public MetaInfContext(File outputFile, String encoding, ScanContext scanContext, RelativeContext relativeContext, boolean sort, PropertiesContext propertiesContext) {
		super();
		Assert.notNull(outputFile, encoding, scanContext, relativeContext, propertiesContext);
		this.outputFile = new CanonicalFile(outputFile);
		this.encoding = encoding;
		this.scanContext = scanContext;
		this.relativeContext = relativeContext;
		this.sort = sort;
		this.propertiesContext = propertiesContext;
	}

	public boolean isSort() {
		return sort;
	}

	public File getOutputFile() {
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
