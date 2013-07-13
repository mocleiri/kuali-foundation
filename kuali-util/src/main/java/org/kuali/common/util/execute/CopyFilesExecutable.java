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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.kuali.common.util.Assert;

public class CopyFilesExecutable implements Executable {

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

	public CopyFilesExecutable(List<CopyFileRequest> requests, boolean skip) {
		super();
		this.requests = requests;
		this.skip = skip;
	}

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		Assert.notNull(requests, "requests is null");

		List<CopyFileResult> results = new ArrayList<CopyFileResult>();
		for (CopyFileRequest request : requests) {
			CopyFileResult result = copyFile(request.getSource(), request.getDestination());
			results.add(result);
		}
		this.results = results;
	}

	protected CopyFileResult copyFile(File src, File dst) {
		try {
			boolean overwritten = dst.exists();
			FileUtils.copyFile(src, dst);
			return new CopyFileResult(src, dst, overwritten);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
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
