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

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.DirectoryDiff;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.ScmRequest;
import org.kuali.common.util.ScmUtils;
import org.kuali.common.util.service.ScmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuildScmExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(BuildScmExecutable.class);

	boolean skip;
	ScmService service;
	List<PrepareScmDirExecutable> executables;
	ScmRequest request;
	String commitMessage;

	// Make them explicitly override this to false in order for a commit happen
	boolean skipScm = true;

	@Override
	public void execute() {

		// They have explicitly asked that execution be skipped
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		// Prepare all of the SCM directories so they contain exactly what they need to contain
		List<DirectoryDiff> diffs = new ArrayList<DirectoryDiff>();
		for (PrepareScmDirExecutable exec : CollectionUtils.toEmptyList(executables)) {
			exec.execute();
			diffs.add(exec.getDiff());
		}

		// Execute a single request to the ScmService that takes into account all the project
		// directories + anything from the ScmRequest object we are explicitly configured with
		ScmRequest request = getScmRequest(diffs, this.request, commitMessage);
		ScmExecutable exec = new ScmExecutable();
		exec.setRequest(request);
		exec.setService(service);
		exec.setSkip(skipScm);
		exec.execute();

	}

	protected ScmRequest getScmRequest(List<DirectoryDiff> diffs, ScmRequest request, String commitMessage) {
		List<File> adds = new ArrayList<File>();
		List<File> deletes = new ArrayList<File>();
		List<File> commits = new ArrayList<File>();
		for (DirectoryDiff diff : diffs) {
			List<File> addFiles = FileSystemUtils.getFullPaths(diff.getDir2(), diff.getDir1Only());
			List<File> deleteFiles = FileSystemUtils.getFullPaths(diff.getDir2(), diff.getDir2Only());

			// Need to add/delete individual files
			adds.addAll(addFiles);
			deletes.addAll(deleteFiles);

			// Only need to commit the top level SCM dir
			commits.add(diff.getDir2());
		}

		ScmRequest sr = ScmUtils.cloneOrNew(request);
		sr.setAdds(CollectionUtils.nullSafeCombine(sr.getAdds(), adds));
		sr.setDeletes(CollectionUtils.nullSafeCombine(sr.getDeletes(), deletes));
		sr.setCommits(CollectionUtils.nullSafeCombine(sr.getCommits(), commits));
		sr.setCommitMessage(commitMessage);
		return sr;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public ScmService getService() {
		return service;
	}

	public void setService(ScmService service) {
		this.service = service;
	}

	public List<PrepareScmDirExecutable> getExecutables() {
		return executables;
	}

	public void setExecutables(List<PrepareScmDirExecutable> executables) {
		this.executables = executables;
	}

	public ScmRequest getRequest() {
		return request;
	}

	public void setRequest(ScmRequest request) {
		this.request = request;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}

	public boolean isSkipScm() {
		return skipScm;
	}

	public void setSkipScm(boolean skipScm) {
		this.skipScm = skipScm;
	}

}
