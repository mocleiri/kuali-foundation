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
import org.kuali.common.util.scm.ScmRequest;
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

	// Make them explicitly override this to false in order for any SCM updating to occur
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

		// Create an ScmRequest object that takes into account all the adds and deletes from each of the preparation steps
		ScmRequest request = getScmRequest(diffs);

		// Combine it with the request we've been directly supplied
		ScmRequest combined = combine(commitMessage, request, this.request);

		// Create an executable that will handle the combined request as a single commit
		ScmExecutable exec = new ScmExecutable();
		exec.setRequest(combined);
		exec.setService(service);
		exec.setSkip(skipScm);
		exec.setLogConfiguration(true);
		exec.execute();

	}

	protected ScmRequest getScmRequest(List<DirectoryDiff> diffs) {
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

		ScmRequest sr = new ScmRequest();
		sr.setAdds(adds);
		sr.setDeletes(deletes);
		sr.setCommits(commits);
		return sr;
	}

	protected ScmRequest combine(String commitMessage, ScmRequest... requests) {
		ScmRequest combined = new ScmRequest();
		for (ScmRequest request : requests) {
			if (request != null) {
				combined.setAdds(CollectionUtils.nullSafeCombine(combined.getAdds(), request.getAdds()));
				combined.setDeletes(CollectionUtils.nullSafeCombine(combined.getDeletes(), request.getDeletes()));
				combined.setCommits(CollectionUtils.nullSafeCombine(combined.getCommits(), request.getCommits()));
			}
		}
		combined.setCommitMessage(commitMessage);
		return combined;
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
