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
package org.kuali.common.util.file;

import java.io.File;
import java.util.List;

public class DirDiff {

	// The directories that were diff'd
	File sourceDir;
	File targetDir;

	// Relative paths characterizing what files were found where
	List<String> both;
	List<String> sourceDirOnly;
	List<String> targetDirOnly;

	public DirDiff() {
		this(null, null);
	}

	public DirDiff(File dir1, File dir2) {
		super();
		this.sourceDir = dir1;
		this.targetDir = dir2;
	}

	public List<String> getSourceDirOnly() {
		return sourceDirOnly;
	}

	public void setSourceDirOnly(List<String> dir1Only) {
		this.sourceDirOnly = dir1Only;
	}

	public List<String> getTargetDirOnly() {
		return targetDirOnly;
	}

	public void setTargetDirOnly(List<String> dir2Only) {
		this.targetDirOnly = dir2Only;
	}

	public List<String> getBoth() {
		return both;
	}

	public void setBoth(List<String> both) {
		this.both = both;
	}

	public File getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(File dir1) {
		this.sourceDir = dir1;
	}

	public File getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(File dir2) {
		this.targetDir = dir2;
	}

}
