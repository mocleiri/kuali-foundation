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

public class PrepareScmDirRequest {

	List<String> scmIgnorePatterns = FileSystemUtils.DEFAULT_SCM_IGNORE_PATTERNS;
	File srcDir;
	File scmDir;

	public PrepareScmDirRequest() {
		this(null, null);
	}

	public PrepareScmDirRequest(File srcDir, File scmDir) {
		super();
		this.srcDir = srcDir;
		this.scmDir = scmDir;
	}

	public PrepareScmDirRequest(File srcDir, File scmDir, List<String> scmIgnorePatterns) {
		super();
		this.srcDir = srcDir;
		this.scmDir = scmDir;
		this.scmIgnorePatterns = scmIgnorePatterns;
	}

	public File getSrcDir() {
		return srcDir;
	}

	public void setSrcDir(File srcDir) {
		this.srcDir = srcDir;
	}

	public File getScmDir() {
		return scmDir;
	}

	public void setScmDir(File scmDir) {
		this.scmDir = scmDir;
	}

	public List<String> getScmIgnorePatterns() {
		return scmIgnorePatterns;
	}

	public void setScmIgnorePatterns(List<String> scmIgnorePatterns) {
		this.scmIgnorePatterns = scmIgnorePatterns;
	}

}
