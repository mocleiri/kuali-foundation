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
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.SyncResult;
import org.kuali.common.util.file.DirRequest;
import org.kuali.common.util.service.ScmService;
import org.kuali.common.util.sync.DefaultSyncService;
import org.kuali.common.util.sync.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateScmExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(UpdateScmExecutable.class);

	boolean skip;
	// Don't commit changes unless they specifically set this to true
	boolean commitChanges;
	ScmService scmService;
	SyncService syncService = new DefaultSyncService();
	List<DirRequest> requests;
	String message = "Automated update";
	List<File> commitPaths;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		Assert.notNull(scmService);
		Assert.notNull(syncService);

		List<SyncResult> results = syncService.sync(requests);

		List<File> adds = new ArrayList<File>();
		List<File> deletes = new ArrayList<File>();
		List<File> updates = new ArrayList<File>();

		for (SyncResult result : results) {
			adds.addAll(result.getAdds());
			deletes.addAll(result.getDeletes());
			updates.addAll(result.getUpdates());
		}

		logger.info("---------- Sync results ----------");
		logger.info("Files added - {}", adds.size());
		logger.info("Files updated - {}", updates.size());
		logger.info("Files deleted - {}", deletes.size());
		logger.info("---------- Sync results ----------");

		if (commitChanges) {
			List<File> paths = new ArrayList<File>();
			paths.addAll(CollectionUtils.toEmptyList(commitPaths));
			for (DirRequest request : CollectionUtils.toEmptyList(requests)) {
				paths.add(request.getTargetDir());
			}
			scmService.add(adds);
			scmService.delete(deletes);
			scmService.commit(paths, message);
		} else {
			logger.info("Skipping SCM commit");
		}
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean isCommitChanges() {
		return commitChanges;
	}

	public void setCommitChanges(boolean commitChanges) {
		this.commitChanges = commitChanges;
	}

	public ScmService getScmService() {
		return scmService;
	}

	public void setScmService(ScmService scmService) {
		this.scmService = scmService;
	}

	public SyncService getSyncService() {
		return syncService;
	}

	public void setSyncService(SyncService syncService) {
		this.syncService = syncService;
	}

	public List<DirRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<DirRequest> requests) {
		this.requests = requests;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<File> getCommitPaths() {
		return commitPaths;
	}

	public void setCommitPaths(List<File> commitPaths) {
		this.commitPaths = commitPaths;
	}

}
