package org.kuali.common.impex.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.schema.CreateFilteredSchemaExecutable;
import org.kuali.common.impex.schema.DumpSchemaExecutable;
import org.kuali.common.impex.util.DumpConstants;
import org.kuali.common.impex.util.DumpUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ProjectStagingConfig {

	private static final String GAVS_KEY = "impex.dump.staging.projects";
	private static final String DIR_KEY = "impex.dump.staging.dir";
	private static final String SERVICE_KEY = DumpSchemaConfig.SERVICE_KEY;
	private static final String SKIP_KEY = "impex.dump.staging.skip";

	@Autowired
	Environment env;

	@Bean
	public Executable copyProjectDataFilesExecutable() {
		File inputSchemaFile = SpringUtils.getFile(env, DumpSchemaConfig.FILE_KEY);
		File stagingDir = SpringUtils.getFile(env, DIR_KEY);
		List<Project> projects = getProjects();
		List<CreateFilteredSchemaExecutable> execs = new ArrayList<CreateFilteredSchemaExecutable>();
		for (Project project : projects) {
			CreateFilteredSchemaExecutable exec = getCreateFilteredSchemaExecutable(project, stagingDir, inputSchemaFile);
			execs.add(exec);
		}
		ExecutablesExecutable exec = new ExecutablesExecutable(execs);
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
		return exec;
	}
	@Bean
	public Executable createFilteredProjectSchemasExecutable() {
		File inputSchemaFile = SpringUtils.getFile(env, DumpSchemaConfig.FILE_KEY);
		File stagingDir = SpringUtils.getFile(env, DIR_KEY);
		List<Project> projects = getProjects();
		List<CreateFilteredSchemaExecutable> execs = new ArrayList<CreateFilteredSchemaExecutable>();
		for (Project project : projects) {
			CreateFilteredSchemaExecutable exec = getCreateFilteredSchemaExecutable(project, stagingDir, inputSchemaFile);
			execs.add(exec);
		}
		ExecutablesExecutable exec = new ExecutablesExecutable(execs);
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
		return exec;
	}

	protected CreateFilteredSchemaExecutable getCreateFilteredSchemaExecutable(Project project, File stagingDir, File inputSchemaFile) {
		String includesKey = "impex." + project.getArtifactId() + ".includes";
		String excludesKey = "impex." + project.getArtifactId() + ".excludes";
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, includesKey, DumpConstants.DEFAULT_INCLUDE);
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, excludesKey, DumpConstants.DEFAULT_EXCLUDE);
		File outputFile = DumpUtils.getSchemaFile(stagingDir, project, inputSchemaFile);
		CreateFilteredSchemaExecutable exec = new CreateFilteredSchemaExecutable();
		exec.setIncludes(includes);
		exec.setExcludes(excludes);
		exec.setOutputSchemaFile(outputFile);
		exec.setInputSchemaFile(inputSchemaFile);
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
