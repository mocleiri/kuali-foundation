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

public class MetaInfContext {

	public static final boolean DEFAULT_SORT = true;

	boolean sort = DEFAULT_SORT;
	File outputFile;
	File baseDir;
	RelativeContext relativeContext;
	PropertiesContext propertiesContext = PropertiesContext.DEFAULT_PROPERTIES_CONTEXT;
	List<String> includes;
	List<String> excludes;

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public File getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(File baseDir) {
		this.baseDir = baseDir;
	}

	public RelativeContext getRelativeContext() {
		return relativeContext;
	}

	public void setRelativeContext(RelativeContext relativeContext) {
		this.relativeContext = relativeContext;
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
