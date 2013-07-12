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
package org.kuali.common.util.spring;

import java.util.List;

import org.kuali.common.util.ScmRequest;
import org.kuali.common.util.execute.BuildScmExecutable;
import org.kuali.common.util.execute.PrepareScmDirExecutable;
import org.kuali.common.util.service.ScmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ ScmConfig.class, BuildPrepareScmConfig.class })
public class BuildUpdateScmConfig {

	private static final String SKIP_KEY = "build.scm.update.skip";
	private static final String MESSAGE_KEY = "build.scm.update.commitMessage";
	private static final String COMMITS_KEY = "build.scm.update.commits";
	private static final String ADDS_KEY = "build.scm.update.adds";
	private static final String DELETES_KEY = "build.scm.update.deletes";

	@Autowired
	Environment env;

	@Autowired
	BuildPrepareScmConfig buildPrepareScmConfig;

	@Autowired
	ScmConfig scmConfig;

	@Bean
	public BuildScmExecutable buildScmExecutable() {

		boolean skip = SpringUtils.getBoolean(env, SKIP_KEY, true);
		String commitMessage = SpringUtils.getProperty(env, MESSAGE_KEY);
		List<PrepareScmDirExecutable> preparers = buildPrepareScmConfig.prepareScmDirExecutables();
		ScmRequest request = getScmRequest();
		ScmService service = scmConfig.scmService();

		BuildScmExecutable exec = new BuildScmExecutable();
		exec.setExecutables(preparers);
		exec.setSkip(skip);
		exec.setCommitMessage(commitMessage);
		exec.setRequest(request);
		exec.setService(service);
		exec.setSkipScm(skip);
		return exec;
	}

	protected ScmRequest getScmRequest() {
		ScmRequest request = new ScmRequest();
		request.setAdds(SpringUtils.getFilesFromCSV(env, ADDS_KEY));
		request.setCommits(SpringUtils.getFilesFromCSV(env, COMMITS_KEY));
		request.setDeletes(SpringUtils.getFilesFromCSV(env, DELETES_KEY));
		return request;
	}
}