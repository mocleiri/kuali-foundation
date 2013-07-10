package org.kuali.common.impex.spring;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.ScmRequest;
import org.kuali.common.util.execute.BuildScmExecutable;
import org.kuali.common.util.execute.PrepareScmDirExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ ProjectPrepareScmConfig.class })
public class UpdateScmConfig {

	private static final String UPDATE_KEY = "impex.scm.update.skip";
	private static final String MESSAGE_KEY = "impex.scm.update.commitMessage";
	private static final String SCM_URL_KEY = "project.scm.developerConnection";
	private static final String STATS_KEY = DumpDataConfig.STATS_LOCATION_KEY;

	@Autowired
	Environment env;

	@Autowired
	ProjectPrepareScmConfig projectPrepareScmConfig;

	@Bean
	public BuildScmExecutable buildScmExecutable() {

		boolean skip = SpringUtils.getBoolean(env, UPDATE_KEY);
		String commitMessage = SpringUtils.getProperty(env, MESSAGE_KEY);
		List<PrepareScmDirExecutable> preparers = projectPrepareScmConfig.prepareScmDirExecutables();
		ScmRequest request = new ScmRequest();
		request.setCommits(Arrays.asList(SpringUtils.getFile(env, STATS_KEY)));

		BuildScmExecutable exec = new BuildScmExecutable();
		exec.setExecutables(preparers);
		exec.setSkip(skip);
		exec.setCommitMessage(commitMessage);
		exec.setRequest(request);
		return exec;
	}
}