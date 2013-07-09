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
import org.kuali.common.util.service.ScmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class ScmExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ScmExecutable.class);

	ScmService service;
	boolean skip;
	String commitMessage;
	List<File> adds;
	List<File> deletes;
	List<File> commits;

	@Override
	public void execute() {

		// They have explicitly asked that execution be skipped
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		// There are no files to add/delete/commit, no point in going further.
		if (isEmpty()) {
			logger.info("Skipping execution.  Nothing to do!");
			return;
		}

		// Make sure we are configured correctly
		validateConfiguration();

		// Add files as needed
		if (!CollectionUtils.isEmpty(adds)) {
			service.add(adds);
		}

		// Delete files as needed
		if (!CollectionUtils.isEmpty(deletes)) {
			service.delete(deletes);
		}

		// Commit files as needed
		if (!CollectionUtils.isEmpty(commits)) {
			service.commit(commits, commitMessage);
		}
	}

	protected void validateConfiguration() {
		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");
		if (!CollectionUtils.isEmpty(commits)) {
			Assert.hasText(commitMessage, "commitMessage has no text");
		}
	}

	public boolean isEmpty() {
		return CollectionUtils.isEmpty(adds) && CollectionUtils.isEmpty(deletes) && CollectionUtils.isEmpty(commits);
	}

	public ScmService getService() {
		return service;
	}

	public void setService(ScmService service) {
		this.service = service;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}

	public List<File> getAdds() {
		return adds;
	}

	public void setAdds(List<File> adds) {
		this.adds = adds;
	}

	public List<File> getDeletes() {
		return deletes;
	}

	public void setDeletes(List<File> deletes) {
		this.deletes = deletes;
	}

	public List<File> getCommits() {
		return commits;
	}

	public void setCommits(List<File> commits) {
		this.commits = commits;
	}

}
