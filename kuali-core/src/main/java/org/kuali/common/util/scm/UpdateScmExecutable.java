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
package org.kuali.common.util.scm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.SyncResult;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.file.DirRequest;
import org.kuali.common.util.sync.DefaultSyncService;
import org.kuali.common.util.sync.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateScmExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(UpdateScmExecutable.class);

	public static final boolean DEFAULT_SKIP_VALUE = false;
	public static final boolean DEFAULT_SKIP_COMMIT_VALUE = true;

	boolean skip = DEFAULT_SKIP_VALUE;
	// Always skip the commit step, unless they specifically set this to false
	boolean skipCommit = DEFAULT_SKIP_COMMIT_VALUE;
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

		ScmRequest request = getScmRequest(requests, results, commitPaths);

		logger.info("---------- Sync results ----------");
		logger.info("Files added - {}", request.getAdds().size());
		logger.info("Files updated - {}", request.getUpdates().size());
		logger.info("Files deleted - {}", request.getDeletes().size());
		logger.info("---------- Sync results ----------");

		if (skipCommit) {
			logger.info("Skipping SCM commit");
		} else {
			scmService.add(request.getAdds());
			scmService.delete(request.getDeletes());
			scmService.commit(request.getCommits(), message);
		}
	}

	protected ScmRequest getScmRequest(List<DirRequest> requests, List<SyncResult> results, List<File> commitPaths) {
		List<File> adds = new ArrayList<File>();
		List<File> deletes = new ArrayList<File>();
		List<File> updates = new ArrayList<File>();

		for (SyncResult result : results) {
			adds.addAll(result.getAdds());
			deletes.addAll(result.getDeletes());
			updates.addAll(result.getUpdates());
		}

		// Add any commit paths they explicitly provided
		List<File> commits = new ArrayList<File>(CollectionUtils.toEmptyList(commitPaths));

		// Add each target directory as a path to recursively commit
		for (DirRequest request : CollectionUtils.toEmptyList(requests)) {
			commits.add(request.getTargetDir());
		}

		ScmRequest scmRequest = new ScmRequest();
		scmRequest.setAdds(adds);
		scmRequest.setUpdates(updates);
		scmRequest.setDeletes(deletes);
		scmRequest.setCommits(commits);
		return scmRequest;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean isSkipCommit() {
		return skipCommit;
	}

	public void setSkipCommit(boolean commitChanges) {
		this.skipCommit = commitChanges;
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
