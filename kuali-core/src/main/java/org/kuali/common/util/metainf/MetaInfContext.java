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
package org.kuali.common.util.metainf;

import java.io.File;
import java.util.List;

import org.kuali.common.util.CollectionUtils;

/**
 * @deprecated
 */
@Deprecated
public class MetaInfContext {

	/**
	 * These default values help ensure backwards compatibility with deprecated config classes using MetaInfContext
	 */
	public static final String DEFAULT_PREFIX = "classpath:";
	public static final boolean DEFAULT_SORT = true;

	String prefix = DEFAULT_PREFIX;
	boolean sort = DEFAULT_SORT;
	File baseDir;
	File outputFile;
	List<String> includes;
	List<String> excludes;
	boolean addPropertiesFile;
	boolean addLineCount;

	public File getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(File baseDir) {
		this.baseDir = baseDir;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
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

	public boolean isAddPropertiesFile() {
		return addPropertiesFile;
	}

	public void setAddPropertiesFile(boolean addPropertiesFile) {
		this.addPropertiesFile = addPropertiesFile;
	}

	public boolean isAddLineCount() {
		return addLineCount;
	}

	public void setAddLineCount(boolean addLineCount) {
		this.addLineCount = addLineCount;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("addPropertiesFile: ").append(addPropertiesFile);
		sb.append("addLineCount: ").append(addLineCount);
		sb.append("includes: ").append(CollectionUtils.getCSV(getIncludes()));
		sb.append("excludes: ").append(CollectionUtils.getCSV(getExcludes()));
		sb.append("output: ").append(outputFile.toString());
		sb.append("basedir: ").append(baseDir.toString());
		sb.append("prefix: ").append(prefix);
		sb.append("sort: ").append(sort);

		return sb.toString();
	}
}
