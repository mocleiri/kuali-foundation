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

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FileSystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyFilesExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(CopyFilesExecutable.class);

	boolean skip;
	List<CopyFileRequest> requests;

	// Filled in during execution
	List<CopyFileResult> results;

	public CopyFilesExecutable() {
		this(null, false);
	}

	public CopyFilesExecutable(CopyFileRequest request) {
		this(Arrays.asList(request), false);
	}

	public CopyFilesExecutable(List<CopyFileRequest> requests) {
		this(requests, false);
	}

	public CopyFilesExecutable(List<CopyFileRequest> requests, boolean skip) {
		super();
		this.requests = requests;
		this.skip = skip;
	}

	@Override
	public void execute() {

		// Might be nothing to do
		if (skip) {
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(requests, "requests is null");

		// Show how many files we are copying
		logger.info("Copying {} files", requests.size());

		// Perform the file system copy
		List<CopyFileResult> results = FileSystemUtils.copyFiles(requests);

		// Store the results in our internal member variable
		this.results = results;
	}

	/**
	 * Expose the copy results via a getter
	 */
	public List<CopyFileResult> getResults() {
		return results;
	}

	public List<CopyFileRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<CopyFileRequest> requests) {
		this.requests = requests;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
