package org.kuali.common.impex.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ProjectStagingConfig {

	private static final String GAVS_KEY = "impex.dump.staging.projects";
	private static final String DIR_KEY = "impex.dump.staging.dir";

	@Autowired
	Environment env;

	@Bean
	public Executable createFilteredProjectSchemasExecutable() {

		List<Project> projects = getProjects();
		File inputSchemaFile = SpringUtils.getFile(env, DumpSchemaConfig.FILE_KEY);
		File stagingDir = SpringUtils.getFile(env, DIR_KEY);

		return null;
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
