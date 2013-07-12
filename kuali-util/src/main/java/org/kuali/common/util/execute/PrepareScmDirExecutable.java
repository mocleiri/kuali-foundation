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
package org.kuali.common.util.execute;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.DirectoryDiff;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.PrepareScmDirRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrepareScmDirExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(PrepareScmDirExecutable.class);

	boolean skip;
	boolean skipCopy;

	// Required
	PrepareScmDirRequest request;

	// Optional
	File relativeDir;

	// Filled in during execution
	DirectoryDiff diff;

	@Override
	public void execute() {

		if (skip) {
			logger.info("Skipping preparation");
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(request, "request is null");

		// Copy files into the SCM directory
		// The DirectoryDiff object captures the differences between the 2 directories *before* any files were copied
		this.diff = FileSystemUtils.prepareScmDir(request, relativeDir, skipCopy);
	}

	/**
	 * Expose <code>DirectoryDiff</code> via a getter
	 */
	public DirectoryDiff getDiff() {
		return diff;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public PrepareScmDirRequest getRequest() {
		return request;
	}

	public void setRequest(PrepareScmDirRequest request) {
		this.request = request;
	}

	public File getRelativeDir() {
		return relativeDir;
	}

	public void setRelativeDir(File relativeDir) {
		this.relativeDir = relativeDir;
	}

	public boolean isSkipCopy() {
		return skipCopy;
	}

	public void setSkipCopy(boolean skipCopy) {
		this.skipCopy = skipCopy;
	}

}
