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
public class DirectoryDiff {

	// The directories that were diff'd
	File dir1;
	File dir2;

	// Relative paths characterizing what files were found where
	List<String> both;
	List<String> dir1Only;
	List<String> dir2Only;

	public DirectoryDiff() {
		this(null, null);
	}

	public DirectoryDiff(File dir1, File dir2) {
		super();
		this.dir1 = dir1;
		this.dir2 = dir2;
	}

	public List<String> getDir1Only() {
		return dir1Only;
	}

	public void setDir1Only(List<String> dir1Only) {
		this.dir1Only = dir1Only;
	}

	public List<String> getDir2Only() {
		return dir2Only;
	}

	public void setDir2Only(List<String> dir2Only) {
		this.dir2Only = dir2Only;
	}

	public List<String> getBoth() {
		return both;
	}

	public void setBoth(List<String> both) {
		this.both = both;
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

}
