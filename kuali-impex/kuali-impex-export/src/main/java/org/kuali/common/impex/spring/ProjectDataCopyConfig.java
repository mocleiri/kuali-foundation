/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.util.ExportConstants;
import org.kuali.common.impex.util.ExportUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.execute.CopyFilesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 */
@Configuration
public class ProjectDataCopyConfig {

	public static final String PROJECTS_KEY = "impex.export.data.projects";
	public static final String WORKING_DIR_KEY = "impex.export.data.workingDir";

	@Autowired
	Environment env;

	@Bean
	public List<CopyFilesExecutable> copyDataFilesExecutables() {

		File workingDir = SpringUtils.getFile(env, WORKING_DIR_KEY);
		List<String> gavs = SpringUtils.getListFromCSV(env, PROJECTS_KEY);

		List<CopyFilesExecutable> executables = new ArrayList<CopyFilesExecutable>();
		for (String gav : gavs) {
			CopyFilesExecutable exec = getCopyDataFilesExecutable(gav, workingDir);
			executables.add(exec);
		}
		return executables;
	}

	protected CopyFilesExecutable getCopyDataFilesExecutable(String gav, File workingDir) {

		// Get a Project model object from the GAV
		Project project = ProjectUtils.loadProject(gav);

		// dstDir is always based on groupId + artifactId
		File dstDir = ExportUtils.getOutputDir(workingDir, project);

		// Setup the includes/excludes appropriate for this project
		String includesKey = "impex.export.data." + project.getArtifactId() + ".includes";
		String excludesKey = "impex.export.data." + project.getArtifactId() + ".excludes";
		List<String> includes = SpringUtils.getListFromCSV(env, includesKey, ExportConstants.DEFAULT_INCLUDE);
		List<String> excludes = SpringUtils.getListFromCSV(env, excludesKey, ExportConstants.DEFAULT_EXCLUDE);

		// Configure our executable
		CopyFilesExecutable exec = new CopyFilesExecutable();
		exec.setIncludes(includes);
		exec.setExcludes(excludes);
		exec.setSrcDir(workingDir);
		exec.setDstDir(dstDir);
		return exec;
	}

}
