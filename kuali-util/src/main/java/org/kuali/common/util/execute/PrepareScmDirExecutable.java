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
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.SyncRequest;
import org.kuali.common.util.SyncResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrepareScmDirExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(PrepareScmDirExecutable.class);

	boolean skip;
	File srcDir;
	File scmDir;

	// Filled in during execution
	SyncResult result;

	@Override
	public void execute() {

		if (skip) {
			logger.info("Skipping preparation");
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(srcDir, "srcDir is null");
		Assert.notNull(scmDir, "scmDir is null");
		Assert.isExistingDir(srcDir, "srcDir is not an existing directory");
		Assert.isExistingDir(scmDir, "scmDir is not an existing directory");

		// Get a listing of all files in the source directory
		List<File> srcFiles = FileSystemUtils.getAllNonScmFiles(srcDir);

		// Create a sync request
		SyncRequest request = new SyncRequest(srcDir, srcFiles, scmDir);

		// Execute the sync request
		this.result = FileSystemUtils.syncFilesQuietly(request);
	}

	/**
	 * Expose <code>SyncResult</code> via a getter
	 */
	public SyncResult getResult() {
		return result;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
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

}
