/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.apache.torque.util;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChangeDetector {

	private static final Log log = LogFactory.getLog(ChangeDetector.class);
	File controlFile;
	List<File> files;

	public ChangeDetector() {
		this(null, null);
	}

	public ChangeDetector(File controlFile, List<File> files) {
		super();
		this.controlFile = controlFile;
		this.files = files;
	}

	/**
	 * Return true if any file in the list of files has a last modified timestamp newer than the control file or if the
	 * control file does not exist
	 */
	public boolean isChanged() {
		if (!getControlFile().exists()) {
			log.debug("File " + getControlFile().getAbsolutePath() + " does not exist.  Returning true");
			return true;
		}
		long lastModified = getControlFile().lastModified();
		for (File file : getFiles()) {
			if (file.lastModified() > lastModified) {
				log.debug("File " + file.getAbsolutePath() + " was modified after " + getControlFile().getAbsolutePath() + " was last modified");
				return true;
			}
		}
		log.debug("No files were modified.");
		return false;
	}

	public File getControlFile() {
		return controlFile;
	}

	public void setControlFile(File controlFile) {
		this.controlFile = controlFile;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
}
