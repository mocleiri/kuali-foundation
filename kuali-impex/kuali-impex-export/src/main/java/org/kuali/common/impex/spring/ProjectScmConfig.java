package org.kuali.common.impex.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.PrepareScmDirRequest;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.execute.PrepareScmDirExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ProjectScmConfig {

	public static final String STAGING_DIR_KEY = ProjectStagingConfig.DIR_KEY;
	public static final String PROJECTS_KEY = "impex.scm.projects";
	public static final String RELATIVE_DIR_KEY = "impex.scm.dir.relative";

	// SCM directories to ignore
	public static final String IGNORES_KEY = "impex.scm.ignores";

	private static final String PREPARE_KEY = "impex.scm.prepare.skip";
	private static final String SKIP_KEY = "impex.scm.skip";

	@Autowired
	Environment env;

	@Bean
	public List<PrepareScmDirExecutable> prepareScmDirExecutables() {

		// This is the directory files get copied out of
		File stagingDir = SpringUtils.getFile(env, STAGING_DIR_KEY);

		// These are the projects we are updating
		List<Project> projects = ConfigUtils.getProjects(env, PROJECTS_KEY);

		// Return a list of executables that can prepare the directories
		return getPrepareScmDirExecutables(stagingDir, projects);
	}

	protected List<PrepareScmDirExecutable> getPrepareScmDirExecutables(File stagingDir, List<Project> projects) {

		// Setup some storage for the executables
		List<PrepareScmDirExecutable> execs = new ArrayList<PrepareScmDirExecutable>();

		// Prepare an executable for handling each project
		for (Project project : projects) {

			// Figure out the project specific directory being managed by SCM
			// This is the directory files get copied into
			String projectScmDirKey = "impex.scm." + project.getArtifactId() + ".dir";
			File projectDir = SpringUtils.getFile(env, projectScmDirKey);
			File scmDir = ProjectUtils.getResourceDirectory(projectDir, project);

			// This is the source directory containing the files to copy to the project directory
			File srcDir = ProjectUtils.getResourceDirectory(stagingDir, project);

			// Ignore SCM meta data directories
			List<String> scmIgnorePatterns = SpringUtils.getNoneSensitiveListFromCSV(env, IGNORES_KEY);

			// Setup the request
			PrepareScmDirRequest request = new PrepareScmDirRequest();
			request.setSrcDir(srcDir);
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
