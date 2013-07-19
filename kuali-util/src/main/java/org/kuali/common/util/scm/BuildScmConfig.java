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

import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.file.DirRequest;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ ScmConfig.class })
public class BuildScmConfig {

	private static final String PROJECTS_KEY = "build.scm.projects";
	private static final String SRC_DIR_KEY = "build.scm.dir.src";
	private static final String RELATIVE_DIR_KEY = "build.scm.dir.relative";
	private static final String COMMITS_KEY = "build.scm.commits";

	// SCM directories to ignore
	private static final String IGNORES_KEY = "build.scm.ignores";
	private static final String SKIP_KEY = "build.scm.skip";

	@Autowired
	Environment env;

	@Autowired
	ScmConfig scmConfig;

	@Bean
	public UpdateScmExecutable updateScmExecutable() {

		// This is the directory files get copied out of
		File srcDir = SpringUtils.getFile(env, SRC_DIR_KEY);
		File relativeDir = SpringUtils.getFile(env, RELATIVE_DIR_KEY, srcDir);

		// These are the projects we are updating
		List<String> projectIds = SpringUtils.getNoneSensitiveListFromCSV(env, PROJECTS_KEY);
		List<Project> projects = ProjectUtils.getProjects(projectIds);
		List<File> commits = getCommits();

		List<DirRequest> requests = getDirRequests(srcDir, projects, relativeDir);

		UpdateScmExecutable exec = new UpdateScmExecutable();
		exec.setRequests(requests);
		exec.setCommitPaths(commits);
		exec.setScmService(scmConfig.scmService());
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));

		return exec;
	}

	protected List<File> getCommits() {
		List<String> commitPaths = SpringUtils.getNoneSensitiveListFromCSV(env, COMMITS_KEY);
		List<File> files = new ArrayList<File>();
		for (String commitPath : commitPaths) {
			File file = new File(commitPath);
			files.add(file);
		}
		return files;
	}

	protected List<DirRequest> getDirRequests(File srcDir, List<Project> projects, File relativeDir) {

		// Setup some storage for the requests
		List<DirRequest> requests = new ArrayList<DirRequest>();

		// Prepare an executable for handling each project
		for (Project project : projects) {

			// Figure out the project specific directory being managed by SCM
			// This is the directory files get copied into
			String projectScmDirKey = "build.scm." + project.getArtifactId() + ".dir";
			// This is ${project.basedir}/src/main/resources
			File projectDir = SpringUtils.getFile(env, projectScmDirKey);
			// This is ${project.basedir}/src/main/resources/${project.groupId.path}/${project.artifactId}
			File targetDir = ProjectUtils.getResourceDirectory(projectDir, project);

			// This is the staging directory where the .mpx files are
			File sourceDir = ProjectUtils.getResourceDirectory(srcDir, project);

			// Ignore SCM meta data directories
			List<String> scmIgnorePatterns = SpringUtils.getNoneSensitiveListFromCSV(env, IGNORES_KEY);

			// Setup the request
			DirRequest request = new DirRequest();
			request.setExcludes(scmIgnorePatterns);
			request.setRelativeDir(relativeDir);
			request.setSourceDir(sourceDir);
			request.setTargetDir(targetDir);

			// Add the executable to our list
			requests.add(request);

		}

		// Return the list we've created
		return requests;
	}

}
