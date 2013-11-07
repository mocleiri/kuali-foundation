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
import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.DirectoryScanner;

/**
 * This class provides a simple method for scanning a directory for files that match include/exclude patterns
 */
public class SimpleScanner extends DirectoryScanner {
	private static final String FS = File.separator;

	public SimpleScanner() {
		this((File) null, (String) null, (String) null);
	}

	public SimpleScanner(File baseDir, String include, String exclude) {
		this(baseDir, CollectionUtils.toEmptyList(include), CollectionUtils.toEmptyList(exclude));
	}

	public SimpleScanner(File baseDir, List<String> includes, List<String> excludes) {
		super();
		if (baseDir != null) {
			setBasedir(baseDir);
		}
		if (!CollectionUtils.isEmpty(includes)) {
			setIncludes(CollectionUtils.toStringArray(includes));
		}
		if (!CollectionUtils.isEmpty(excludes)) {
			setExcludes(CollectionUtils.toStringArray(excludes));
		}
	}

	/**
	 * This method scans the file system starting at <code>basedir</code> and returns files matching the provided include/exclude patterns
	 */
	public List<File> getFiles() {
		scan();
		String[] includedFiles = getIncludedFiles();
		List<File> files = new ArrayList<File>();
		for (String includedFile : includedFiles) {
			String filename = getBasedir().getAbsolutePath() + FS + includedFile;
			File file = new File(filename);
			files.add(file);
		}
		return files;
	}
}
