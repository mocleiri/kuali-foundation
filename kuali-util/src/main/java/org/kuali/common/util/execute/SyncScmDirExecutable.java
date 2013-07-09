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

import org.kuali.common.util.Assert;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.SyncScmDirRequest;
import org.kuali.common.util.SyncScmDirResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncScmDirExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SyncScmDirExecutable.class);

	boolean skip;
	SyncScmDirRequest request;

	// Filled in during execution
	SyncScmDirResult result;

	@Override
	public void execute() {

		if (skip) {
			logger.info("Skipping preparation");
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(request, "request is null");

		// Execute the sync request
		this.result = FileSystemUtils.syncScmDir(request);
	}

	/**
	 * Expose <code>SyncScmDirResult</code> via a getter
	 */
	public SyncScmDirResult getResult() {
		return result;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public SyncScmDirRequest getRequest() {
		return request;
	}

	public void setRequest(SyncScmDirRequest request) {
		this.request = request;
	}

	public void setResult(SyncScmDirResult result) {
		this.result = result;
	}

}
