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

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.file.DirRequest;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import(ScmConfig.class)
public class ScmProjectConfig {

	private static final String PROJECTS_KEY = "scm.build.projects";
	private static final String RELATIVE_DIR_KEY = "scm.dir.relative";
	private static final String SOURCE_DIR_KEY = "scm.dir.src";
	private static final String INCLUDES_KEY = "scm.includes";
	private static final String EXCLUDES_KEY = "scm.excludes";
	private static final String SKIP_KEY = "scm.skip";
	private static final String COMMIT_SKIP_KEY = "scm.commit.skip";
	private static final String COMMIT_PATHS_KEY = "scm.commit.paths";
	private static final String COMMIT_MESSAGE_KEY = "scm.commit.message";

	@Autowired
	Environment env;

	@Autowired
	ScmConfig scmConfig;

	@Bean
	public Executable projectScmConfigUpdateScmExecutable() {

		boolean skip = SpringUtils.getBoolean(env, SKIP_KEY, UpdateScmExecutable.DEFAULT_SKIP_VALUE);
		boolean skipCommit = SpringUtils.getBoolean(env, COMMIT_SKIP_KEY, UpdateScmExecutable.DEFAULT_SKIP_COMMIT_VALUE);
		List<File> commitPaths = SpringUtils.getFilesFromCSV(env, COMMIT_PATHS_KEY, NullUtils.NONE);
		String commitMessage = SpringUtils.getProperty(env, COMMIT_MESSAGE_KEY);

		UpdateScmExecutable exec = new UpdateScmExecutable();
		exec.setRequests(projectScmConfigDirRequests());
		exec.setScmService(scmConfig.scmService());
		exec.setSkip(skip);
		exec.setSkipCommit(skipCommit);
		exec.setCommitPaths(commitPaths);
		exec.setMessage(commitMessage);
		return exec;
	}

	@Bean
	public List<DirRequest> projectScmConfigDirRequests() {
		List<String> projectIds = SpringUtils.getNoneSensitiveListFromCSV(env, PROJECTS_KEY);
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, INCLUDES_KEY);
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, EXCLUDES_KEY);
		List<ProjectIdentifier> identifiers = ProjectUtils.getIdentifiers(projectIds);
		File stagingDir = SpringUtils.getFile(env, SOURCE_DIR_KEY);
		File relativeDir = SpringUtils.getFile(env, RELATIVE_DIR_KEY);
		List<DirRequest> requests = new ArrayList<DirRequest>();
		for (ProjectIdentifier identifier : identifiers) {
			DirRequest request = getDirRequest(identifier, stagingDir, relativeDir, includes, excludes);
			requests.add(request);
		}
		return requests;
	}

	protected DirRequest getDirRequest(ProjectIdentifier identifier, File stagingDir, File relativeDir, List<String> includes, List<String> excludes) {
		String key = "scm.build." + identifier.getArtifactId() + ".dir";
		// This is ${project.basedir}/src/main/resources
		File projectResourceDir = SpringUtils.getFile(env, key);
		File targetDir = ProjectUtils.getResourceDirectory(projectResourceDir, identifier.getGroupId(), identifier.getArtifactId());
		File sourceDir = ProjectUtils.getResourceDirectory(stagingDir, identifier.getGroupId(), identifier.getArtifactId());
		DirRequest request = new DirRequest();
		request.setRelativeDir(relativeDir);
		request.setTargetDir(targetDir);
		request.setSourceDir(sourceDir);
		request.setIncludes(includes);
		request.setExcludes(excludes);
		return request;
	}

}
