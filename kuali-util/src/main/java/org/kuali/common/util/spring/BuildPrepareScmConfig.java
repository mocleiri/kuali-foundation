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

	// SCM directories to ignore
	public static final String IGNORES_KEY = "build.scm.prepare.ignores";
	private static final String PREPARE_KEY = "build.scm.prepare.skip";

	@Autowired
	Environment env;

	@Bean
	public List<PrepareScmDirExecutable> prepareScmDirExecutables() {

		// This is the directory files get copied out of
		File srcDir = SpringUtils.getFile(env, SRC_DIR_KEY);

		// These are the projects we are updating
		List<String> gavs = SpringUtils.getNoneSensitiveListFromCSV(env, PROJECTS_KEY);
		List<Project> projects = ProjectUtils.getProjects(gavs);

		// Return a list of executables that can prepare the project's SCM directories
		return getPrepareScmDirExecutables(srcDir, projects);
	}

	protected List<PrepareScmDirExecutable> getPrepareScmDirExecutables(File srcDir, List<Project> projects) {

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
			exec.setSkip(SpringUtils.getBoolean(env, PREPARE_KEY));

			// Add the executable to our list
			execs.add(exec);

		}

		// Return the list we've created
		return execs;
	}

}
