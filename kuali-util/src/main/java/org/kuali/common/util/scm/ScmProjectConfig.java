package org.kuali.common.util.scm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.file.DirRequest;
import org.kuali.common.util.property.Constants;
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
	private static final String RELATIVE_DIR_KEY = "scm.build.dir.relative";
	private static final String SOURCE_DIR_KEY = "scm.build.dir.src";
	private static final String INCLUDES_KEY = "scm.build.includes";
	private static final String EXCLUDES_KEY = "scm.build.excludes";
	private static final String SKIP_KEY = "scm.build.skip";
	private static final String SKIP_COMMIT_KEY = "scm.build.commit.skip";
	private static final String COMMITS_KEY = "scm.build.commits";

	@Autowired
	Environment env;

	@Autowired
	ScmConfig scmConfig;

	@Bean
	public Executable projectScmConfigUpdateScmExecutable() {
		boolean skip = SpringUtils.getBoolean(env, SKIP_KEY, UpdateScmExecutable.DEFAULT_SKIP_VALUE);
		boolean skipCommit = SpringUtils.getBoolean(env, SKIP_COMMIT_KEY, UpdateScmExecutable.DEFAULT_SKIP_COMMIT_VALUE);
		List<File> commits = SpringUtils.getFilesFromCSV(env, COMMITS_KEY, Constants.NONE);

		UpdateScmExecutable exec = new UpdateScmExecutable();
		exec.setRequests(projectScmConfigDirRequests());
		exec.setScmService(scmConfig.scmService());
		exec.setSkip(skip);
		exec.setSkipCommit(skipCommit);
		exec.setCommitPaths(commits);
		return exec;
	}

	@Bean
	public List<DirRequest> projectScmConfigDirRequests() {
		List<String> projectIds = SpringUtils.getNoneSensitiveListFromCSV(env, PROJECTS_KEY);
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, INCLUDES_KEY);
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, EXCLUDES_KEY);
		List<Project> projects = ProjectUtils.loadProjects(projectIds);
		File stagingDir = SpringUtils.getFile(env, SOURCE_DIR_KEY);
		File relativeDir = SpringUtils.getFile(env, RELATIVE_DIR_KEY);
		List<DirRequest> requests = new ArrayList<DirRequest>();
		for (Project project : projects) {
			DirRequest request = getDirRequest(project, stagingDir, relativeDir, includes, excludes);
			requests.add(request);
		}
		return requests;
	}

	protected DirRequest getDirRequest(Project project, File stagingDir, File relativeDir, List<String> includes, List<String> excludes) {
		String key = "scm.build." + project.getArtifactId() + ".dir";
		File projectResourceDir = SpringUtils.getFile(env, key);
		File targetDir = ProjectUtils.getResourceDirectory(projectResourceDir, project);
		File sourceDir = ProjectUtils.getResourceDirectory(stagingDir, project);
		DirRequest request = new DirRequest();
		request.setRelativeDir(relativeDir);
		request.setTargetDir(targetDir);
		request.setSourceDir(sourceDir);
		request.setIncludes(includes);
		request.setExcludes(excludes);
		return request;
	}

}
