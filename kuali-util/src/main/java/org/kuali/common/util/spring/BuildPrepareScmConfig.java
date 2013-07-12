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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.PrepareScmDirRequest;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.execute.PrepareScmDirExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class BuildPrepareScmConfig {

	private static final String PROJECTS_KEY = "build.scm.prepare.projects";
	private static final String SRC_DIR_KEY = "build.scm.prepare.dir.src";
	private static final String RELATIVE_DIR_KEY = "build.scm.prepare.dir.relative";

	// SCM directories to ignore
	private static final String IGNORES_KEY = "build.scm.prepare.ignores";
	private static final String SKIP_KEY = "build.scm.prepare.skip";
	private static final String DIFF_ONLY_KEY = "build.scm.prepare.diffOnly";

	@Autowired
	Environment env;

	@Bean
	public List<PrepareScmDirExecutable> prepareScmDirExecutables() {

		// This is the directory files get copied out of
		File srcDir = SpringUtils.getFile(env, SRC_DIR_KEY);
		File relativeDir = SpringUtils.getFile(env, RELATIVE_DIR_KEY, srcDir);

		// These are the projects we are updating
		List<String> gavs = SpringUtils.getNoneSensitiveListFromCSV(env, PROJECTS_KEY);
		List<Project> projects = ProjectUtils.getProjects(gavs);

		// Return a list of executables that can prepare the project's SCM directories
		return getPrepareScmDirExecutables(srcDir, projects, relativeDir);
	}

	protected List<PrepareScmDirExecutable> getPrepareScmDirExecutables(File srcDir, List<Project> projects, File relativeDir) {

		// Setup some storage for the executables
		List<PrepareScmDirExecutable> execs = new ArrayList<PrepareScmDirExecutable>();

		// Prepare an executable for handling each project
		for (Project project : projects) {

			// Figure out the project specific directory being managed by SCM
			// This is the directory files get copied into
			String projectScmDirKey = "build.scm.prepare." + project.getArtifactId() + ".dir";
			File projectDir = SpringUtils.getFile(env, projectScmDirKey);
			File scmDir = ProjectUtils.getResourceDirectory(projectDir, project);

			// This is the directory containing the files to copy to the project directory
			File resourceDir = ProjectUtils.getResourceDirectory(srcDir, project);

			// Ignore SCM meta data directories
			List<String> scmIgnorePatterns = SpringUtils.getNoneSensitiveListFromCSV(env, IGNORES_KEY);

			// Setup the request
			PrepareScmDirRequest request = new PrepareScmDirRequest();
			request.setSrcDir(resourceDir);
			request.setScmDir(scmDir);
			request.setScmIgnorePatterns(scmIgnorePatterns);

			// Setup the executable
			PrepareScmDirExecutable exec = new PrepareScmDirExecutable();
			exec.setRequest(request);
			exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
			exec.setDiffOnly(SpringUtils.getBoolean(env, DIFF_ONLY_KEY, false));
			exec.setRelativeDir(relativeDir);

			// Add the executable to our list
			execs.add(exec);

		}

		// Return the list we've created
		return execs;
	}

}
