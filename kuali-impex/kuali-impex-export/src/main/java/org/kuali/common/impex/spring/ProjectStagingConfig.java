package org.kuali.common.impex.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.impex.schema.CreateFilteredSchemaExecutable;
import org.kuali.common.impex.schema.DumpSchemaExecutable;
import org.kuali.common.impex.util.DumpConstants;
import org.kuali.common.impex.util.DumpUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.execute.CopyFilesExecutable;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ProjectStagingConfig {

	private static final String SERVICE_KEY = "impex.staging.schema.service";
	public static final String GAVS_KEY = "impex.staging.projects";
	public static final String DIR_KEY = "impex.staging.dir";
	public static final String SCHEMA_FILE_KEY = "impex.staging.schema.file";
	private static final String SKIP_KEY = "impex.staging.skip";
	public static final String RELATIVE_DIR_KEY = "impex.staging.dir.relative";

	@Autowired
	Environment env;

	@Bean
	public Executable projectStagingExecutable() {
		boolean skip = SpringUtils.getBoolean(env, SKIP_KEY, false);
		List<Executable> execs = Arrays.asList(createFilteredProjectSchemasExecutable(), copyProjectDataFilesExecutable());
		return new ExecutablesExecutable(execs, skip);
	}

	@Bean
	public Executable createFilteredProjectSchemasExecutable() {
		File inputSchemaFile = SpringUtils.getFile(env, SCHEMA_FILE_KEY);
		File stagingDir = SpringUtils.getFile(env, DIR_KEY);
		File relativeDir = SpringUtils.getFile(env, RELATIVE_DIR_KEY, stagingDir);
		List<Project> projects = getProjects();
		List<CreateFilteredSchemaExecutable> execs = new ArrayList<CreateFilteredSchemaExecutable>();
		for (Project project : projects) {
			CreateFilteredSchemaExecutable exec = getCreateFilteredSchemaExecutable(project, stagingDir, inputSchemaFile);
			exec.setRelativeDir(relativeDir);
			execs.add(exec);
		}
		return new ExecutablesExecutable(execs);
	}

	@Bean
	public Executable copyProjectDataFilesExecutable() {

		File stagingDir = SpringUtils.getFile(env, ProjectStagingConfig.DIR_KEY);
		File dumpDir = SpringUtils.getFile(env, DumpDataConfig.DIR_KEY);
		List<String> gavs = SpringUtils.getListFromCSV(env, ProjectStagingConfig.GAVS_KEY);

		List<CopyFilesExecutable> executables = new ArrayList<CopyFilesExecutable>();
		for (String gav : gavs) {
			CopyFilesExecutable exec = getCopyDataFilesExecutable(gav, dumpDir, stagingDir);
			executables.add(exec);
		}
		return new ExecutablesExecutable(executables);
	}

	protected CopyFilesExecutable getCopyDataFilesExecutable(String gav, File dumpDir, File stagingDir) {

		// Get a Project model object from the GAV
		Project project = ProjectUtils.loadProject(gav);

		// dstDir is always based on groupId + artifactId
		File dstDir = DumpUtils.getOutputDir(stagingDir, project);

		// Setup the includes/excludes appropriate for this project
		String includesKey = "impex.staging.data." + project.getArtifactId() + ".includes";
		String excludesKey = "impex.staging.data." + project.getArtifactId() + ".excludes";
		List<String> includes = SpringUtils.getListFromCSV(env, includesKey, DumpConstants.DEFAULT_FILE_INCLUDE);
		List<String> excludes = SpringUtils.getListFromCSV(env, excludesKey, DumpConstants.DEFAULT_FILE_EXCLUDE);

		// Configure our executable
		CopyFilesExecutable exec = new CopyFilesExecutable();
		exec.setIncludes(includes);
		exec.setExcludes(excludes);
		exec.setSrcDir(dumpDir);
		exec.setDstDir(dstDir);
		return exec;
	}

	protected CreateFilteredSchemaExecutable getCreateFilteredSchemaExecutable(Project project, File stagingDir, File inputSchemaFile) {
		String includesKey = "impex.staging.schema." + project.getArtifactId() + ".includes";
		String excludesKey = "impex.staging.schema." + project.getArtifactId() + ".excludes";
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, includesKey, DumpConstants.DEFAULT_INCLUDE);
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, excludesKey, DumpConstants.DEFAULT_EXCLUDE);
		File outputFile = DumpUtils.getSchemaFile(stagingDir, project, inputSchemaFile);
		CreateFilteredSchemaExecutable exec = new CreateFilteredSchemaExecutable();
		exec.setIncludes(includes);
		exec.setExcludes(excludes);
		exec.setSchemaOutputFile(outputFile);
		exec.setSchemaInputFile(inputSchemaFile);
		exec.setService(SpringUtils.getInstance(env, SERVICE_KEY, DumpSchemaExecutable.DEFAULT_EXPORT_SCHEMA_SERVICE.getClass()));
		return exec;
	}

	protected List<Project> getProjects() {
		List<String> gavs = SpringUtils.getNoneSensitiveListFromCSV(env, GAVS_KEY);
		List<Project> projects = new ArrayList<Project>();
		for (String gav : gavs) {
			Project project = ProjectUtils.loadProject(gav);
			projects.add(project);
		}
		return projects;
	}

}
