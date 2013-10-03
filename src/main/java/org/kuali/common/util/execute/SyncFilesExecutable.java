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
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.SyncRequest;
import org.kuali.common.util.SyncResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class SyncFilesExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SyncFilesExecutable.class);

	boolean skip;
	// Don't commit changes unless they specifically set this to true
	boolean commitChanges;
	org.kuali.common.util.service.ScmService service;
	String message = "Automated update";
	List<SyncRequest> requests;
	List<File> commitPaths;

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping file sync");
			return;
		}

		Assert.notNull(requests);
		Assert.notNull(service);
		Assert.notNull(commitPaths);

		List<File> adds = new ArrayList<File>();
		List<File> deletes = new ArrayList<File>();

		List<SyncResult> results = syncFiles();
		for (SyncResult result : results) {
			adds.addAll(result.getAdds());
			deletes.addAll(result.getDeletes());
		}

		logger.info("---------- Sync results ----------");
		logger.info("Files added - {}", adds.size());
		logger.info("Files deleted - {}", deletes.size());
		logger.info("---------- Sync results ----------");

		if (commitChanges) {
			service.add(adds);
			service.delete(deletes);
			service.commit(commitPaths, message);
		} else {
			logger.info("Skipping SCM commit");
		}
	}

	public List<SyncResult> syncFiles() {
		logger.info("Syncing {} requests", requests.size());
		try {
			return FileSystemUtils.syncFiles(requests);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public org.kuali.common.util.service.ScmService getService() {
		return service;
	}

	public void setService(org.kuali.common.util.service.ScmService service) {
		this.service = service;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isCommitChanges() {
		return commitChanges;
	}

	public void setCommitChanges(boolean commitChanges) {
		this.commitChanges = commitChanges;
	}

	public List<File> getCommitPaths() {
		return commitPaths;
	}

	public void setCommitPaths(List<File> commitPaths) {
		this.commitPaths = commitPaths;
	}

	public List<SyncRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<SyncRequest> requests) {
		this.requests = requests;
	}
}
