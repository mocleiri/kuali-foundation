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
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.scm.ScmRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
public class ScmExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ScmExecutable.class);

	boolean skip;
	boolean logConfiguration;
	org.kuali.common.util.service.ScmService service;
	ScmRequest request;

	@Override
	public void execute() {

		// Show our current configuration
		if (logConfiguration) {
			log(this);
		}

		// They have explicitly asked that execution be skipped
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		// The SCM request is not allowed to be null here
		Assert.notNull(request);

		// There are no files to add/delete/commit, no point in going further.
		if (isEmpty(request)) {
			logger.info("Skipping execution.  Nothing to do!");
			return;
		}

		// Make sure we are configured correctly
		validateConfiguration(service, request);

		// Add files as needed
		if (!CollectionUtils.isEmpty(request.getAdds())) {
			service.add(request.getAdds());
		}

		// Delete files as needed
		if (!CollectionUtils.isEmpty(request.getDeletes())) {
			service.delete(request.getDeletes());
		}

		// Commit files as needed
		if (!CollectionUtils.isEmpty(request.getCommits())) {
			service.commit(request.getCommits(), request.getCommitMessage());
		}
	}

	protected void validateConfiguration(org.kuali.common.util.service.ScmService service, ScmRequest request) {
		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");
		if (!CollectionUtils.isEmpty(request.getCommits())) {
			Assert.hasText(request.getCommitMessage(), "commitMessage has no text");
		}
	}

	protected void log(ScmExecutable exec) {
		ScmRequest request = org.kuali.common.util.ScmUtils.cloneOrNew(exec.getRequest());
		String adds = FormatUtils.getCount(CollectionUtils.toEmptyList(request.getAdds()).size());
		String deletes = FormatUtils.getCount(CollectionUtils.toEmptyList(request.getDeletes()).size());
		String commits = FormatUtils.getCount(CollectionUtils.toEmptyList(request.getCommits()).size());

		logger.info(" -- SCM --");
		logger.info("Adds: {}", adds);
		logger.info("Deletes: {}", deletes);
		logger.info("Commits: {}", commits);
		logger.info("Skip: {}", skip);
	}

	public boolean isEmpty(ScmRequest request) {
		if (!CollectionUtils.isEmpty(request.getAdds())) {
			return false;
		}
		if (!CollectionUtils.isEmpty(request.getDeletes())) {
			return false;
		}
		if (!CollectionUtils.isEmpty(request.getCommits())) {
			return false;
		}
		return true;
	}

	public org.kuali.common.util.service.ScmService getService() {
		return service;
	}

	public void setService(org.kuali.common.util.service.ScmService service) {
		this.service = service;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public ScmRequest getRequest() {
		return request;
	}

	public void setRequest(ScmRequest request) {
		this.request = request;
	}

	public boolean isLogConfiguration() {
		return logConfiguration;
	}

	public void setLogConfiguration(boolean logConfiguration) {
		this.logConfiguration = logConfiguration;
	}

}
