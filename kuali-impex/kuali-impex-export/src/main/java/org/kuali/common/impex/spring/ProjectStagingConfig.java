package org.kuali.common.impex.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.DumpSchemaService;
import org.kuali.common.impex.schema.execute.DumpSchemaRequest;
import org.kuali.common.impex.schema.execute.DumpSchemasExecutable;
import org.kuali.common.impex.util.DumpConstants;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.execute.CopyFilesExecutable;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ ExportCommonConfig.class })
public class ProjectStagingConfig {

	private static final String GAVS_KEY = "impex.staging.projects";
	private static final String DST_DIR_KEY = "impex.staging.dir.dst";
	private static final String SRC_DIR_KEY = "impex.staging.dir.src";
	private static final String RELATIVE_DIR_KEY = "impex.staging.dir.relative";
	private static final String SCHEMA_FILE_KEY = "impex.staging.schema.file";

	private static final String SKIP_KEY = "impex.staging.skip";
	private static final String SERVICE_KEY = "impex.staging.schema.service";

	@Autowired
	Environment env;

	@Autowired
	ExportCommonConfig exportCommonConfig;

	@Bean
	public Executable projectStagingExecutable() {
		boolean skip = SpringUtils.getBoolean(env, SKIP_KEY, false);
		List<Executable> execs = Arrays.asList(dumpSchemaFilesExecutable(), copyProjectDataFilesExecutable());
		return new ExecutablesExecutable(execs, skip);
	}

	@Bean
	public Executable dumpSchemaFilesExecutable() {
		File existingSchemaFile = SpringUtils.getFile(env, SCHEMA_FILE_KEY);
		DumpSchemaService service = exportCommonConfig.exportDumpSchemaService();
		Schema schema = service.getSchema(existingSchemaFile);

		File stagingDir = SpringUtils.getFile(env, DST_DIR_KEY);
		File relativeDir = SpringUtils.getFile(env, RELATIVE_DIR_KEY, stagingDir);
		List<Project> projects = getProjects();
		List<DumpSchemaRequest> requests = new ArrayList<DumpSchemaRequest>();
		for (Project project : projects) {
			DumpSchemaRequest request = getDumpSchemaRequest(project, stagingDir, schema, existingSchemaFile);
			request.setRelativeDir(relativeDir);
			requests.add(request);
		}
		return new DumpSchemasExecutable(requests, service, false);
	}

	@Bean
	public Executable copyProjectDataFilesExecutable() {

		File stagingDir = SpringUtils.getFile(env, DST_DIR_KEY);
		File relativeDir = SpringUtils.getFile(env, RELATIVE_DIR_KEY, stagingDir);
		File sourceDir = SpringUtils.getFile(env, SRC_DIR_KEY);
		List<String> gavs = SpringUtils.getListFromCSV(env, GAVS_KEY);

		List<CopyFilesExecutable> executables = new ArrayList<CopyFilesExecutable>();
		for (String gav : gavs) {
			CopyFilesExecutable exec = getCopyDataFilesExecutable(gav, sourceDir, stagingDir);
			exec.setRelativeDir(relativeDir);
			executables.add(exec);
		}

		return new ExecutablesExecutable(executables);
	}

	protected CopyFilesExecutable getCopyDataFilesExecutable(String gav, File dumpDir, File stagingDir) {

		// Get a Project model object from the GAV
		Project project = ProjectUtils.loadProject(gav);

		// dstDir is always based on groupId + artifactId
		File dstDir = ProjectUtils.getResourceDirectory(stagingDir, project);

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

	protected DumpSchemaRequest getDumpSchemaRequest(Project project, File stagingDir, Schema schema, File existingSchemaFile) {
		String includesKey = "impex.staging.schema." + project.getArtifactId() + ".includes";
		String excludesKey = "impex.staging.schema." + project.getArtifactId() + ".excludes";
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, includesKey, DumpConstants.DEFAULT_REGEX_INCLUDE);
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, excludesKey, DumpConstants.DEFAULT_REGEX_EXCLUDE);
		File outputFile = ProjectUtils.getResourceFile(stagingDir, project, existingSchemaFile.getName());

		DumpSchemaRequest request = new DumpSchemaRequest();
		request.setIncludes(includes);
		request.setExcludes(excludes);
		request.setOutputFile(outputFile);
		request.setSchema(schema);
		return request;
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
