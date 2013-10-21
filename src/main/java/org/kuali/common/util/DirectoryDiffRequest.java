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
package org.kuali.common.util;

import java.io.File;
import java.util.List;

@Deprecated
public class DirectoryDiffRequest {

	List<String> includes = FileSystemUtils.DEFAULT_RECURSIVE_INCLUDES;
	List<String> excludes = FileSystemUtils.DEFAULT_SCM_IGNORE_PATTERNS;

	File dir1;
	File dir2;

	public DirectoryDiffRequest() {
		this(null, null);
	}

	public DirectoryDiffRequest(File dir1, File dir2) {
		super();
		this.dir1 = dir1;
		this.dir2 = dir2;
	}

	public File getDir1() {
		return dir1;
	}

	public void setDir1(File dir1) {
		this.dir1 = dir1;
	}

	public File getDir2() {
		return dir2;
	}

	public void setDir2(File dir2) {
		this.dir2 = dir2;
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
